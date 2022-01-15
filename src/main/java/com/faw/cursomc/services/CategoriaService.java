package com.faw.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.faw.cursomc.domain.Categoria;
import com.faw.cursomc.repositories.CategoriaRepository;
import com.faw.cursomc.services.exceptions.DataIntegrityException;
import com.faw.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	// buscar uma categoria por codigo
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	// metodo para inserir uma categoria para inserir no repositório
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	// metodo para atualizar uma categoria para inserir no repositório
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	// metodo para deletar uma categoria para inserir no repositório
	public void delete(Integer id) {
		find(id);
		try {   // tratamento para que quando fro excluir uma categoria que tem produtos inserido nela
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluior uma categoria que tem produtos!");
		}
		
	}

}
