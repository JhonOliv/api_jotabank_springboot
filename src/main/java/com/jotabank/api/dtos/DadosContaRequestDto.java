package com.jotabank.api.dtos;

public class DadosContaRequestDto {
	
	private Long idConta;
	private Double saldoConta;
	
	DadosContaRequestDto(Long idConta, Double saldoConta){
		setIdConta(idConta);
		setSaldoConta(saldoConta);
	}
	public Long getIdConta() {
		return idConta;
	}
	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
	public Double getSaldoConta() {
		return saldoConta;
	}
	public void setSaldoConta(Double saldoConta) {
		this.saldoConta = saldoConta;
	}
	
	

}
