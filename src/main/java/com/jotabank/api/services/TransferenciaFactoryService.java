package com.jotabank.api.services;

import org.springframework.stereotype.Service;

import com.jotabank.api.dtos.DtoTransferResponse;
import com.jotabank.api.models.Conta;

@Service
public class TransferenciaFactoryService implements TransferenciaService {

	@Override
	public DtoTransferResponse transfePix(Conta titular, Conta destino, double valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtoTransferResponse transfeTed(Conta titular, Conta destino, double valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtoTransferResponse transfeDoc(Conta titular, Conta destino, double valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtoTransferResponse transfeSaque(Conta conta, double saldo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtoTransferResponse transfeDeposito(Conta conta, double deposito) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
