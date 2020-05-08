package com.example.interview.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.interview.exception.CustomException;
import com.example.interview.exception.DeleteClienteByIdException;
import com.example.interview.exception.FindClienteByIdException;
import com.example.interview.exception.InsertClienteException;
import com.example.interview.exception.UpdateClienteNomeByIdException;
import com.example.interview.model.Cidade;
import com.example.interview.model.Cliente;
import com.example.interview.repository.ClienteRepository;
import com.example.interview.service.CidadeService;
import com.example.interview.service.ClienteService;
import com.example.interview.util.Constants;
import com.example.interview.util.Response;


@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Override
	public Page<Cliente> findByNomeCompleto (String nomeCompleto, Pageable pageable){
		return clienteRepository.findByNomeCompleto(nomeCompleto, pageable);
	}
	
	@Override
	public Cliente findById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if(cliente.isPresent()) {
			return cliente.get();
		}else {
			throw new FindClienteByIdException(Constants.CLIENTE_NOT_FOUND);
		}
		
		
	}
	
	@Override
	public Response deleteById(Long id) {
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		if(optionalCliente.isPresent()) {
			clienteRepository.deleteById(id);
			return new Response(Constants.SUCESS, Constants.DELETED_SUCCESSFUL);
		}
		throw new DeleteClienteByIdException(Constants.CLIENTE_NOT_FOUND);
		
	}
	
	
	@Override
	public Cliente updateById(Cliente cliente, Long id){
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		
		if(optionalCliente.isPresent()) {
			optionalCliente.get().setNomeCompleto(cliente.getNomeCompleto());
			return clienteRepository.save(optionalCliente.get());
		}
		
		throw new UpdateClienteNomeByIdException(Constants.CLIENTE_NOT_FOUND);
		
	}
	
	@Override
	public Cliente insert(Cliente cliente){
		
		if(cliente.getCidade() == null) {
			throw new InsertClienteException(Constants.CIDADE_NOT_FOUND);
		}
		
		if(cliente.getCidade().getNome() == null 
				|| cliente.getCidade().getNome().equals("")
				|| cliente.getCidade().getEstado() == null
				|| cliente.getCidade().getEstado().equals("")) {
			throw new InsertClienteException(Constants.CIDADE_OR_ESTADO_NOT_FOUND);
		}
		
		Cidade cidade = cidadeService.saveCidade(new Cidade(cliente.getCidade().getNome(),
															cliente.getCidade().getEstado()));
		
		
		cliente.setCidade(cidade);
		return clienteRepository.save(cliente);
	}

}
