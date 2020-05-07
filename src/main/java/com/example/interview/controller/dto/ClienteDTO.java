package com.example.interview.controller.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {
	
	@NotNull 
	@NotEmpty
	private String nomeCompleto;
	
	@NotNull 
	@NotEmpty
	private String genero;
	
	@NotNull
	private LocalDate dataNascimento;
	
	@NotNull
	private Long idade;
	
	private CidadeDTO cidade;

}
