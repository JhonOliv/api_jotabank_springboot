package com.jotabank.api.controllers;


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

import com.jotabank.api.dtos.ContaDtoResponse;
import com.jotabank.api.dtos.ContaDtoRequest;
import com.jotabank.api.exception.GlobalExceptionHandler;
import com.jotabank.api.exception.NegativeNumberException;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.VerificarDadosConta;
import com.jotabank.api.services.ContaFactoryService;

@RestController
@RequestMapping("v1/api/conta")
public class ControllerConta {

	@Autowired
	private ContaFactoryService servContaCorrente;
	
	@GetMapping
	public String helloWorld() {
		return servContaCorrente.testHelloWorld("Jhonatan Silva");
	}
	
	@PostMapping("/criarConta")
	public ResponseEntity<?> criarConta(@RequestBody ContaDtoRequest request) throws ValidacaoDadosPessoa, VerificarDadosConta {
				
		if(request.getNomeCompleto().isBlank() || request.getCpf().isBlank()) {
			GlobalExceptionHandler error = new GlobalExceptionHandler("Dados informados estão incorretos", "404");
			return ResponseEntity.badRequest().body(error);
		}else if (servContaCorrente.criarContaCorrente(request) == null) {
			GlobalExceptionHandler error = new GlobalExceptionHandler("Conta jà existe, tente com outro cpf", "400");
			return ResponseEntity.badRequest().body(error);
		}
		
		servContaCorrente.criarContaCorrente(request); 
		GlobalExceptionHandler success = new GlobalExceptionHandler("Conta foi Criada com sucesso", "201");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(success);

	}
	
	@GetMapping("/todasContas")
	public ResponseEntity<?> getTodasContas(){
		
		if(servContaCorrente.getAllConta().isEmpty()) {
			GlobalExceptionHandler error = new GlobalExceptionHandler("Lista de Contas está vazia!", "404");
			return ResponseEntity.badRequest().body(error);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(servContaCorrente.getAllConta());
		
	}
	
	@GetMapping("/buscarConta/{id}")
	
	public ResponseEntity<?> buscarContaById(@PathVariable("id") Long id) throws NumberFormatException, NegativeNumberException{
		
		if(servContaCorrente.getContaPorId(id) == null) {
			
			GlobalExceptionHandler error = new GlobalExceptionHandler("Erro, conta não existe!", "404");
			return ResponseEntity.badRequest().body(error);
		}
		
		ContaDtoResponse conta = servContaCorrente.getContaPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(conta);
	}
	
	@PutMapping("/atualizarDadosConta/{id}")
	public ResponseEntity<?> atualizarDadosConta( @PathVariable("id") Long id, @RequestBody ContaDtoRequest request) throws VerificarDadosConta, ValidacaoDadosPessoa{
		
		String  dtoConta = servContaCorrente.updateConta(id, request);
		return ResponseEntity.status(HttpStatus.OK).body(dtoConta);
		
	}
	
	@DeleteMapping("/deletarConta/{id}")
	public ResponseEntity<?> deletarContaById(@PathVariable("id") Long id){
		System.out.print(id.toString().matches("^\\d+$\r\n"));
		
		if(id.toString().matches("^\\d+$\r\n") || id <= 0 ) {

			GlobalExceptionHandler error = new GlobalExceptionHandler("Erro ao deletar conta do usuário.", "404");
			return ResponseEntity.badRequest().body(error);
		}
		
		ContaDtoRequest dtoConta =  servContaCorrente.deletarContaById(id);
		
		GlobalExceptionHandler success = new GlobalExceptionHandler("Conta do " + dtoConta.getNomeCompleto() + 
				" foi deletada com sucesso!", "200");
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
	}
}
