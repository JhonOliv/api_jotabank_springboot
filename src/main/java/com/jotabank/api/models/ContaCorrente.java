package com.jotabank.api.models;


import com.jotabank.api.exception.VerificarDadosConta;
import jakarta.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class ContaCorrente extends Conta {
	
	public ContaCorrente(Cliente pessoa, double saldo, String password, Role role) throws VerificarDadosConta {
		super(pessoa, saldo, role,  password);
		// TODO Auto-generated constructor stub
	}
	
	public ContaCorrente () {
		
	}



}
