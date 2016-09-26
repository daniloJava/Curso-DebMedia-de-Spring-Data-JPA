package br.com.projeto.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.blog.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

	Autor findByNome(String nome);
	
}
