package com.jotabank.api.services;


import com.jotabank.api.models.Conta;

import java.util.List;

import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.VerificarDadosConta;
import com.jotabank.api.models.Cliente;

public interface ContaService {
	
	
	public void criarContaCorrente(Conta novaConta, Cliente titular) throws ValidacaoDadosPessoa, VerificarDadosConta;
	public Conta getContaPorId(Long idConta);
	public void atualizarDadosConta(Conta conta) throws VerificarDadosConta;
	public void deletarContaById(Long id);
	public List<Conta> getAllConta();
}
