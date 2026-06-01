package com.jotabank.api.services;

import com.jotabank.api.dtos.DtoTransferResponse;
import com.jotabank.api.models.Conta; 

public interface TransferenciaService {
	
	public DtoTransferResponse transfePix(Long id, String cpf, double valor) throws Exception;
	public void transfeTed(Conta titular, Conta destino, double valor);
	public void transfeDoc(Conta titular, Conta destino, double valor);
	public void transfeSaque(Conta conta, double saldo);
	public void transfeDeposito(Conta conta, double deposito);
}
