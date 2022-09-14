package com.demo.doman.car.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.demo.doman.car.dto.UserRequest;
import com.demo.doman.car.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This utility class have utility methods corresponding to JWT 
 * @author pavan
 *
 */

@Component
public class JWTUtility implements Serializable{

	private static final long serialVersionUID = -7964962164952690289L;


	@Value("${jwt.secret}")
	private String secretKey;

	/**
	 *  This method is used to generate token
	 * @param userRequest
	 * @return token  
	 */
	public String generateToken(UserRequest userRequest) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userRequest.getName());
	}

	/**
	 * This method is used to create token
	 * @param claims key/value pairs
	 * @param subject 
	 * @return
	 */
	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 ))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	/**
	 *  Get the claims from token and apply the claims resolver
	 * @param <T> 
	 * @param token the token
	 * @param claimsResolver
	 * @return
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
	
		return claimsResolver.apply(claims);
	}

	/**
	 * Get user name from token
	 * @param token
	 * @return
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Get expiration date from token
	 * @param token
	 * @return
	 */
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}


	/**
	 * Get claims from token
	 * @param token
	 * @return
	 */
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	/**
	 * return true if token expired
	 * @param token
	 * @return
	 */
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}



	/**
	 * Validate the token
	 * @param token
	 * @param user
	 * @return
	 */
	public Boolean validateToken(String token, User user) {
		final String username = extractUsername(token);
		return (username.equals(user.getName()) && !isTokenExpired(token));
	}

	/**
	 * Validate the token
	 * @param token
	 * @param userName
	 * @return
	 */
	public Boolean validateToken(String token, String userName) {
		final String tokenUserName = extractUsername(token);
		return (tokenUserName.equals(userName) && !isTokenExpired(token));
	}

	/**
	 * Get the user name from token
	 * @param token
	 * @return
	 */
	public String getUsernameFromToken(String token) {
		final String username = extractUsername(token);
		return username;
	}
}

