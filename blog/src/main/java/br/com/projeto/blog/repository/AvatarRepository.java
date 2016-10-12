package br.com.projeto.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.blog.entity.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long>{
	
	
}
