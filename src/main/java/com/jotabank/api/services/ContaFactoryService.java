package com.jotabank.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jotabank.api.dtos.ContaDtoResponse;
import com.jotabank.api.dtos.ContaDtoRequest;
import com.jotabank.api.exception.NegativeNumberException;
import com.jotabank.api.exception.ValidacaoDadosPessoa;
import com.jotabank.api.exception.VerificarDadosConta;
import com.jotabank.api.models.Cliente;
import com.jotabank.api.models.Conta;
import com.jotabank.api.models.ContaCorrente;
import com.jotabank.api.models.Role;
import com.jotabank.api.repositories.ContaRepository;
import com.jotabank.api.repositories.PessoaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContaFactoryService implements ContaService {
	@Autowired
	private ContaRepository repositoryConta;
	@Autowired
	private PessoaRepository repositoryCliente;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String testHelloWorld(String nome) {
		return "Hello World my dev " + nome;
	}

	@Transactional
	@Override
	public ContaDtoRequest criarContaCorrente(ContaDtoRequest request)
			throws ValidacaoDadosPessoa, VerificarDadosConta {
		Conta novaConta;
		Conta contaSalva = null;
		
		if (request.getNomeCompleto().isBlank() && request.getCpf().isBlank())
			throw new ValidacaoDadosPessoa("Erro ao inserir os dados pessoais.");
		if (request.getSaldo() <= 0)
			throw new VerificarDadosConta("Saldo Insuficíente !!!");
		if (repositoryConta.getConta(request.getCpf()) != null)
			throw new VerificarDadosConta("Conta já existente, try another one!");

		Cliente titular = new Cliente(request.getNomeCompleto(), request.getCpf(), request.getTelefone(),
				request.getEndereco(), request.getSalario());
		Cliente clienteSalvo = repositoryCliente.save(titular);

		if (request.getRole().toUpperCase().equals(Role.USER)) {
			novaConta = new ContaCorrente(clienteSalvo, request.getSaldo(),
					passwordEncoder.encode(request.getPassword()), Role.USER);
			contaSalva = repositoryConta.save(novaConta);
		}
		if (request.getRole().toUpperCase().equals(Role.ADMIN)) {

			novaConta = new ContaCorrente(clienteSalvo, request.getSaldo(),
					passwordEncoder.encode(request.getPassword()), Role.ADMIN);

			contaSalva = repositoryConta.save(novaConta);

		}
		if (request.getRole().toUpperCase().equals(Role.MANAGER)) {

			novaConta = new ContaCorrente(clienteSalvo, request.getSaldo(),
					passwordEncoder.encode(request.getPassword()), Role.MANAGER);

			contaSalva = repositoryConta.save(novaConta);

		}

		return new ContaDtoRequest(clienteSalvo.getNome(), contaSalva.getSaldoConta());

	}

	@Override
	public ContaDtoResponse getContaPorId(Long idConta) throws NumberFormatException, NegativeNumberException {

		if (idConta.toString().matches("^[\\\\p{L}\\\\s]+$\\r\\n")) {
			throw new NumberFormatException("Aceita somente número!");
		} else if (idConta <= 0) {
			throw new NegativeNumberException("Você digitou um número negativo.");
		}

		Conta conta = repositoryConta.findById(idConta).orElse(null);

		return new ContaDtoResponse(conta.getTitular().getNome(), conta.getTitular().getCpf(),
				conta.getTitular().getTelefone(), conta.getTitular().getEndereco(),
				conta.getTitular().getSalarioCliente(), conta.getSaldoConta());

	}

	@Override
	@Transactional
	public String updateConta(Long id, ContaDtoRequest request) throws VerificarDadosConta, ValidacaoDadosPessoa {
		// TODO Auto-generated method stub

		if (request.getNomeCompleto().matches("^[\\p{L}\\s]+$\r\n") || request.getTelefone().matches("/^\\d+$/\r\n")) {
			throw new ValidacaoDadosPessoa("Dados inseridos incorretamente, entre em contato com seu administrador.");
		}

		Conta conta = repositoryConta.findById(id).orElseThrow(() -> new RuntimeException("Conta Não Encontrada"));

		Cliente clinte = conta.getTitular();

		clinte.setNome(request.getNomeCompleto());
		clinte.setCpf(request.getCpf());
		clinte.setEndereco(request.getEndereco());
		clinte.setTelefone(request.getTelefone());
		clinte.setSalarioCliente(request.getSalario());
		conta.setSaldoConta(request.getSaldo());

		return "Atualizado com sucesso";
	}

	@Override
	@Transactional
	public ContaDtoRequest deletarContaById(Long id) {
		// TODO Auto-generated method stub

		Conta conta = repositoryConta.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontada."));
		repositoryConta.delete(conta);

		return new ContaDtoRequest(conta.getTitular().getNome(), conta.getSaldoConta());

	}

	@Override
	public List<ContaDtoResponse> getAllConta() {

		List<ContaDtoResponse> contaResponse = new ArrayList<ContaDtoResponse>();

		repositoryConta.findAll().stream().forEach(conta -> {
			ContaDtoResponse resConta = new ContaDtoResponse(conta);
			contaResponse.add(resConta);
		});

		return contaResponse;

	}

}
