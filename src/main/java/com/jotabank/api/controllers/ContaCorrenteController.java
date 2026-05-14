package com.jotabank.api.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotabank.api.dtos.ContaDtosRequest;
import com.jotabank.api.exception.MessageError;
import com.jotabank.api.exception.NegativeNumberException;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.VerificarDadosConta;
import com.jotabank.api.models.ContaCorrente;
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
	
	@GetMapping("/todasContas")
	public List<ContaCorrente> getTodasContas(){
		return servContaCorrente.getAllConta();
		
	}
	
	@GetMapping("/buscarConta/{id}")
	
	public ResponseEntity<?> buscarContaById(@PathVariable("id") Long id) throws NumberFormatException, NegativeNumberException{
		
		if(id <= 0) {
			
			MessageError error = new MessageError("Error, valor igual a 0 ou é negativo!", "404");
			return ResponseEntity.badRequest().body(error);
		}
		ContaCorrente conta = servContaCorrente.getContaPorId(id);
		return ResponseEntity.ok(conta);
	}
	
	@PutMapping("/atualizarDadosConta/{id}")
	public ResponseEntity<?> atualizarDadosConta( @PathVariable("id") Long id, @RequestBody ContaDtosRequest request) throws VerificarDadosConta, ValidacaoDadosPessoa{
		
		return ResponseEntity.ok(servContaCorrente.updateConta(id, request));
		
	}
	
	@DeleteMapping("/deletarConta/{id}")
	public ResponseEntity<?> deletarContaById(@PathVariable("id") Long id){
		
		return ResponseEntity.ok(servContaCorrente.deletarContaById(id));
	}
}
