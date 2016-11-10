package br.com.projeto.blog.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.projeto.blog.entity.UsuarioLogado;

/**Classe de configuração pra auditoria.
 * 
 * @author Danilo Silva
 *
 */
public class SpringSecurityAuditor implements AuditorAware<String>{

	@Override
	public String getCurrentAuditor() {
		
		//ira retornar o usuário que está no contexto fazendo as operações
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		//anonymousUser - quando o usuáro anonimo está realizando operações
		if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
			
			return authentication.getPrincipal().toString();
		}
		
		return ((UsuarioLogado) authentication.getPrincipal()).getUsername();
	}

}
