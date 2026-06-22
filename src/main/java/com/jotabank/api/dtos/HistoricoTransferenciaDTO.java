package com.jotabank.api.dtos;

import java.math.BigDecimal;

public class HistoricoTransferenciaDTO {
	
		
	    private String nome;
	    private BigDecimal valorTransferencia;
	    private String movimentacao;
	    private String dataTransacao;
	    
	    public HistoricoTransferenciaDTO() {}

	    public HistoricoTransferenciaDTO(String nome, BigDecimal valorTransacao, String movimentacao, String data) {
	        this.nome = nome;
	        this.valorTransferencia = valorTransacao;
	        this.movimentacao = movimentacao;
	        this.dataTransacao = data; 
	        
	    }

	    // Getters e Setters
	    public String getNome() { return nome; }
	    public BigDecimal getValorTransferencia() { return valorTransferencia; }
	    public String getMovimentacao() { return movimentacao; }
	    public String getDataTransacao() { return dataTransacao; }
	}



