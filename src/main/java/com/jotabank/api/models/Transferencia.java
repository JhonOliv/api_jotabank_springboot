package com.jotabank.api.models;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name="tab_transferencia")

public class Transferencia {

	public Transferencia(BigDecimal valorTransferencia, TipoTransacao tipo) throws ValidacaoInsercaoTransferencia {
		// TODO Auto-generated constructor stub
		setValorTransacao(valorTransferencia);
		setTipoTrasacao(tipo);
		
	}
	
	public Transferencia() {}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTransferencia;
	@Column(length = 15, nullable = false)
	private final String dataTransacao = String.valueOf(LocalDate.now());
	@Column(nullable = false)
	private BigDecimal valorTranferencia;
	@Column(length = 15, nullable = false)
	private TipoTransacao tipoTransferencia;
	@Column(length = 10, nullable = false)
	private TipoMovimentacao movimentacao;
	@ManyToOne
	@JoinColumn(name = "conta_origem_id")
	private Conta origem;
	@ManyToOne
	@JoinColumn(name = "conta_destino_id")
	private Conta destino;
	
	
	
	public TipoMovimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(TipoMovimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Long getIdTransferencia() {
		return this.idTransferencia;
	}
	
	public String getDataTransacao() {
		return this.dataTransacao;
	}
	
	public BigDecimal getValorTransacao() {
		return this.valorTranferencia;
	}
	
	public void setValorTransacao(BigDecimal valor) {
		this.valorTranferencia = valor;
	}
	
	public TipoTransacao getTipoTransacao() {
		return this.tipoTransferencia;
	}
	
	public void setTipoTrasacao(TipoTransacao tipo) {
		this.tipoTransferencia = tipo;
	}

	public Conta getOrigem() {
		return origem;
	}

	public void setOrigem(Conta origem) {
		this.origem = origem;
	}

	public Conta getDestino() {
		return destino;
	}

	public void setDestino(Conta destino) {
		this.destino = destino;
	}



	
}
