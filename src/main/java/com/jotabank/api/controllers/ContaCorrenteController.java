package com.jotabank.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotabank.api.dtos.ContaDtosRequest;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.VerificarDadosConta;
import com.jotabank.api.services.ContaFactoryService;

@RestController
@RequestMapping("v1/api/conta")
public class ContaCorrenteController {

	@Autowired
	private ContaFactoryService servContaCorrente;
	
	@GetMapping
	public String helloWorld() {
		return servContaCorrente.testHelloWorld("Jhonatan Silva");
	}
	
	@PostMapping("/criarConta")
	public ResponseEntity<ContaDtosRequest> criarConta(@RequestBody ContaDtosRequest request) throws ValidacaoDadosPessoa, VerificarDadosConta {
				
		servContaCorrente.criarContaCorrente(request); 
		
		
		return new ResponseEntity<>(request, HttpStatus.CREATED);
	}
	
}
