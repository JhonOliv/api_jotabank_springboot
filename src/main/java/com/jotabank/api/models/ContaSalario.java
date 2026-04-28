package com.jotabank.api.models;

import com.jotabank.api.exception.VerificarDadosConta;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_contaSalario")
public class ContaSalario extends Conta{
	
	public ContaSalario(Cliente pessoa, double saldo, String password) throws VerificarDadosConta {
		super(pessoa, saldo, password);
		// TODO Auto-generated constructor stub
	}
	
	
}
