package com.example.interview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.interview.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query("SELECT cl FROM Cliente cl WHERE UPPER(cl.nomeCompleto) LIKE UPPER(CONCAT('%', :nomeCompleto,'%'))")
	Page<Cliente> findByNomeCompleto (@Param("nomeCompleto") String nomeCompleto, Pageable pageable);	

}
