package com.jotabank.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public record ContaDtoLoginUser(@NotBlank(message = "Cpf é obrigatório") String cpf,
		@NotBlank(message = "Password é obrigatório") String password) {

}
