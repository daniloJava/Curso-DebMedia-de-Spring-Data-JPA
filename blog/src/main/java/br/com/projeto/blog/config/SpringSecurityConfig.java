package br.com.projeto.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.projeto.blog.service.UsuarioLogadoDatailService;

/**Configuação do Spring Securityconfig
 * 
 * 
 * @author Danilo Silva
 *
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsuarioLogadoDatailService userLogadoDatails;
	
	/**O Spring bloqueia qualquer arquivo,
	 * com esse metodo 'ecriado uma regra par aliber o que é necessario.
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/css/**").permitAll() //autoricando o CSS
			.antMatchers("/js/**").permitAll() // Autorizando os JS
			.antMatchers("/", "/auth/**",
					"/{permalink}", "/search/**",
					"/autor/{id}/page/{page}",
					"/categoria/{link}/page/{page}",
					"/page/{page}",
					"/avatar/load/{id}"
				) // todas as permições possiveis que são publicas 
				.permitAll() // liberando para todo mundo as paginas	
			// qualquer Request que estiver autenticado trá acesso o que está acima
			.anyRequest().authenticated() 
			
			.and()
				.formLogin() //trabalha com um formulário de login
				.loginPage("/auth/login") // onde indica a url de acesso a pagina de login pelo Controller
				.failureUrl("/auth/login?error=true") //caso haja uma falha no login é redirecionado par auma URL
				.usernameParameter("j_username") // nome do imput que ira usar o username
				.passwordParameter("j_password") // senha  são nomes dos inputs para q o Spring securit são dados de autenticação
				.permitAll()
			.and()
				.logout() // quando clicar no botao de logout de sair da sessão
				.logoutSuccessUrl("/auth/login?logout=true") // definir um url de logout com sucesso
				.invalidateHttpSession(true) // para invalidar aquela sessão que o usuário estava utilizando
				.deleteCookies("JSESSIONID") // deleta os Cookies que o Java aloca
			.and()
				.exceptionHandling()// para qualquer exeção
				.accessDeniedPage("/auth/denied"); // que é aquela ágina que não poderia acessar e é redireicionado a outra pagina
	}
	
	
	/**Metodo Sobrescrito
	 * para informaor aos spring o usuario logado e seus atributos
	 * 
	 * 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userLogadoDatails).passwordEncoder(new BCryptPasswordEncoder());
	
	}
	
}
