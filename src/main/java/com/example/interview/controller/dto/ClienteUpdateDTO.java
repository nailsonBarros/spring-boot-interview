package com.example.interview.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteUpdateDTO {
	
	@NotNull
	@NotEmpty
	private String nomeCompleto;

}
