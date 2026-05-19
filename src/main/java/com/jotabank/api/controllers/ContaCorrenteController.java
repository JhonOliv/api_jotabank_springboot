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
import com.jotabank.api.dtos.ContaDtosRequest;
import com.jotabank.api.exception.MessageError;
import com.jotabank.api.exception.MessageSuccess;
import com.jotabank.api.exception.NegativeNumberException;
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
	public ResponseEntity<?> criarConta(@RequestBody ContaDtosRequest request) throws ValidacaoDadosPessoa, VerificarDadosConta {
				
		if(request.getNomeCompleto().isBlank() || request.getCpf().isBlank()) {
			MessageError error = new MessageError("Dados informados estão incorretos", "404");
			return ResponseEntity.badRequest().body(error);
		}
		
		servContaCorrente.criarContaCorrente(request); 
		MessageSuccess success = new MessageSuccess("Conta criada com sucesso!", "201");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(success);

	}
	
	@GetMapping("/todasContas")
	public ResponseEntity<?> getTodasContas(){
		
		if(servContaCorrente.getAllConta().isEmpty()) {
			MessageError error = new MessageError("Lista de Contas está vazia!", "422");
			return ResponseEntity.badRequest().body(error);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(servContaCorrente.getAllConta());
		
	}
	
	@GetMapping("/buscarConta/{id}")
	
	public ResponseEntity<?> buscarContaById(@PathVariable("id") Long id) throws NumberFormatException, NegativeNumberException{
		
		if(id.toString().matches("^[\\\\p{L}\\\\s]+$\\r\\n") || id <= 0 ) {
			
			MessageError error = new MessageError("Error, valor igual a 0 ou é negativo!", "404");
			return ResponseEntity.badRequest().body(error);
		}
		ContaDtoResponse conta = servContaCorrente.getContaPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(conta);
	}
	
	@PutMapping("/atualizarDadosConta/{id}")
	public ResponseEntity<?> atualizarDadosConta( @PathVariable("id") Long id, @RequestBody ContaDtosRequest request) throws VerificarDadosConta, ValidacaoDadosPessoa{
		
		String  dtoConta = servContaCorrente.updateConta(id, request);
		return ResponseEntity.status(HttpStatus.OK).body(dtoConta);
		
	}
	
	@DeleteMapping("/deletarConta/{id}")
	public ResponseEntity<?> deletarContaById(@PathVariable("id") Long id){
		System.out.print(id.toString().matches("^\\d+$\r\n"));
		
		if(id.toString().matches("^\\d+$\r\n") || id <= 0 ) {

			MessageError error = new MessageError("Erro ao deletar conta do usuário.", "404");
			return ResponseEntity.badRequest().body(error);
		}
		
		ContaDtosRequest dtoConta =  servContaCorrente.deletarContaById(id);
		
		MessageSuccess success = new MessageSuccess("Conta do " + dtoConta.getNomeCompleto() + 
				" foi deletada com sucesso!", "200");
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
	}
}
