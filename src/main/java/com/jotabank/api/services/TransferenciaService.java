package com.jotabank.api.services;


import java.math.BigDecimal;
import java.util.List;

import com.jotabank.api.dtos.DtoSaqueResponse;
import com.jotabank.api.dtos.DtoTransferResponse;
import com.jotabank.api.dtos.HistoricoTransferenciaDTO;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.ValidacaoInsercaoTransferencia;

public interface TransferenciaService {
	
	public DtoTransferResponse transfePix(Long id, String cpf, BigDecimal valor) throws Exception;
	public DtoTransferResponse transfeTed(Long id, int numConta, String cpf, BigDecimal valor) throws ValidacaoInsercaoTransferencia;
	public DtoSaqueResponse transfeSaque(double saldo, Long idConta );
	public DtoSaqueResponse transfeDeposito(double deposito, Long idConta);
	public List<HistoricoTransferenciaDTO> getHistoricoTransf(String cpf) throws ValidacaoDadosPessoa;
}
