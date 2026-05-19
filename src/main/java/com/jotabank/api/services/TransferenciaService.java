package com.jotabank.api.services;

import com.jotabank.api.dtos.DtoTransferResponse;
import com.jotabank.api.models.Conta; 

public interface TransferenciaService {
	
	public DtoTransferResponse transfePix(Conta titular, Conta destino, double valor);
	public DtoTransferResponse transfeTed(Conta titular, Conta destino, double valor);
	public DtoTransferResponse transfeDoc(Conta titular, Conta destino, double valor);
	public DtoTransferResponse transfeSaque(Conta conta, double saldo);
	public DtoTransferResponse transfeDeposito(Conta conta, double deposito);
}
