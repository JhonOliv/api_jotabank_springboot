package com.jotabank.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotabank.api.config.TokenConfig;
import com.jotabank.api.dtos.LoginDtoRequest;
import com.jotabank.api.models.Conta;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ControllerAuth {
	
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenConfig tokenConfig;
	
	@GetMapping("/teste")
	public ResponseEntity<String> testeLogin(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Teste Realizado com sucesso.");
	}
	
	@PostMapping("/loginSystem")
	public ResponseEntity<?> loginSytem(@RequestBody LoginDtoRequest request){
		System.out.print(request.getUsername());
		System.out.print(request.getPassword());
		
		return null;
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDtoRequest request) {
		System.out.print(request.getUsername());
		
		UsernamePasswordAuthenticationToken userAndPass = new 
		UsernamePasswordAuthenticationToken(request.getUsername(),
		request.getPassword());
		
		System.out.print(userAndPass);
		try {
			Authentication authentication = authManager.authenticate(userAndPass);
			System.out.print(authentication.isAuthenticated());
			Conta conta = (Conta) authentication.getPrincipal();
			String token = tokenConfig.generationToken(conta);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(token);

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		return null;
		
	}
}
