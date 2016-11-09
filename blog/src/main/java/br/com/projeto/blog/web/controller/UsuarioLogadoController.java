package br.com.projeto.blog.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.projeto.blog.entity.UsuarioLogado;

/**Controlle rreferente ao usuário Logado.
 * 
 * @author Danilo Silva
 *
 */
@ControllerAdvice
public class UsuarioLogadoController {

	/**recueora o usuário logado
	 * 
	 * @param authentication - poderá fazer o teste se esta logado ou não
	 * @return
	 */
	@ModelAttribute("logado") // atributo que informa que o usuário está logado.
	public UsuarioLogado getUsuarioLogado(Authentication authentication){
		
		return (authentication == null) ? null : (UsuarioLogado) authentication.getPrincipal();
	}
	
}
