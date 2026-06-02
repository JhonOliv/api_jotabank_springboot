package com.jotabank.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotabank.api.dtos.DtoTransferResponse;
import com.jotabank.api.exception.NegativeNumberException;
import com.jotabank.api.exception.ValidacaoInsercaoTransferencia;
import com.jotabank.api.models.Conta;
import com.jotabank.api.models.ContaCorrente;
import com.jotabank.api.models.ExtratoMovimentacao;
import com.jotabank.api.models.TipoTransacao;
import com.jotabank.api.models.Transferencia;
import com.jotabank.api.repositories.ContaRepository;
import com.jotabank.api.repositories.ExtratoMovimentacaoRepository;
import com.jotabank.api.repositories.TransferenciaRepository;

@Service
public class TransferenciaFactoryService implements TransferenciaService {
	@Autowired
	private TransferenciaRepository repositoryTransf;
	@Autowired
	private ContaRepository repositoryConta;
	@Autowired
	private ExtratoMovimentacaoRepository repositoryExtrato;

	@Override
	public DtoTransferResponse transfePix(Long id, String cpf, double valor) throws Exception  {
		// TODO Auto-generated method stub
			ContaCorrente origem = repositoryConta.findById(id).orElse(null);
			DtoTransferResponse response = new DtoTransferResponse();
		
			if(Double.toString(valor).matches("\"/^\\\\d+$/\\r\\n\"")) {
				throw new NegativeNumberException("Informação inserida não é compatível com o esperado revise a sua solicitação!");
			}else if (origem.getSaldoConta() > 0 && origem.getSaldoConta() <= valor ) {
				
				ContaCorrente destino = repositoryConta.getConta(cpf);				
				Transferencia trans = new Transferencia(valor, origem, destino, TipoTransacao.Pix);
				ExtratoMovimentacao extrato = new ExtratoMovimentacao(TipoTransacao.Pix, origem.getTitular().getCpf(), valor, origem, destino);
				destino.setSaldoConta(destino.getSaldoConta() + valor);
				origem.setSaldoConta(origem.getSaldoConta() - valor);
							
				
				response.setTipoTrasacao(TipoTransacao.Pix);
				response.setDestino(destino);
				response.setOrigem(origem);
				response.setValorTransacao(valor);
				
				repositoryExtrato.save(extrato);
				repositoryConta.save(destino);
				repositoryConta.save(origem);
				repositoryTransf.save(trans);
				return response; 
					
			}else {				
				return response;
			}
			
	}

	@Override
	public void transfeTed(Conta titular, Conta destino, double valor) {
		// TODO Auto-generated method stub
		
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
	
	

}
