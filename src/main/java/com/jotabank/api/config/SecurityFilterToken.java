package com.jotabank.api.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilterToken extends OncePerRequestFilter {

	@Autowired
	private TokenConfig tokenConfig;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

			String token = authorizationHeader.substring(7);

			Optional<JWTUserData> optUser = tokenConfig.validationToken(token);

			if (optUser.isPresent()) {

				JWTUserData jwtUser = optUser.get();

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser,
						null, null);

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(request, response);

	}

}
