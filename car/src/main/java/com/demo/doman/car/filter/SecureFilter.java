package com.demo.doman.car.filter;

import java.io.IOException;
import java.net.http.HttpHeaders;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.doman.car.service.UserService;
import com.demo.doman.car.util.JWTUtility;

@Component
public class SecureFilter extends OncePerRequestFilter {

	private static String AUTHORIZATION = "Authorization";
	
	@Autowired
	JWTUtility jwtUtil;

	@Autowired
	UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = request.getHeader(SecureFilter.AUTHORIZATION);

		if(null != token) {
			String userName = jwtUtil.getUsernameFromToken(token);
			if (null != userName &&
					SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails =	userService.loadUserByUsername(userName);
				boolean isValid = jwtUtil.validateToken(token, userDetails.getUsername());

				if(isValid) {
					UsernamePasswordAuthenticationToken authenticationToken = 
							new UsernamePasswordAuthenticationToken(
									userName,
									userDetails.getPassword(),
									userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		}

		filterChain.doFilter(request, response);


	}

}
