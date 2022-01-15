package com.faw.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faw.cursomc.domain.Cliente;
import com.faw.cursomc.repositories.ClienteRepository;
import com.faw.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	//buscar uma cliente por codigo
	public Cliente buscar(Integer id) { 
		 Optional<Cliente> obj = repo.findById(id); 
		 return obj.orElseThrow(() -> new ObjectNotFoundException( 
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 
	}
}
