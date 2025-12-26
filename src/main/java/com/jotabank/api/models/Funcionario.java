package com.jotabank.api.models;

import com.jotabank.api.exception.ValidacaoDadosPessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_funcionario")
@MappedSuperclass
public class Funcionario extends Pessoa {
	
	public Funcionario(String nome, String cpf, String telefone, String endereco) throws ValidacaoDadosPessoa {
		super(nome, cpf, telefone, endereco);
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idFuncionario;

}
