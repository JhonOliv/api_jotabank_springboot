package com.jotabank.api.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jotabank.api.exception.ValidacaoInsercaoTransferencia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_extrato")

public class ExtratoMovimentacao {
	
	
	public ExtratoMovimentacao(TipoTransacao tipo, String date, BigDecimal valor, Conta origem, Conta destino) throws ValidacaoInsercaoTransferencia {
		// TODO Auto-generated constructor stub
		if(tipo != null && date != null && valor.intValue() > 0) {
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
	private BigDecimal valor;
	@OneToOne
	private Conta  corrente;
	@OneToOne	
	private Conta  destino;
	
	
	
	
	
	public void setTipoTransacao(TipoTransacao tipo) {
		this.tipo = tipo;
	}
	
	public BigDecimal getValor() {
		return this.valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Conta getCorrente() {
		return corrente;
	}

	public void setCorrente(Conta corrente) {
		this.corrente = corrente;
	}

	public Conta getDestino() {
		return destino;
	}

	public void setDestino(Conta destino) {
		this.destino = destino;
	}
	
	
		

}
