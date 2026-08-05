package com.jotabank.api.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jotabank.api.dtos.DadosContaRequestDto;
import com.jotabank.api.dtos.DtoSaqueResponse;
import com.jotabank.api.dtos.DtoTransferResponse;
import com.jotabank.api.dtos.HistoricoTransferenciaDTO;
import com.jotabank.api.exception.NegativeNumberException;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.ValidacaoInsercaoTransferencia;
import com.jotabank.api.models.Conta;
import com.jotabank.api.models.ExtratoMovimentacao;
import com.jotabank.api.models.TipoMovimentacao;
import com.jotabank.api.models.TipoTransacao;
import com.jotabank.api.models.Transferencia;
import com.jotabank.api.repositories.ContaRepository;
import com.jotabank.api.repositories.ExtratoMovimentacaoRepository;
import com.jotabank.api.repositories.TransferenciaRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferenciaFactoryService implements TransferenciaService {
	
	private TransferenciaRepository repositoryTransf;
	private ContaRepository repositoryConta;
	private ExtratoMovimentacaoRepository repositoryExtrato;

	@Transactional
	@Override
	public DtoTransferResponse transfePix(Long id, String cpf, BigDecimal valor) throws Exception {
		// TODO Auto-generated method stub
		Conta origem = repositoryConta.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontardo."));

		DadosContaRequestDto destino = repositoryConta.getConta(cpf);
		Conta destinoTransf = repositoryConta.findById(destino.getIdConta())
				.orElseThrow(() -> new RuntimeException("Erros ao buscar dados da conta"));
		DtoTransferResponse response = new DtoTransferResponse();

		// Guard Clauses 
		if (origem.getTitular().getCpf().equals(destinoTransf.getTitular().getCpf())) 
			throw new ValidationException("Dados da Origem são as mesma de Destino");
		if (Double.toString(valor.doubleValue()).matches("\"/^\\\\d+$/\\r\\n\"")) 
			throw new NegativeNumberException(
			"Informação inserida não é compatível com o esperado revise a sua solicitação!"); 
		
		if (origem.getSaldoConta().compareTo(valor.doubleValue()) >= 0) {

			Transferencia transfOrigem = new Transferencia(valor, TipoTransacao.Pix);
			transfOrigem.setConta(origem);
			transfOrigem.setMovimentacao(TipoMovimentacao.Saida);

			Transferencia transfDestino = new Transferencia(valor, TipoTransacao.Pix);
			transfDestino.setConta(destinoTransf);
			transfDestino.setMovimentacao(TipoMovimentacao.Entrada);

			ExtratoMovimentacao extratoOrigem = new ExtratoMovimentacao(TipoTransacao.Pix, valor);
			ExtratoMovimentacao extratoDestino = new ExtratoMovimentacao(TipoTransacao.Pix, valor);

			destinoTransf.setSaldoConta(destino.getSaldoConta() + valor.doubleValue());
			origem.setSaldoConta(origem.getSaldoConta().doubleValue() - valor.doubleValue());

			repositoryTransf.save(transfOrigem);
			repositoryTransf.save(transfDestino);
			
			
			response.setTipoTrasacao(TipoTransacao.Pix);
			response.setDestino(destinoTransf);
			response.setOrigem(origem);
			response.setValorTransacao(valor.doubleValue());

			repositoryExtrato.save(extratoOrigem);
			repositoryExtrato.save(extratoDestino);
			repositoryConta.save(destinoTransf);
			repositoryConta.save(origem);

			return response;

		} else {
			return response;
		}

	}

	@Transactional
	@Override
	public DtoTransferResponse transfeTed(Long id, int numConta, String cpf, BigDecimal valor) throws ValidacaoInsercaoTransferencia {
		
		Conta origem = repositoryConta.findById(id).orElseThrow(() -> new RuntimeException("Erro ao buscar Conta"));
		DadosContaRequestDto destinoRef = repositoryConta.getConta(cpf);
		
		Conta destino = repositoryConta.findById(destinoRef.getIdConta()).orElseThrow(() ->
		new RuntimeException("Conta de destino não encontrada!"));
		
		if(origem == null || destino == null) {
			return new DtoTransferResponse();
		}
		
		origem.setSaldoConta(origem.getSaldoConta() - valor.doubleValue());
		destino.setSaldoConta(destino.getSaldoConta() + valor.doubleValue());
		
		Transferencia transfOrigem = new Transferencia();
		Transferencia transfDestino = new Transferencia();
		
		transfOrigem.setConta(origem);
		transfOrigem.setMovimentacao(TipoMovimentacao.Saida);
		transfOrigem.setTipoTrasacao(TipoTransacao.Ted);
		transfOrigem.setValorTransacao(valor);
				
		transfDestino.setConta(destino);
		transfDestino.setMovimentacao(TipoMovimentacao.Entrada);
		transfDestino.setTipoTrasacao(TipoTransacao.Ted);
		transfDestino.setValorTransacao(valor);
		
		ExtratoMovimentacao extratoOrigem = new ExtratoMovimentacao(TipoTransacao.Ted, valor);
		ExtratoMovimentacao extratoDestino = new ExtratoMovimentacao(TipoTransacao.Ted, valor);
		
		repositoryTransf.save(transfOrigem);
		repositoryTransf.save(transfDestino);
		repositoryExtrato.save(extratoOrigem);
		repositoryExtrato.save(extratoDestino);
		repositoryConta.save(origem);
		repositoryConta.save(destino);
		
		DtoTransferResponse response = new DtoTransferResponse();
		response.setDestino(destino);
		response.setOrigem(origem);
		response.setTipoTrasacao(TipoTransacao.Ted);
		response.setValorTransacao(valor.doubleValue());
		
		return response;
	}

	@Override
	public DtoSaqueResponse transfeSaque(double valor, Long idConta) {
		// TODO Auto-generated method stub
		
		Conta conta = repositoryConta.findById(idConta).orElseThrow(() -> new RuntimeException(
				"Conta nâo encontrada."));
		
		if(conta.getSaldoConta() >= valor) {
			conta.setSaldoConta(conta.getSaldoConta() - valor);			
			repositoryConta.save(conta);
			DtoSaqueResponse saque = new DtoSaqueResponse();
			saque.setValor(conta.getSaldoConta());
			saque.setMsg("Saque Realizado com sucesso.");
			return saque;
		}else {
			
			DtoSaqueResponse response = new DtoSaqueResponse();
			response.setValor(0);
			response.setMsg("Saque indisponível por falta de recursos financeiros.");
			return response;
		}
		
	}

	@Override
	public DtoSaqueResponse transfeDeposito(double deposito, Long idConta) {
		// TODO Auto-generated method stub
		Conta conta = repositoryConta.findById(idConta).orElseThrow(() -> new RuntimeException(
				"Conta nâo encontrada."));
		
		if(deposito > 0) {
			conta.setSaldoConta(conta.getSaldoConta() + deposito);			
			repositoryConta.save(conta);
			
			DtoSaqueResponse dep = new DtoSaqueResponse();
			dep.setValor(conta.getSaldoConta());
			dep.setMsg("Saque Realizado com sucesso.");
			return dep;
			
		}else {
			
			DtoSaqueResponse response = new DtoSaqueResponse();
			response.setValor(0);
			response.setMsg("Saque indisponível por falta de recursos financeiros.");
			return response;
		}	
	}
	
	@Override
	public List<HistoricoTransferenciaDTO> getHistoricoTransf(String cpf) throws ValidacaoDadosPessoa {
		// TODO Auto-generated method stub
		if(!cpf.isEmpty()) {
			
			List<HistoricoTransferenciaDTO> histTrans = repositoryTransf.buscarHistoricoPorCpf(cpf);
			return histTrans;
		}
		
		return null;
	}


}
