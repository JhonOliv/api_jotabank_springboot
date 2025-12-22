package com.jotabank.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_extrato")

public class ExtratoMovimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idExtrato;

}
