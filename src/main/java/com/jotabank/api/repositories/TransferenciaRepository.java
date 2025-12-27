package com.jotabank.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.jotabank.api.models.Transferencia;

@Repository
public interface TransferenciaRepository extends CrudRepository<Transferencia, Long> {

}
