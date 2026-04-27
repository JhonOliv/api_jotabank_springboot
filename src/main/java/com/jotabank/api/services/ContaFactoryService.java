package com.jotabank.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.VerificarDadosConta;
import com.jotabank.api.models.Cliente;
import com.jotabank.api.models.Conta;
import com.jotabank.api.repositories.ContaRepository;
import com.jotabank.api.repositories.PessoaRepository;

@Service
public class ContaFactoryService implements ContaService{
	@Autowired
	private ContaRepository conta;
	@Autowired
	private PessoaRepository cliente;
	
	public String testHelloWorld(String nome) {
		return "Hello World my dev " + nome;
	}

	@Override
	public void criarContaCorrente(Conta novaConta, Cliente titular) throws ValidacaoDadosPessoa, VerificarDadosConta{
		
		if(titular.getCpf().equals("")) {
			throw new ValidacaoDadosPessoa("Cpf inválido verifique o campo!");
		}else if(novaConta.getSaldoConta() <= 0) {
			throw new VerificarDadosConta("Seu saldo é igual ou menor que zero, verifique o campo");
		}else {	
			conta.save(novaConta);
			cliente.save(titular);
		}
		
		
	}
	
	@Override
	public Conta getContaPorId( Long idConta) {
		return this.conta.getById(idConta);
		
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
