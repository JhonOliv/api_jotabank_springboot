package com.jotabank.api.services;


import java.math.BigDecimal;

import com.jotabank.api.dtos.DtoTransferResponse;
import com.jotabank.api.dtos.HistoricoTransferenciaDTO;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.models.Conta; 

public interface TransferenciaService {
	
	public DtoTransferResponse transfePix(Long id, String cpf, BigDecimal valor) throws Exception;
	public DtoTransferResponse transfeTed(Long id, Integer numConta, double valor);
	public void transfeDoc(Conta titular, Conta destino, double valor);
	public void transfeSaque(Conta conta, double saldo);
	public void transfeDeposito(Conta conta, double deposito);
	public HistoricoTransferenciaDTO getHistoricoTransf(String cpf) throws ValidacaoDadosPessoa;
}
