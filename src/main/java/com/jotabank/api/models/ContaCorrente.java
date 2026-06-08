package com.jotabank.api.models;

import com.jotabank.api.exception.VerificarDadosConta;
import jakarta.persistence.Entity;

@Entity
public class ContaCorrente extends Conta {
	
	public ContaCorrente(Cliente pessoa, double saldo, String password) throws VerificarDadosConta {
		super(pessoa, saldo, password);
		// TODO Auto-generated constructor stub
	}
	
	public ContaCorrente () {
		
	}
	
}
