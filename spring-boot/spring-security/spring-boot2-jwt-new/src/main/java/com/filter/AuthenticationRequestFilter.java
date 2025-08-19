package com.filter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.config.SecurityConfig;
import com.util.TokenHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class AuthenticationRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public AuthenticationRequestFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        /**
         * Cần đặt ở đầu tiên (để successfulAuthentication() sẽ trả về JSON)
         */
        response.setCharacterEncoding("UTF-8");
        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        String requestURI = request.getRequestURI();
        String path = requestURI.substring(request.getContextPath().length());
        if (isWhiteList(path)) {
            log.info("Request '{}' is in white list", path);
            chain.doFilter(request, response);
        } else {
            log.info("Filter request '{}'", path);
            String jwt = TokenHandler.getJwtFromRequest(request);
            if (StringUtils.isEmpty(jwt)) {
                log.info("Couldn't find bearer string");
                throw new BadCredentialsException("Missing authentication token (JWT)");
            }

            // DecodedJWT decoded = TokenHandler.decodedJWT(jwt);
            boolean validated = TokenHandler.validateJwt(jwt);
            if (!validated) {
                throw new JWTDecodeException("Invalid JWT token");
            }

            DecodedJWT decoded = TokenHandler.decodedJwt(jwt);
            String username = TokenHandler.getSubject(decoded);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null && StringUtils.isNotEmpty(username)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authToken = getAuthentication(userDetails, decoded);
                authToken.setDetails(new WebAuthenticationDetailsSource() //
                        .buildDetails(request));
                log.info("Authenticated '" + username + "', setting security context");
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(UserDetails userDetails, DecodedJWT decoded) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        Claim claims = TokenHandler.getClaim(decoded);
        if (StringUtils.isNotEmpty(claims.asString())) {
            Arrays.stream(claims.asString().split(",")).forEach(t -> {
                authorities.add(new SimpleGrantedAuthority(t));
            });
        }
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    private boolean isWhiteList(String path) {
        for (String pattern : SecurityConfig.PUBLIC_API) {
            if (antPathMatcher.match(pattern, path)) {
                return true;
            }
        }
        return false;
    }

}
