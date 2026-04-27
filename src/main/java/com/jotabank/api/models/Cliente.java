package com.jotabank.api.models;

import com.jotabank.api.exception.ValidacaoDadosPessoa;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_cliente")
@MappedSuperclass
public class Cliente extends Pessoa {

	public Cliente(String nome, String cpf, String telefone, String endereco, double salario) throws ValidacaoDadosPessoa {
		super(nome, cpf, telefone, endereco);
		// TODO Auto-generated constructor stub
		setSalarioCliente(salario);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 15, nullable = false)
	private double salarioCliente;
	
	public double getSalarioCliente() {
		return salarioCliente;
	}

	public void setSalarioCliente(double salarioCliente) {
		this.salarioCliente = salarioCliente;
	}

	@Override
	public String toString() {
		return "Cliente [ getNome()=" + getNome() + "salarioCliente=" + salarioCliente +  "getCpf()=" + getCpf()
				+ ", getTelefone()=" + getTelefone() + "]";
	}

	
	

	
}
