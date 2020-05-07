package com.example.interview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.interview.model.Cliente;
import com.example.interview.util.Response;

public interface ClienteService {

	/**
	 * 
	 * @param nomeCompleto
	 * @param pageable
	 * @return Page<Cliente>
	 */
	Page<Cliente> findByNomeCompleto(String nomeCompleto, Pageable pageable);

	/**
	 * 
	 * @param id
	 * @return Cliente
	 */
	Cliente findById(Long id);

	/**
	 * 
	 * @param id
	 * @return Response
	 */
	Response deleteById(Long id);

	/**
	 * 
	 * @param cliente
	 * @param id
	 * @return Cliente
	 */
	Cliente updateById(Cliente cliente, Long id);

	/**
	 * 
	 * @param cliente
	 * @return Cliente
	 */
	Cliente insert(Cliente cliente);

}