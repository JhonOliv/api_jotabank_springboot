package com.jotabank.api.models;

import com.jotabank.api.exception.VerificarDadosConta;

import jakarta.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class ContaSalario extends Conta{
	
	public ContaSalario(Cliente pessoa, double saldo, String password, Role role) throws VerificarDadosConta {
		super(pessoa, saldo, role, password);
		// TODO Auto-generated constructor stub
	}
	
	
}
