package com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	 @Autowired
	 private UserDetailsService userDetailsService;

//	private UsernamePasswordAuthenticationToken getAuthentication(String header) throws TokenExpiredException {
//		String token = header.replace(TokenHandler.PREFIX, "");
//		boolean expiration = TokenHandler.checkExpiration(token);
//		if (expiration) {
//			LOG.warn("Token expiration or remove");
//			throw new TokenExpiredException("Token expiration or remove");
//		} else {
//			String role = TokenHandler.getRole(token);
//			String username = TokenHandler.getUsername(token);
//			if (username != null) {
//				return new UsernamePasswordAuthenticationToken(username, null,
//						Collections.singleton(new SimpleGrantedAuthority(role)));
//			}
//		}
//		return null;
//	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//		String header = req.getHeader(HttpHeaders.AUTHORIZATION);
//		if (StringUtils.isEmpty(header) || !header.startsWith(TokenHandler.PREFIX)) {
//			chain.doFilter(req, res);
//			return;
//		}
//		try {
//			SecurityContextHolder.getContext().setAuthentication(getAuthentication(header));
//		} catch (TokenExpiredException e) {
//			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			res.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
//			String url = req.getRequestURI();
//			String json = JsonUtils.toJSON(new ApiError(401, "Unauthorized", e.getMessage(), url));
//			res.getWriter().write(json);
//			return;
//		}
//		super.doFilterInternal(req, res, chain);
		String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null, username = null;
        if (header != null && header.startsWith(TokenHandler.PREFIX)) {
			token = header.replace(TokenHandler.PREFIX, "");
            try {
                username = TokenHandler.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
            	LOG.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
            	LOG.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
            	LOG.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
        	LOG.warn("couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (TokenHandler.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = TokenHandler.getAuthentication(token, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                //UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                LOG.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(req, res);
	}

}
