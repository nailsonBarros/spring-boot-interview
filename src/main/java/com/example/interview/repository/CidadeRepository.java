package com.example.interview.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.interview.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	@Query("SELECT ci FROM Cidade ci WHERE UPPER(ci.nome) LIKE UPPER(CONCAT('%', :nome,'%'))")
	Page<Cidade> findByNome(@Param("nome") String nome, Pageable pageable);
	
	@Query("SELECT ci FROM Cidade ci WHERE UPPER(ci.estado) LIKE UPPER(CONCAT('%', :estado,'%'))")
	Page<Cidade> findByEstado(@Param("estado") String estado, Pageable pageable);
	
	@Query("SELECT ci FROM Cidade ci WHERE UPPER(ci.nome) = UPPER(:nome) AND UPPER(ci.estado) = UPPER(:estado)")
	Optional<Cidade> verificaJaExiste(@Param("nome") String nome,@Param("estado") String estado);
}
