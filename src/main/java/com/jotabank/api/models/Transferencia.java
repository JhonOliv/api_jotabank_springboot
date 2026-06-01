package com.jotabank.api.models;

import java.time.LocalDate;

import com.jotabank.api.exception.ValidacaoInsercaoTransferencia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_transferencia")

public class Transferencia {

	public Transferencia(double valorTransferencia, ContaCorrente origem, ContaCorrente destino, TipoTransacao tipo) throws ValidacaoInsercaoTransferencia {
		// TODO Auto-generated constructor stub
		setValorTransacao(valorTransferencia);
		setTipoTrasacao(tipo);
		setOrigem(origem);
		setDestino(destino);
	}
	
	public Transferencia() {}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTransferencia;
	@Column(length = 15, nullable = false)
	private final String dataTransacao = String.valueOf(LocalDate.now());
	@Column(nullable = false)
	private double valorTranferencia;
	@Column(length = 15, nullable = false)
	private TipoTransacao tipoTransferencia;
	@OneToOne
	private ContaCorrente destino;
	@OneToOne
	private ContaCorrente origem;
	
	public Long getIdTransferencia() {
		return this.idTransferencia;
	}
	
	public String getDataTransacao() {
		return this.dataTransacao;
	}
	
	public double getValorTransacao() {
		return this.valorTranferencia;
	}
	
	public void setValorTransacao(double valor) {
		this.valorTranferencia = valor;
	}
	
	public TipoTransacao getTipoTransacao() {
		return this.tipoTransferencia;
	}
	
	public void setTipoTrasacao(TipoTransacao tipo) {
		this.tipoTransferencia = tipo;
	}

	public ContaCorrente getDestino() {
		return destino;
	}

	public void setDestino(ContaCorrente destino) {
		this.destino = destino;
	}

	public ContaCorrente getOrigem() {
		return origem;
	}

	public void setOrigem(ContaCorrente origem) {
		this.origem = origem;
	}
	
}
