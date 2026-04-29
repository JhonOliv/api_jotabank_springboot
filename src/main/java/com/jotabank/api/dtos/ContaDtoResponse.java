package com.jotabank.api.dtos;

import com.jotabank.api.models.Cliente;
import com.jotabank.api.models.ExtratoMovimentacao;
import com.jotabank.api.models.Transferencia;

import java.util.List;


public class ContaDtoResponse {
	
	private int numConta;
	private Cliente titular;
	private Double saldo;
	private List<ExtratoMovimentacao> extrato;
	private List<Transferencia> transferencia;
	
	public int getNumConta() {
		return numConta;
	}
	public void setNumConta(int numConta) {
		this.numConta = numConta;
	}
	public Cliente getTitular() {
		return titular;
	}
	public void setTitular(Cliente titular) {
		this.titular = titular;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public List<ExtratoMovimentacao> getExtrato() {
		return extrato;
	}
	public void setExtrato(List<ExtratoMovimentacao> extrato) {
		this.extrato = extrato;
	}
	public List<Transferencia> getTransferencia() {
		return transferencia;
	}
	public void setTransferencia(List<Transferencia> transferencia) {
		this.transferencia = transferencia;
	}
	
	

}
