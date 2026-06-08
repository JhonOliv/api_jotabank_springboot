package com.jotabank.api.models;

import com.jotabank.api.exception.VerificarDadosConta;

import jakarta.persistence.Entity;

@Entity
public class ContaPoupanca extends Conta{
	
	public ContaPoupanca(Cliente pessoa, double saldo, String password) throws VerificarDadosConta {
		super(pessoa, saldo, password);
		// TODO Auto-generated constructor stub
	}
	
	public ContaPoupanca() {
		
	}


}
