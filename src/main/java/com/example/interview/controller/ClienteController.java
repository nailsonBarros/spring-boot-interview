package com.example.interview.controller;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.interview.controller.dto.ClienteDTO;
import com.example.interview.controller.dto.ClienteUpdateDTO;
import com.example.interview.model.Cliente;
import com.example.interview.service.ClienteService;
import com.example.interview.util.Response;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	
	@PostMapping
	public ResponseEntity<Cliente> insert(@RequestBody @Valid ClienteDTO clienteDTO, UriComponentsBuilder uriBuilder){
		Cliente cliente = clienteService.insert(new ModelMapper().map(clienteDTO, Cliente.class));
		URI location = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(location).body(clienteService.insert(cliente));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id){
		return new ResponseEntity<>(clienteService.findById(id),HttpStatus.OK);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Page<Cliente>> findByNomeCompleto(@PathVariable String name, Pageable pageable){
		return new ResponseEntity<>(clienteService.findByNomeCompleto(name, pageable), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteById(@PathVariable Long id){
		return new ResponseEntity<>(clienteService.deleteById(id),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateById(@RequestBody @Valid ClienteUpdateDTO clienteUpdateDTO, @PathVariable Long id){
		return new ResponseEntity<>(clienteService.updateById(new ModelMapper().map(clienteUpdateDTO, Cliente.class), id), HttpStatus.OK);
	}
	

}
