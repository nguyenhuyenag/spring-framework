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
	 
	 public JWTAuthenticationFilter(UserDetailsService service) {
		 this.userDetailsService = service;
	 }

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null, username = null;
        if (header != null && header.startsWith(TokenHandler.TOKEN_PREFIX)) {
			token = header.replace(TokenHandler.TOKEN_PREFIX, "");
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
            UserDetails user = userDetailsService.loadUserByUsername(username);
            if (TokenHandler.validateToken(token, user)) {
                UsernamePasswordAuthenticationToken authentication = TokenHandler.getAuthentication(token, SecurityContextHolder.getContext().getAuthentication(), user);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                LOG.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(req, res);
	}

}
