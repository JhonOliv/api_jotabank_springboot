package com.jotabank.api.dtos;

import java.time.LocalDate;

public record HistoricoTransferenciaDTO( 
 String nome,
 LocalDate dataTransferencia,
 Double valorTransferencia
 ) {}
