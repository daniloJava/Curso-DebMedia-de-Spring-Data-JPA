package br.com.projeto.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.blog.entity.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Categoria findByDescricao(String descricao);
	
}
