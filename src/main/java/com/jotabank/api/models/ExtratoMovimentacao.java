package com.jotabank.api.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_extrato")

public class ExtratoMovimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idExtrato;
	
	@Column(nullable = false)
	@ManyToOne
	private Pessoa titular;
	@Column( length = 10, nullable = false)
	private TipoTransacao tipo;
	@Column(length = 15, nullable = false)
	private String dateTransacao = String.valueOf(LocalDate.now());
	@Column(length = 15, nullable = false)
	private double valor;
	
	public void setPessoa(Pessoa titular) {
		this.titular = titular;
	}
	
	public Pessoa getPessoa() {
		return this.titular;
	}
	
	public void setTipoTransacao(TipoTransacao tipo) {
		this.tipo = tipo;
	}
	
	public double getValor() {
		return this.valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
	
	
	
	

}
