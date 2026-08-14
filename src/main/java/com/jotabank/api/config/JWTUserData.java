package com.jotabank.api.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public record JWTUserData(Long id_Conta, String username) {
	

}
