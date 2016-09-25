package br.com.projeto.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.blog.entity.Avatar;
import br.com.projeto.blog.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);
	
	Usuario findByAvatar(Avatar avatar);
	
	
}
