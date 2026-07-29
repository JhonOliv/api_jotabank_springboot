package com.jotabank.api.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.jotabank.api.exception.ValidacaoInsercaoTransferencia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_extrato")

public class ExtratoMovimentacao {
	
	
	public ExtratoMovimentacao(TipoTransacao tipo, BigDecimal valor) throws ValidacaoInsercaoTransferencia {
		// TODO Auto-generated constructor stub
		if(tipo != null && valor.intValue() > 0) {
			setTipoTransacao(tipo);
			setValor(valor);
		}else {
			throw new ValidacaoInsercaoTransferencia("Dados enviados estão incorretos verifique eles e tente novamente!");
		}
	}
	
	public ExtratoMovimentacao() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idExtrato;
	@Column( length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipo;
	@Column(length = 15, nullable = false)
	private String dateTransacao = String.valueOf(LocalDate.now());
	@Column(length = 15, nullable = false)
	private BigDecimal valor;
	
	
	
	
	
	
	public void setTipoTransacao(TipoTransacao tipo) {
		this.tipo = tipo;
	}
	
	public BigDecimal getValor() {
		return this.valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}



	
		

}
