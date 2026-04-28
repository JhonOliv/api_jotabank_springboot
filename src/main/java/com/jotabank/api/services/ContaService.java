package com.jotabank.api.services;


import com.jotabank.api.models.Conta;

import java.util.List;

import com.jotabank.api.dtos.ContaDtosRequest;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.VerificarDadosConta;

public interface ContaService {
	
	
	public ContaDtosRequest criarContaCorrente(ContaDtosRequest request) throws ValidacaoDadosPessoa, VerificarDadosConta;
	public void getContaPorId(Long idConta);
	public void atualizarDadosConta(Conta conta) throws VerificarDadosConta;
	public void deletarContaById(Long id);
	public List<Conta> getAllConta();
}
