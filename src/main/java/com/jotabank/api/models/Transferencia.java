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

	public Transferencia(double valorTransferencia, Pessoa origem, Pessoa destino, TipoTransacao tipo) throws ValidacaoInsercaoTransferencia {
		// TODO Auto-generated constructor stub
		setValorTransacao(valorTransferencia);
		setOrigem(origem);
		setDestino(destino);
		setTipoTrasacao(tipo);

	}
	
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
	
	public Long getIdTransferencia() {
		return this.idTransferencia;
	}
	
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
	
	public Pessoa getOrigen() {
		return this.origem;
	}
	
	public void setOrigem(Pessoa origem) {
		this.origem = origem;
	}
	
	public Pessoa getDestino() {
		return this.destino;
	}
	
	public void setDestino(Pessoa destino) {
		this.destino = destino;
	}
	
	public TipoTransacao getTipoTransacao() {
		return this.tipoTransferencia;
	}
	
	public void setTipoTrasacao(TipoTransacao tipo) {
		this.tipoTransferencia = tipo;
	}
	
}
