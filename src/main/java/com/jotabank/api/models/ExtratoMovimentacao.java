package com.jotabank.api.models;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jotabank.api.exception.ValidacaoInsercaoTransferencia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_extrato")

public class ExtratoMovimentacao {
	
	
	public ExtratoMovimentacao(TipoTransacao tipo, String date, double valor, ContaCorrente origem, ContaCorrente destino) throws ValidacaoInsercaoTransferencia {
		// TODO Auto-generated constructor stub
		if(tipo != null && date != null && valor > 0) {
			setTipoTransacao(tipo);
			setValor(valor);
			setCorrente(origem);
			setDestino(destino);
		}else {
			throw new ValidacaoInsercaoTransferencia("Dados enviados estão incorretos verifique eles e tente novamente!");
		}
	}
	
	public ExtratoMovimentacao() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idExtrato;
	@Column( length = 10, nullable = false)
	private TipoTransacao tipo;
	@Column(length = 15, nullable = false)
	private String dateTransacao = String.valueOf(LocalDate.now());
	@Column(length = 15, nullable = false)
	private double valor;
	@Autowired
	@ManyToOne
	@JoinColumn(name="conta_corrente_id")
	@JsonIgnore
	private ContaCorrente  corrente;
	@Autowired
	@ManyToOne
	@JoinColumn(name="contaDestino")
	@JsonIgnore
	private ContaCorrente  destino;
	
	
	
	public void setTipoTransacao(TipoTransacao tipo) {
		this.tipo = tipo;
	}
	
	public double getValor() {
		return this.valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	public ContaCorrente getCorrente() {
		return corrente;
	}

	public void setCorrente(ContaCorrente corrente) {
		this.corrente = corrente;
	}

	public ContaCorrente getDestino() {
		return destino;
	}

	public void setDestino(ContaCorrente destino) {
		this.destino = destino;
	}
	
	
		

}
