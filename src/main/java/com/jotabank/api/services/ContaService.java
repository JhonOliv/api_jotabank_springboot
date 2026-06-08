package com.jotabank.api.services;

import java.util.List;

import com.jotabank.api.dtos.ContaDtoResponse;
import com.jotabank.api.dtos.ContaDtoRequest;
import com.jotabank.api.exception.NegativeNumberException;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.VerificarDadosConta;

public interface ContaService {
	
	
	public ContaDtoRequest criarContaCorrente(ContaDtoRequest request) throws ValidacaoDadosPessoa, VerificarDadosConta;
	public ContaDtoResponse getContaPorId(Long idConta) throws NumberFormatException, NegativeNumberException;
	public String  updateConta (Long id, ContaDtoRequest request) throws VerificarDadosConta, ValidacaoDadosPessoa;
	public ContaDtoRequest deletarContaById(Long id);
	public List<ContaDtoResponse> getAllConta();
}
