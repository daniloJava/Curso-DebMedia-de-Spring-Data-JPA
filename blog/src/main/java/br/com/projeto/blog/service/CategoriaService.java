package br.com.projeto.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.blog.entity.Categoria;
import br.com.projeto.blog.repository.CategoriaRepository;
import br.com.projeto.blog.util.MyReplaceString;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;

	
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id){
		repository.delete(id);
		
	}
	
	public Categoria findByDescricao(String descricao){
		return repository.findByDescricao(descricao);
	}
	
	public Categoria findById(Long id){
		return repository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Categoria categoria){
		String permalink = MyReplaceString.formatarPermalink(categoria.getDescricao());
		categoria.setPermaLink(permalink);
		repository.save(categoria);
		
	}
	
}
