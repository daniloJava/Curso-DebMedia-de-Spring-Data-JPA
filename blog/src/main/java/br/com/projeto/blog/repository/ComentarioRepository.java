package br.com.projeto.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.blog.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

	
}
