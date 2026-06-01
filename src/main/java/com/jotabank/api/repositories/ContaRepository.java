package com.jotabank.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jotabank.api.models.ContaCorrente;

@Repository
public interface ContaRepository extends JpaRepository<ContaCorrente, Long> {
	
	@Query(value = "SELECT * FROM TAB_CONTA_CORRENTE AS CORRENTE INNER JOIN TAB_CLIENTE AS CLI ON CORRENTE.TITULAR_ID_PESSOA = CLI.ID_PESSOA "
			+ "WHERE CLI.CPF = :cpf", nativeQuery = true)
	ContaCorrente getConta(@Param("cpf") String cpf);
	

}
