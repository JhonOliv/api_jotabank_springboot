package com.jotabank.api.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jotabank.api.dtos.HistoricoTransferenciaDTO;
import com.jotabank.api.models.Transferencia;

@Repository
public interface TransferenciaRepository extends CrudRepository<Transferencia, Long> {
	
	
	@Query(value="SELECT NOME, DATA_TRANSACAO, VALOR_TRANFERENCIA, MOVIMENTACAO, CONTA_DESTINO_ID, CONTA_ORIGEM_ID "
			+ "FROM TAB_TRANSFERENCIA AS TRANSF INNER JOIN TAB_CONTA AS CONTA ON  TRANSF.CONTA_ORIGEM_ID = CONTA.ID_CONTA "
			+ "INNER JOIN TAB_PESSOA  AS PESSOA ON CONTA.TITULAR_ID_PESSOA = PESSOA.ID_PESSOA WHERE PESSOA.CPF = :cpf "
			+ "ORDER BY TRANSF.DATA_TRANSACAO DESC", nativeQuery = true)
	HistoricoTransferenciaDTO historicoTransferencia (@Param("cpf") String cpf);

}
