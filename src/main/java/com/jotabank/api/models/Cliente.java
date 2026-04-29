package com.jotabank.api.models;

import com.jotabank.api.exception.ValidacaoDadosPessoa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_cliente")
public class Cliente extends Pessoa {

	public Cliente(String nome, String cpf, String telefone, String endereco, Double salario) throws ValidacaoDadosPessoa {
		super(nome, cpf, telefone, endereco);
		// TODO Auto-generated constructor stub
		setSalarioCliente(salario);
	}
	
	@Column(length = 15, nullable = false)
	private double salarioCliente;
	
	public double getSalarioCliente() {
		return salarioCliente;
	}

	public void setSalarioCliente(Double salarioCliente) {
		this.salarioCliente = salarioCliente;
	}

	@Override
	public String toString() {
		return "Cliente [ getNome()=" + getNome() + "salarioCliente=" + salarioCliente +  "getCpf()=" + getCpf()
				+ ", getTelefone()=" + getTelefone() + "]";
	}

	
	

	
}
