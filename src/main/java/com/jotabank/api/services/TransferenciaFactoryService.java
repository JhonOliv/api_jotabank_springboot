package com.jotabank.api.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotabank.api.dtos.DadosContaRequestDto;
import com.jotabank.api.dtos.DtoTransferResponse;
import com.jotabank.api.dtos.HistoricoTransferenciaDTO;
import com.jotabank.api.exception.NegativeNumberException;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.models.Conta;
import com.jotabank.api.models.ExtratoMovimentacao;
import com.jotabank.api.models.TipoMovimentacao;
import com.jotabank.api.models.TipoTransacao;
import com.jotabank.api.models.Transferencia;
import com.jotabank.api.repositories.ContaRepository;
import com.jotabank.api.repositories.ExtratoMovimentacaoRepository;
import com.jotabank.api.repositories.TransferenciaRepository;

import jakarta.transaction.Transactional;

@Service
public class TransferenciaFactoryService implements TransferenciaService {
	@Autowired
	private TransferenciaRepository repositoryTransf;
	@Autowired
	private ContaRepository repositoryConta;
	@Autowired
	private ExtratoMovimentacaoRepository repositoryExtrato;

	@Transactional
	@Override
	public DtoTransferResponse transfePix(Long id, String cpf, BigDecimal valor) throws Exception  {
		// TODO Auto-generated method stub
			Conta origem = repositoryConta.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontardo."));
			
			DtoTransferResponse response = new DtoTransferResponse();
		
			if(Double.toString(valor.doubleValue()).matches("\"/^\\\\d+$/\\r\\n\"")) {
				throw new NegativeNumberException("Informação inserida não é compatível com o esperado revise a sua solicitação!");
			}else if (origem.getSaldoConta().doubleValue() >= valor.doubleValue()) {
				
				DadosContaRequestDto destino = repositoryConta.getConta(cpf);
				Conta destinoTransf = repositoryConta.findById(destino.getIdConta()).orElseThrow(() -> new RuntimeException("Erros ao buscar dados da conta"));
				
				Transferencia transfOrigem = new Transferencia(valor, TipoTransacao.Pix);
				transfOrigem.setOrigem(origem);
				transfOrigem.setDestino(destinoTransf);
				transfOrigem.setMovimentacao(TipoMovimentacao.Saida);
				Transferencia transfDestino = new Transferencia(valor, TipoTransacao.Pix);
				transfDestino.setDestino(destinoTransf);
				transfDestino.setOrigem(origem);
				transfDestino.setMovimentacao(TipoMovimentacao.Entrada);
				
				ExtratoMovimentacao extrato = new ExtratoMovimentacao(TipoTransacao.Pix, origem.getTitular().getCpf(), valor, origem, destinoTransf);
				
				destinoTransf.setSaldoConta(destino.getSaldoConta() + valor.doubleValue());
				origem.setSaldoConta(origem.getSaldoConta().doubleValue() - valor.doubleValue());
				
				response.setTipoTrasacao(TipoTransacao.Pix);
				response.setDestino(destinoTransf);
				response.setOrigem(origem);
				response.setValorTransacao(valor.doubleValue());
				
				repositoryExtrato.save(extrato);
				repositoryConta.save(destinoTransf);
				repositoryConta.save(origem);
				repositoryTransf.save(transfOrigem);
				repositoryTransf.save(transfDestino);
				
				
				return response; 
					
			}else {				
				return response;
			}
			
	}

	@Override
	public DtoTransferResponse transfeTed(Long id, Integer numConta, double valor) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public void transfeDoc(Conta titular, Conta destino, double valor) {
		// TODO Auto-generated method stub
	}

	@Override
	public void transfeSaque(Conta conta, double saldo) {
		// TODO Auto-generated method stub
	}

	@Override
	public void transfeDeposito(Conta conta, double deposito) {
		// TODO Auto-generated method stub
	}

	@Override
	public HistoricoTransferenciaDTO getHistoricoTransf(String cpf) throws ValidacaoDadosPessoa {
		// TODO Auto-generated method stub
		
		if(!cpf.matches("^[0-9]+$\\")) {
			throw new ValidacaoDadosPessoa("O campo CPF só pode aceitar valores númericos.");
		}
		
		HistoricoTransferenciaDTO histTrans = repositoryTransf.historicoTransferencia(cpf);
		return histTrans;
	}
	
	

}
