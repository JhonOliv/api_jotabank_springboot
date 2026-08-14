package com.jotabank.api.config;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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
	
	
	public Optional<JWTUserData> validationToken(String token){
		
		try {
		
			Algorithm algoritmo = Algorithm.HMAC256(secret);
			DecodedJWT decode = JWT.require(algoritmo).build().verify(token);
			
			Long idConta = decode.getClaim("id_Conta").asLong();
			String username = decode.getSubject();
			return Optional.of(new JWTUserData(idConta, username));
			
			
		} catch (JWTVerificationException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		return null;
	}

}