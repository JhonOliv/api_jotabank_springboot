package com.jotabank.api.models;

import com.jotabank.api.exception.ValidacaoDadosPessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_cliente")
@MappedSuperclass
public class Cliente extends Pessoa {

	public Cliente(String nome, String cpf, String telefone, String endereco) throws ValidacaoDadosPessoa {
		super(nome, cpf, telefone, endereco);
		// TODO Auto-generated constructor stub
	}

	
}
