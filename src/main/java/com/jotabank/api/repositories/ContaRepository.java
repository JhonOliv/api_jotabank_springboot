package com.jotabank.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.jotabank.api.models.Conta;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Long> {

}
