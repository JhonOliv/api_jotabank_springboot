package com.jotabank.api.models;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import com.jotabank.api.exception.ValidacaoInsercaoExtrato;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idConta;
	@Column(length = 50, nullable = false)	
	private final int numConta  = (int) Math.round(Math.random() * 10000);
	@Column(length = 100, nullable = false)
	private String password;
	@Autowired
	@ManyToOne
	private Cliente titular;
	@Column(length = 50, nullable = false)	
	private double saldoConta;
	@Column(length = 50, nullable = false)	
	private ArrayList<ExtratoMovimentacao> extratoConta;
	@Column(length = 50, nullable = false)	
	private ArrayList<Transferencia> minhasTransferencias;
	
	public Long getIdConta() {
		return this.idConta;
	}
	
	public int getNumConta() {
		return this.numConta;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	public double getSaldoConta() {
		return this.saldoConta;
	}
	
	public void setSaldoConta(double saldo) {
		this.saldoConta = saldo;
	}
	
	public ArrayList<ExtratoMovimentacao> getExtrato(){
		return this.extratoConta;
	}
	
	public void setItemExtrato(ExtratoMovimentacao item) throws Exception {
		if(item == null) {
			throw new ValidacaoInsercaoExtrato("Não tem movimentação para registrar no extrato");
		}
		
		this.extratoConta.add(item);			
	}
	
	public ArrayList<Transferencia> geTransferencia(){
		return this.minhasTransferencias;
	}
	
	public void setItemTransferencia(Transferencia item) throws Exception {
		if(item == null) {
			throw new ValidacaoInsercaoExtrato("Não tem transferência para registrar em Transferência!");
		}	
		this.minhasTransferencias.add(item);			
	}
	

}
