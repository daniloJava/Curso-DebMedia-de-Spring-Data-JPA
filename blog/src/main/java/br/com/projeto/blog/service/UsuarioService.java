package br.com.projeto.blog.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.blog.entity.Avatar;
import br.com.projeto.blog.entity.Usuario;
import br.com.projeto.blog.repository.UsuarioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	
	@Transactional(readOnly = false)
	public void updateNomeAndEmail(Usuario user) {
		
		repository.updateNomeAndEmail(user.getNome(), user.getEmail(), user.getId());
	}
	
	
	@Transactional(readOnly = true)
	public void delete(Long id){
		
		repository.delete(id);
	}
	
	@Transactional(readOnly = false)
	public void save(Usuario user){
		//recupera a data e coloca no Objeto usuário
		if(user.getDataCadastro() == null)
			user.setDataCadastro(LocalDate.now());
		
		String hash = new BCryptPasswordEncoder().encode(user.getSenha());
		user.setSenha(hash);
		
		repository.save(user);
		
	}
	
	public Usuario findById(Long id){
		return repository.findOne(id);
	}
	
	public Usuario findByEmail(String email){
		return repository.findByEmail(email);
	}

	public Usuario findByAvatar(Avatar avatar){
		return repository.findByAvatar(avatar);
	}
	
	
	
	public List<Usuario> findAll(){
		return repository.findAll();
	}

	@Transactional(readOnly = false)
	public void updateSenha(Usuario user) {
		String hash = new BCryptPasswordEncoder().encode(user.getSenha());
		user.setSenha(hash);
		
		repository.updateSenha(user.getSenha(), user.getId());
	}
	
	
	
}
