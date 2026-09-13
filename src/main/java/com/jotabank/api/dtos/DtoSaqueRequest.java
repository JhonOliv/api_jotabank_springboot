package com.jotabank.api.dtos;

import java.math.BigDecimal;

public class DtoSaqueRequest {
	
	private  BigDecimal valor;
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
