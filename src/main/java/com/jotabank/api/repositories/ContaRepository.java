package com.jotabank.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jotabank.api.dtos.DadosContaRequestDto;
import com.jotabank.api.models.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	
	@Query(value = "SELECT CONTA.ID_CONTA, CONTA.SALDO_CONTA FROM TAB_CONTA AS CONTA INNER JOIN TAB_PESSOA AS CLI ON CONTA.TITULAR_ID_PESSOA = CLI.ID_PESSOA"
			+ " WHERE CLI.CPF = :cpf", nativeQuery = true)
	DadosContaRequestDto getConta(@Param("cpf") String cpf);
	
	
	@Query(value = "SELECT * FROM TAB_CONTA AS CONTA INNER JOIN TAB_PESSOA AS CLI ON CONTA.TITULAR_ID_PESSOA = CLI.ID_PESSOA"
			+ " WHERE CLI.CPF = :cpf", nativeQuery = true)
	Optional<Conta> findContaByCpf (@Param("cpf") String cpf);
		

}
