package com.jotabank.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotabank.api.dtos.ContaDtosRequest;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.VerificarDadosConta;
import com.jotabank.api.models.Cliente;
import com.jotabank.api.models.Conta;
import com.jotabank.api.models.ContaCorrente;
import com.jotabank.api.repositories.ContaRepository;
import com.jotabank.api.repositories.PessoaRepository;

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

		Conta novaConta = new ContaCorrente(clienteSalvo, request.getSaldo(), request.getPassword());	
		Conta contaCriada = repositoryConta.save(novaConta);
		
		return new ContaDtosRequest(clienteSalvo.getNome(), contaCriada.getSaldoConta());
		
	}
	
	@Override
	public Optional<Conta> getContaPorId( Long idConta) {
		
			Optional<Conta> conta = repositoryConta.findById(idConta);
			return conta;
			
		
	}

	@Override
	public void atualizarDadosConta(Conta conta) throws VerificarDadosConta {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarContaById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Conta> getAllConta() {
		// TODO Auto-generated method stub
		return null;
	}


}
