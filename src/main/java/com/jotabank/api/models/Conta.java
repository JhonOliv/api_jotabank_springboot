package com.jotabank.api.models;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_conta")
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idConta;
	private int numConta  = (int) Math.random() * 10000;
	@Autowired
	private Cliente titular;
	private double saldoConta;
	
	

}
