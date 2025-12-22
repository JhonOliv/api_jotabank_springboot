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
@Table(name="tab_contaSalario")
public class ContaSalario extends Conta{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idContaSalario;
	
	
}
