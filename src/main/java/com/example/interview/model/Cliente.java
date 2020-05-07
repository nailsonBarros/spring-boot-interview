package com.example.interview.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@NoArgsConstructor
public class Cliente {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
	
	@Column
	private String nomeCompleto;
	
	@Column
	private String genero;
	
	@Column
	private LocalDate dataNascimento;
	
	@Column
	private Long idade;
	
	@ManyToOne
	private Cidade cidade;


}
