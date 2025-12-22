package com.jotabank.api.models;

import com.jotabank.api.exception.VerificarDadosConta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="tab_contaPoupanca")
public class ContaPoupanca extends Conta{
	
	public ContaPoupanca(Pessoa pessoa, double saldo, String password) throws VerificarDadosConta {
		super(pessoa, saldo, password);
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idContaPoupanca;

}
