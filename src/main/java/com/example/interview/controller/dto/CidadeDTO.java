package com.example.interview.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CidadeDTO {
	
	@NotNull 
	@NotEmpty
	private String nome;
	
	@NotNull 
	@NotEmpty
	private String estado;
	

}
