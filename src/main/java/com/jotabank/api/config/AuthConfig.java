package com.jotabank.api.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jotabank.api.repositories.ContaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthConfig implements UserDetailsService {
	
	private ContaRepository repositoryConta;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repositoryConta.findContaByCpf(username).orElseThrow(() -> new RuntimeException("Conta não encontrada")); 
		
	}
	
	

}
