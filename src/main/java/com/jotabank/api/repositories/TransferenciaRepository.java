package com.jotabank.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jotabank.api.dtos.HistoricoTransferenciaDTO;
import com.jotabank.api.models.Transferencia;

@Repository
public interface TransferenciaRepository extends CrudRepository<Transferencia, Long> {
	
	
	@Query(value="SELECT NOME, VALOR_TRANFERENCIA, MOVIMENTACAO, "
			+ "CAST(TRANS.DATA_TRANSACAO AS VARCHAR) AS dataTransacao\r\n FROM TAB_TRANSFERENCIA AS TRANS \r\n"
			+ "INNER JOIN TAB_CONTA AS CONTA ON TRANS.CONTA = CONTA.ID_CONTA \r\n"
			+ "INNER JOIN TAB_PESSOA AS PESSOA ON CONTA.TITULAR_ID_PESSOA = PESSOA.ID_PESSOA \r\n"
			+ "WHERE PESSOA.CPF = :cpf", nativeQuery = true)
	
	List<HistoricoTransferenciaDTO> buscarHistoricoPorCpf (@Param("cpf") String cpf);

}