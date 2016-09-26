package br.com.projeto.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.blog.entity.Autor;
import br.com.projeto.blog.repository.AutorRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AutorService {
	
	@Autowired
	private AutorRepository repository;

	@Transactional(readOnly = false)
	public void save(Autor autor){
		repository.save(autor);
	}
	
	public List<Autor> findAll(){
		return repository.findAll();
	}
	
	public Autor findById(Long id){
		return repository.findOne(id);
		
	}
	public Autor findByNome(String nome){
		return repository.findByNome(nome);
	}
}
