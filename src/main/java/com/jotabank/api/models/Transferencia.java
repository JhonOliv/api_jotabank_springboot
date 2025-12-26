package com.jotabank.api.models;

import java.time.LocalDate;

import com.jotabank.api.exception.ValidacaoInsercaoTransferencia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_transferencia")

public class Transferencia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTransferencia;
	@Column(length = 15, nullable = false)
	private final String dataTransacao = String.valueOf(LocalDate.now());
	@Column(nullable = false)
	private double valorTranferencia;
	@ManyToOne
	@JoinColumn(name = "idOrigem", nullable = false)
	private Pessoa origem;
	@ManyToOne
	@JoinColumn(name = "IdDestino", nullable = false)
	private Pessoa destino;
	@Column(length = 15, nullable = false)
	private TipoTransacao tipoTransferencia;
	
	
	public String getDataTransacao() {
		return this.dataTransacao;
	}
	
	public double getValorTransacao() {
		return this.valorTranferencia;
	}
	
	public void setValorTransacao(double valor) throws ValidacaoInsercaoTransferencia {
		
		if(valor <= 0) {
			throw new ValidacaoInsercaoTransferencia("Valor inserido não é menor ou igual a zero, valide as informações inseridas.");
		}
		
		this.valorTranferencia = valor;
	}
	
	
	
	
	
	
	
}
