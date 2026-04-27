package com.jotabank.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.jotabank.api.models.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
