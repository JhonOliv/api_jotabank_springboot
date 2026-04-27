package com.jotabank.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotabank.api.models.Conta;
import com.jotabank.api.models.Pessoa;
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
	
	@PostMapping()
	public String criarConta(@RequestBody Conta corrente, @RequestBody Pessoa titular) {
		
		return "nome: " + titular.getNome() +
				" saldo: " + corrente.getSaldoConta();
	}
	
}
