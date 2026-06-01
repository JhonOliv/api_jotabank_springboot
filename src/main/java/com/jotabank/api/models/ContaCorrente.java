package com.jotabank.api.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jotabank.api.exception.ValidacaoInsercaoExtrato;
import com.jotabank.api.exception.VerificarDadosConta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_contaCorrente")
public class ContaCorrente extends Conta {
	
	public ContaCorrente(Cliente pessoa, double saldo, String password) throws VerificarDadosConta {
		super(pessoa, saldo, password);
		// TODO Auto-generated constructor stub
	}
	
	public ContaCorrente () {
		
	}
	
	@Autowired
	@OneToMany(
			mappedBy = "corrente",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<ExtratoMovimentacao> extratoConta;
	
	public List<ExtratoMovimentacao> getExtrato(){
		return this.extratoConta;
	}
	
	public void setItemExtrato(ExtratoMovimentacao item) throws Exception {
		if(item == null) {
			throw new ValidacaoInsercaoExtrato("Não tem movimentação para registrar no extrato");
		}
		
		this.extratoConta.add(item);	
	}
}
