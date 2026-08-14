package com.jotabank.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContaDtoRequest {

	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 3, max = 100)
	private String nomeCompleto;
	@NotBlank
	@Size(min= 11, max = 11)
	private String cpf;
	@Size(min = 11, max = 13)
	private String telefone;
	@Size(max = 70)
	private String endereco;
	private Double salario;
	private Double saldo;
	private String password;
	
	public ContaDtoRequest() {
		
	}
	
	public ContaDtoRequest(String nome, Double saldoConta) {
		// TODO Auto-generated constructor stub
		setNomeCompleto(nome);
		setSaldo(saldoConta);
	}
	
	public ContaDtoRequest (String nome, String cpf, String tel, String end, Double salario, Double saldo) {
		setNomeCompleto(nome);
		setCpf(cpf);
		setTelefone(tel);
		setEndereco(end);
		setSalario(salario);
		setSaldo(saldo);
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	

}
