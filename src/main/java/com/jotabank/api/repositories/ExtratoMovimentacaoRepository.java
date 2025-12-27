package com.jotabank.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.jotabank.api.models.ExtratoMovimentacao;

@Repository
public interface ExtratoMovimentacaoRepository extends CrudRepository<ExtratoMovimentacao, Long>{

}
