package com.jotabank.api.models;

import com.jotabank.api.exception.ValidacaoDadosPessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tab_funcionario")
public class Funcionario extends Pessoa {
	
	public Funcionario(String nome, String cpf, String telefone, String endereco) throws ValidacaoDadosPessoa {
		super(nome, cpf, telefone, endereco);
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idFuncionario;

}
