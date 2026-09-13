package com.jotabank.api.dtos;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public record TransferDtoResponse(
		int numConta,
		String nomeTitular, 
		String cpf) {
	
	
	

}
