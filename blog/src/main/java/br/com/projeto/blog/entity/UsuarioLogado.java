package br.com.projeto.blog.entity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**Somente para armazena as informaçoes o usário logado na sessão
 * do spring security
 * 
 * extends User
 * os dados do login vai par auma classe do Srping Userdtails, 
 * tipo de perfil e uma seria de informaçõe sque o Spring recupera
 * 
 * 
 * @author Danilo Silva
 *
 */
public class UsuarioLogado extends User{

	private Usuario usuario;
	
	//autorizações do pefil
	/**Contrutor para usuario logado
	 * na sua classe super de USer - Spring. ele solicita alguns paramentros
	 * 
	 * Username - Usuário para logar
	 * Password - para senha de login
	 * Collection de Autorizações - são apenas as autoriazaões do Usuário. 
	 * 
	 * @param usuario
	 */
	public UsuarioLogado(Usuario usuario){
		super(
				usuario.getEmail(), 
				usuario.getSenha(), 
				AuthorityUtils.createAuthorityList(usuario.getPerfil().toString()));
		
		this.usuario = usuario;
		
	}
	
	
	public Long getId(){
		return usuario.getId();
	}
	
	
	public Perfil getPerfil(){
		return usuario.getPerfil();
	}
	
	
}
