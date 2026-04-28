package com.jotabank.api.models;

import java.time.LocalDate;

import com.jotabank.api.exception.ValidacaoInsercaoTransferencia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_extrato")

public class ExtratoMovimentacao {
	
	
	public ExtratoMovimentacao(Cliente titular, TipoTransacao tipo, String date, double valor) throws ValidacaoInsercaoTransferencia {
		// TODO Auto-generated constructor stub
		if(titular != null && tipo != null && date != null && valor > 0) {
			setTipoTransacao(tipo);
			setValor(valor);			
		}else {
			throw new ValidacaoInsercaoTransferencia("Dados enviados estão incorretos verifique eles e tente novamente!");
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idExtrato;
	
	@Column( length = 10, nullable = false)
	private TipoTransacao tipo;
	@Column(length = 15, nullable = false)
	private String dateTransacao = String.valueOf(LocalDate.now());
	@Column(length = 15, nullable = false)
	private double valor;
	
	
	public void setTipoTransacao(TipoTransacao tipo) {
		this.tipo = tipo;
	}
	
	public double getValor() {
		return this.valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
		

}
