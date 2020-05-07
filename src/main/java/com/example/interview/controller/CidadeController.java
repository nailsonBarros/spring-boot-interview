package com.example.interview.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.interview.controller.dto.CidadeDTO;
import com.example.interview.model.Cidade;
import com.example.interview.service.CidadeService;

@RestController
@RequestMapping("/cidade")
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Page<Cidade>> findByNome(@PathVariable String nome, Pageable pageable){
		return new ResponseEntity<>(cidadeService.findByNome(nome, pageable), HttpStatus.OK);
	}
	
	@GetMapping("/estado/{estado}")
	public ResponseEntity<Page<Cidade>> findByEstado(@PathVariable String estado, Pageable pageable){
		return new ResponseEntity<>(cidadeService.findByEstado(estado, pageable), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Cidade> saveCidade(@RequestBody @Valid CidadeDTO cidadeDTO){
		return new ResponseEntity<>(cidadeService.saveCidade(new ModelMapper().map(cidadeDTO, Cidade.class)),HttpStatus.CREATED);
	}

}
