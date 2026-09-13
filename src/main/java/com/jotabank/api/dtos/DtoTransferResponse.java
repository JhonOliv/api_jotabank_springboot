package com.jotabank.api.dtos;

import java.time.LocalDate;

import com.jotabank.api.models.Conta;
import com.jotabank.api.models.TipoTransacao;

public class DtoTransferResponse {
	
	private final String dataTransacao = String.valueOf(LocalDate.now());
	private double valorTranferencia;
	private TipoTransacao tipoTransferencia;
	private TransferDtoResponse origem;
	private TransferDtoResponse destino;
	
	public TransferDtoResponse getOrigem() {
		return this.origem;
	}

	public void setOrigem(Conta origem) {
		
		this.origem = new TransferDtoResponse(
				origem.getNumConta(), origem.getTitular().getNome(),
				origem.getTitular().getCpf());
		
	}

	public TransferDtoResponse getDestino() {
		return destino;
	}

	public void setDestino(Conta destino) {
		this.destino = new TransferDtoResponse(
				destino.getNumConta(), destino.getTitular().getNome(),
				destino.getTitular().getCpf());
	}

	public String getDataTransacao() {
		return this.dataTransacao;
	}
	
	public double getValorTransacao() {
		return this.valorTranferencia;
	}
	
	public void setValorTransacao(Double valor) {
		this.valorTranferencia = valor;
	}
	
	public TipoTransacao getTipoTransacao() {
		return this.tipoTransferencia;
	}
	
	public void setTipoTrasacao(TipoTransacao tipo) {
		this.tipoTransferencia = tipo;
	}

}
