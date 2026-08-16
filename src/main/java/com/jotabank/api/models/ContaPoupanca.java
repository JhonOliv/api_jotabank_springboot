package com.jotabank.api.models;


import com.jotabank.api.exception.VerificarDadosConta;

import jakarta.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class ContaPoupanca extends Conta{
	
	public ContaPoupanca(Cliente pessoa, double saldo, String password, Role role) throws VerificarDadosConta {
		super(pessoa, saldo, role, password);
		// TODO Auto-generated constructor stub
	}
	
	public ContaPoupanca() {
		
	}

	


}
