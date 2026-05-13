package com.jotabank.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jotabank.api.models.ContaCorrente;

@Repository
public interface ContaRepository extends JpaRepository<ContaCorrente, Long> {
	

}
