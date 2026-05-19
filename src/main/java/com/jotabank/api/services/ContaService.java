package com.jotabank.api.services;

import java.util.List;

import com.jotabank.api.dtos.ContaDtoResponse;
import com.jotabank.api.dtos.ContaDtosRequest;
import com.jotabank.api.exception.NegativeNumberException;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.VerificarDadosConta;

public interface ContaService {
	
	
	public ContaDtosRequest criarContaCorrente(ContaDtosRequest request) throws ValidacaoDadosPessoa, VerificarDadosConta;
	public ContaDtoResponse getContaPorId(Long idConta) throws NumberFormatException, NegativeNumberException;
	public String  updateConta (Long id, ContaDtosRequest request) throws VerificarDadosConta, ValidacaoDadosPessoa;
	public ContaDtosRequest deletarContaById(Long id);
	public List<ContaDtoResponse> getAllConta();
}
