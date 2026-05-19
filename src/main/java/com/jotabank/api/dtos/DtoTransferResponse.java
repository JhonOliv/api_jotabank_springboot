package com.jotabank.api.dtos;

import java.time.LocalDate;

import com.jotabank.api.models.TipoTransacao;

public class DtoTransferResponse {
	
	
	private Long idTransferencia;
	private final String dataTransacao = String.valueOf(LocalDate.now());
	private double valorTranferencia;
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
	
	public void setValorTransacao(double valor) {
		this.valorTranferencia = valor;
	}
	
	public TipoTransacao getTipoTransacao() {
		return this.tipoTransferencia;
	}
	
	public void setTipoTrasacao(TipoTransacao tipo) {
		this.tipoTransferencia = tipo;
	}

}
