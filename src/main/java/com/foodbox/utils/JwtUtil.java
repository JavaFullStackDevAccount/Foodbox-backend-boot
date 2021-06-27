package com.foodbox.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private String SECRET_KEY = "THISISASECRETKEYUSEDT0GENERATETHEJWTTOKENTHISMUSTBEKEPTASMUCHSECUREASPOSSIBLEORCANLEADTOHUGEHARMTOTHEUSERSPRIVACY";

	/*
	 * public String extractUsername(String token) { return extractClaim(token
	 * Claims::getSubject); }
	 * 
	 * public Date extractExpirationDate(String token) { return extractClaim(token,
	 * Claims::getExpiration); }
	 * 
	 * public <T> T extractClaim(String token)
	 */

	@SuppressWarnings("deprecation")
	public Claims decodeToken(String token) {

		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	public String generateToken(UserDetails userDetails) {

		Map<String, Object> claims = new HashMap<String, Object>();

		return createToken(claims, userDetails.getUsername());
	}

	private boolean isTokenExpired(String token) {

		return decodeToken(token).getExpiration().before(new Date());

	}

	@SuppressWarnings("deprecation")
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public boolean validateToken(String token, UserDetails userDetails) {

		String username = decodeToken(token).getSubject();

		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

	}

}
