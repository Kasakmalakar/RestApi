package com.info.util;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtill {
	private final String SECRETKEY = "StudentManagementSystemSuperSecreteKey12345";

	public String generateToken(UserDetails user) {
		String token = Jwts.builder().setSubject(user.getUsername())
				.claim("roles", user.getAuthorities().stream().map(a -> a.getAuthority()) 
						.toList())
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
				.signWith(Keys.hmacShaKeyFor(SECRETKEY.getBytes()), SignatureAlgorithm.HS256).compact();
		return token;
	}
	public Claims extractClaims(String token) {
		  return Jwts.parserBuilder()
		  .setSigningKey(Keys.hmacShaKeyFor(SECRETKEY.getBytes()))
		  .build()
		  .parseClaimsJws(token)
		  .getBody();
	  }
	 public List<String> extractRoles(String token) {
	        Claims claims = extractClaims(token);
	        return claims.get("roles", List.class);
	    }
	  public void validateToken(String token) {
		  extractClaims(token);
	  }
	  public String extractUsername(String token) {
		    return extractClaims(token).getSubject();
		}
}
