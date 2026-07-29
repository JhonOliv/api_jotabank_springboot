package com.jotabank.api.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DtoTransferRequest {
	

	private final String dataTransacao = String.valueOf(LocalDate.now());
	private BigDecimal valorTranferencia;
	private String cpf;
	private String tel;	
	private int numConta;
	
	public Integer getNumConta() {
		return this.numConta;
	}
	
	public BigDecimal getValorTranferencia() {
		return valorTranferencia;
	}

	public void setValorTranferencia(BigDecimal valorTranferencia) {
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
	
	

}
