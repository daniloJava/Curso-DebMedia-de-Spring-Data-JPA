package br.com.projeto.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.blog.entity.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	Postagem findByPermaLink(String link);

	
	List<Postagem> findByCategoriasPermaLink(String permaLink);


	List<Postagem> findByAutor(String nome);
	
	Page<Postagem> findAllByOrderByDataPostagemDesc(Pageable pageable);


	Page<Postagem> findAllByCategoriasPermaLinkOrderByDataPostagemDesc(Pageable pageable, String permaLink);


	Page<Postagem> findAllByAutorIdOrderByDataPostagemDesc(Pageable pageable, Long id);


	Page<Postagem> findByTextoContainingIgnoreCaseOrderByDataPostagemDesc(String texto, Pageable pageable);

}
