package com.jotabank.api.dtos;

import java.time.LocalDate;

import com.jotabank.api.models.ContaCorrente;
import com.jotabank.api.models.TipoTransacao;

public class DtoTransferResponse {
	
	private final String dataTransacao = String.valueOf(LocalDate.now());
	private double valorTranferencia;
	private TipoTransacao tipoTransferencia;
	private ContaDtoResponse destino;
	private ContaDtoResponse origem;
	
	
	
	public ContaDtoResponse getOrigem() {
		return this.origem;
	}

	public void setOrigem(ContaCorrente origem) {
		ContaDtoResponse dtoRequest = new ContaDtoResponse();
		dtoRequest.setNomeCompleto(origem.getTitular().getNome());
		dtoRequest.setSaldo(origem.getSaldoConta());
		dtoRequest.setContaNumber(origem.getNumConta());

		this.origem = dtoRequest;
	}

	public ContaDtoResponse getDestino() {
		return destino;
	}

	public void setDestino(ContaCorrente destino) {
		ContaDtoResponse dtoRequest = new ContaDtoResponse();
		dtoRequest.setNomeCompleto(destino.getTitular().getNome());
		dtoRequest.setSaldo(destino.getSaldoConta());
		dtoRequest.setContaNumber(destino.getNumConta());
		this.destino = dtoRequest;
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
