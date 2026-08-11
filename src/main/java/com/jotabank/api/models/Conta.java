package com.jotabank.api.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jotabank.api.exception.VerificarDadosConta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_Conta")
public abstract class Conta implements UserDetails {

	public Conta() {}
	public Conta(Cliente pessoa, Double saldo, String password) throws VerificarDadosConta {
		if(!pessoa.equals(null) && saldo.intValue() > 0) {
			setTitular(pessoa);
			setSaldoConta(saldo);
			setPassword(password);
		}else {
			throw new VerificarDadosConta("Erro ao criar conta, verifique se os dados estão corretos!");
		}
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConta;
	@Column(length = 50, nullable = false)	
	private final int numConta  = (int) Math.round(Math.random() * 10000);
	@Column(length = 100, nullable = false)
	private String password;
	@ManyToOne
	private Cliente titular;
	@Column(length = 50, nullable = false)	
	private Double saldoConta;
	@Column(length = 50, nullable = false)
	private String Role;
	@Column
	private boolean isAtive = false;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	
	public boolean isEnabled() {
		return true;
	}
	
	public Long getIdConta() {
		return this.idConta;
	}
	
	public int getNumConta() {
		return this.numConta;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public Cliente getTitular() {
		return this.titular;
	}
	
	public void setTitular(Cliente titular) {
		this.titular = titular;
	}
	
	public Double getSaldoConta() {
		return this.saldoConta;
	}
	
	public void setSaldoConta(Double saldo) {
		this.saldoConta = saldo;
	}
	
	
	
}
