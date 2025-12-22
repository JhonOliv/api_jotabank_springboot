package com.jotabank.api.models;

import com.jotabank.api.exception.ValidacaoDadosPessoa;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class Pessoa {
	
	public Pessoa(String nome, String cpf, String telefone, String endereco) throws ValidacaoDadosPessoa {
		
		if(nome  != null || cpf != null  || telefone != null  || endereco != null ) {
			setNome(nome);
			setCpf(cpf);
			setTelefone(telefone);
			setEndereco(endereco);
		}else {
			throw new ValidacaoDadosPessoa("Erro ao inserir os dados, verifique se não há nenhum campo vazio");
		}
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPessoa;
	@Column(length = 50, nullable = false)
	private String nome;
	@Column(length = 15, nullable = false)
	private String cpf;
	@Column(length = 15, nullable = false)
	private String telefone;
	@Column(length = 100, nullable = false)
	private String endereco;
	
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return this.telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String toString() {
		return "Nome Titular: " + getNome() + "\n" + "CPF: " + getCpf();
	}

}
