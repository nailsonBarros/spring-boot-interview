package com.example.interview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.interview.model.Cidade;

public interface CidadeService {

	/**
	 * 
	 * @param nome
	 * @param pageable
	 * @return Page<Cidadde>
	 */
	Page<Cidade> findByNome(String nome, Pageable pageable);

	/**
	 * 
	 * @param Estado
	 * @param pageable
	 * @return Page<Cidade>
	 */
	Page<Cidade> findByEstado(String estado, Pageable pageable);

	/**
	 * 
	 * @param cidade
	 * @return Cidade
	 */
	Cidade saveCidade(Cidade cidade);

}