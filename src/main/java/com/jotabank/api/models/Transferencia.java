package com.jotabank.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_transferencia")

public class Transferencia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTransferencia;
}
