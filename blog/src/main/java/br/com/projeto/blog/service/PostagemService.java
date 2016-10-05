package br.com.projeto.blog.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.blog.entity.Postagem;
import br.com.projeto.blog.repository.PostagemRepository;
import br.com.projeto.blog.util.MyReplaceString;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PostagemService {

	@Autowired
	private PostagemRepository repository;

	
	public List<Postagem> findAll(){
		return repository.findAll();
	}
	
	
	public Postagem findByPermaLink(String link){
		return repository.findByPermaLink(link);
	}
	
	public Postagem findById(Long id){
		return repository.findOne(id);
		
	}
	
	
	@Transactional(readOnly = false)
	public void saveOrUpdadte(Postagem postagem){
		
		if(postagem.getId() == null){
			save(postagem);
		}else {
			update(postagem);
		}
	}

	private void update(Postagem postagem) {
		Postagem persistente = repository.findOne(postagem.getId());
		
		if(!persistente.getTitulo().equals(postagem.getTitulo())){
			persistente.setTitulo(postagem.getTitulo());
		}
		
		if(!persistente.getTexto().equals(postagem.getTexto())){
			persistente.setTexto(postagem.getTexto());
			
		}
		
		if(persistente.getCategorias() != postagem.getCategorias()){
			persistente.setCategorias(postagem.getCategorias());
			
		}
		
		repository.save(persistente);
	}

	private void save(Postagem postagem) {
		
		String permaLink = MyReplaceString.formatarPermalink(postagem.getTitulo());
		System.out.println(permaLink);
		postagem.setDataPostagem(LocalDateTime.now());
		
		postagem.setPermaLink(permaLink);
		repository.save(postagem);
		
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);
	}
	
	
	
}
