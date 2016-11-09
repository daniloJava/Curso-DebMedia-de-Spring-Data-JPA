package br.com.projeto.blog.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.blog.entity.Usuario;
import br.com.projeto.blog.entity.UsuarioLogado;

/**classe Service para verificar o usuário logado
 * 
 * 
 * @author Danilo Silva
 *
 */
@Service
public class UsuarioLogadoDatailService implements UserDetailsService{
	
	private static final Logger LOG = Logger.getLogger(UsuarioLogadoDatailService.class);
	
	@Autowired
	public UsuarioService service;
	
	/**nesse metodo que o Spring vai testar se 
	 * o usuário está tentando entrar no Sistema.
	 * 
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		
		Usuario usuario;
		
		try{
			usuario = service.findByEmail(userName);
			LOG.info("Usuario Encontrato -->  " + userName + "  <--");
		}catch(Exception e ){
			LOG.error("Usuario Não Encontrato -->  " + userName + "  <--");
			throw new UsernameNotFoundException("Usuário "+ userName + " não encontrado no Sistema");
		}
		
		return new UsuarioLogado(usuario);
	}

}
