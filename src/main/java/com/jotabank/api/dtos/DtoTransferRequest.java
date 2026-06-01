package com.jotabank.api.dtos;

import java.time.LocalDate;

import com.jotabank.api.models.TipoTransacao;

public class DtoTransferRequest {
	

	private final String dataTransacao = String.valueOf(LocalDate.now());
	private double valorTranferencia;
	private String cpf;
	private String tel;	
	
	
	public double getValorTranferencia() {
		return valorTranferencia;
	}

	public void setValorTranferencia(double valorTranferencia) {
		this.valorTranferencia = valorTranferencia;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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
	
	

}
