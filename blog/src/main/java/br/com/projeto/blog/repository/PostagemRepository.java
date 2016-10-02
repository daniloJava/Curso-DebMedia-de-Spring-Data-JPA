package br.com.projeto.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.blog.entity.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	Postagem findByPermaLink(String link);
	
}
