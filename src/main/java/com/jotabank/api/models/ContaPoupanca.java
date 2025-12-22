package com.jotabank.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="tab_contaPoupanca")
public class ContaPoupanca extends Conta{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idContaPoupanca;

}
