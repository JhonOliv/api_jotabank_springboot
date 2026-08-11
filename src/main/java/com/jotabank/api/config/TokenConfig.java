package com.jotabank.api.config;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jotabank.api.models.Conta;

@Component
public class TokenConfig {
	
	private final String secret = "secret";
	
	public String generationToken(Conta conta) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		
		return JWT.create().withClaim("conta_id", conta.getIdConta())
				.withSubject(conta.getTitular().getCpf())
				.withExpiresAt(Instant.now().plusSeconds(5000))
				.withIssuedAt(Instant.now()).sign(algorithm);
	}

}