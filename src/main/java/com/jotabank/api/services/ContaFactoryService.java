package com.jotabank.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotabank.api.dtos.ContaDtosRequest;
import com.jotabank.api.exception.NegativeNumberException;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.VerificarDadosConta;
import com.jotabank.api.models.Cliente;
import com.jotabank.api.models.Conta;
import com.jotabank.api.models.ContaCorrente;
import com.jotabank.api.repositories.ContaRepository;
import com.jotabank.api.repositories.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class ContaFactoryService implements ContaService{
	@Autowired
	private ContaRepository repositoryConta;
	@Autowired
	private PessoaRepository repositoryCliente;
	
	public String testHelloWorld(String nome) {
		return "Hello World my dev " + nome;
	}

	@Override
	public ContaDtosRequest criarContaCorrente(ContaDtosRequest request) throws ValidacaoDadosPessoa, VerificarDadosConta{
		
		if(request.getNomeCompleto().isBlank() && request.getCpf().isBlank()) {
			throw new ValidacaoDadosPessoa("Erro ao inserir os dados pessoais.");
			
		}else if(request.getSaldo() <= 0) {
			throw new VerificarDadosConta("Saldo inserido é menor ou igual a 0");
		}
		
		Cliente titular = new Cliente(request.getNomeCompleto(), request.getCpf(),
				request.getTelefone(), request.getEndereco(), request.getSalario());
		Cliente clienteSalvo = repositoryCliente.save(titular);

		ContaCorrente novaConta = new ContaCorrente(clienteSalvo, request.getSaldo(), request.getPassword());	
		ContaCorrente contaCriada = repositoryConta.save(novaConta);
		
		return new ContaDtosRequest(clienteSalvo.getNome(), contaCriada.getSaldoConta());
		
	}
	
	@Override
	public ContaCorrente getContaPorId(Long idConta) throws NumberFormatException, NegativeNumberException {
		
			if(idConta.toString().matches("^[\\\\p{L}\\\\s]+$\\r\\n")) {
				throw new NumberFormatException("Aceita somente número!");
			}else if(idConta <= 0) {
				throw new NegativeNumberException("Você digitou um número negativo.");
			}
		
			ContaCorrente conta = repositoryConta.findById(idConta).orElse(null);
			
			return conta;
			
		
	}

	@Override
	@Transactional
	public String updateConta( Long id, ContaDtosRequest request) throws VerificarDadosConta, ValidacaoDadosPessoa {
		// TODO Auto-generated method stub

		if(request.getNomeCompleto().matches("^[\\p{L}\\s]+$\r\n") || request.getTelefone().matches("/^\\d+$/\r\n")) {
				throw new ValidacaoDadosPessoa("Dados inseridos incorretamente, entre em contato com seu administrador.");
		}
		
		Conta conta = repositoryConta.findById(id).orElseThrow(() -> new RuntimeException("Conta Não Encontrada"));
		
		Cliente clinte = conta.getTitular();
		
		clinte.setNome(request.getNomeCompleto());
		clinte.setCpf(request.getCpf());
		clinte.setEndereco(request.getEndereco());
		clinte.setTelefone(request.getTelefone());
		clinte.setSalarioCliente(request.getSalario());
		
		conta.setSaldoConta(request.getSaldo());
		conta.setPassword(request.getPassword());
		
		return "Atualizado com sucesso";
	}

	@Override
	public ContaDtosRequest deletarContaById(Long id) {
		// TODO Auto-generated method stub
		
	ContaCorrente conta = repositoryConta.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontada."));
	 repositoryConta.delete(conta);
	
	 return new ContaDtosRequest(conta.getTitular().getNome(), conta.getSaldoConta());
		
	}

	@Override
	public List<ContaCorrente> getAllConta() {				
		return repositoryConta.findAll();
	}


}
