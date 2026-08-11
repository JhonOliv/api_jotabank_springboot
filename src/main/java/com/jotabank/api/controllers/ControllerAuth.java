package com.jotabank.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotabank.api.config.TokenConfig;
import com.jotabank.api.dtos.ContaDtoLoginResponse;
import com.jotabank.api.dtos.ContaDtoLoginUser;
import com.jotabank.api.models.Conta;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ControllerAuth {
	
	private AuthenticationManager authManager;
	private PasswordEncoder passwordEncoder;
	private TokenConfig tokenConfig;
	
	@PostMapping("/login")
	public ResponseEntity<ContaDtoLoginResponse> login(@Valid @RequestBody ContaDtoLoginUser  request) {
		
		UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.cpf(), passwordEncoder.encode(request.password()));
		Authentication authentication = authManager.authenticate(userAndPass);
		
		Conta conta = (Conta) authentication.getPrincipal();
		String token = tokenConfig.generationToken(conta);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ContaDtoLoginResponse(token));

	}
}
