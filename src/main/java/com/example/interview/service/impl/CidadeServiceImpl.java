package com.example.interview.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.interview.model.Cidade;
import com.example.interview.repository.CidadeRepository;
import com.example.interview.service.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public Page<Cidade> findByNome(String nome, Pageable pageable){
		return cidadeRepository.findByNome(nome, pageable);
		
	}
	
	@Override
	public Page<Cidade> findByEstado(String estado, Pageable pageable){
		return cidadeRepository.findByEstado(estado, pageable);
	}
	
	
	@Override
	public Cidade saveCidade(Cidade cidade) {
		
		Optional<Cidade> optionalCidade = cidadeRepository.verificaJaExiste(cidade.getNome(), cidade.getEstado());
		
		if(optionalCidade.isPresent()) {
			return optionalCidade.get();
		}
		
		return cidadeRepository.save(cidade);
	}

}
