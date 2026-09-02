package com.info.util;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	private final JwtUtill jwt;
	private final UserDetailsService detailService;

	public JwtAuthenticationFilter(JwtUtill jwt, UserDetailsService detailService) {
		this.jwt = jwt;
		this.detailService = detailService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 String authHeader = request.getHeader("Authorization");

	        // No JWT -> continue
	        // This allows /user/register and /user/login to work
	        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	            filterChain.doFilter(request, response);
	            return;
	        }

	        // Remove "Bearer "
	        String token = authHeader.substring(7);

	        try {

	        	 String email = jwt.extractUsername(token);

	             if (email != null &&
	                 SecurityContextHolder.getContext().getAuthentication() == null) {

	                 // Load user from database
	                 UserDetails userDetails =
	                         detailService.loadUserByUsername(email);

	                 // Validate JWT
	                 jwt.validateToken(token);

	                 
	                 

	                 // Create authentication using authorities
	                 // returned by UserDetailsService
	                 UsernamePasswordAuthenticationToken authentication =
	                         new UsernamePasswordAuthenticationToken(
	                                 userDetails,
	                                 null,
	                                 userDetails.getAuthorities()
	                         );

	                 authentication.setDetails(
	                         new WebAuthenticationDetailsSource()
	                                 .buildDetails(request)
	                 );

	                 // Store authentication
	                 SecurityContextHolder.getContext()
	                         .setAuthentication(authentication);
	             }

	        } catch (Exception e) {

	            System.out.println("Invalid JWT: " + e.getMessage());
	        }

	        // Continue request
	        filterChain.doFilter(request, response);
		
		
	}
	

}
