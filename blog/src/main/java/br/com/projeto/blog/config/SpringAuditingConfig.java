package br.com.projeto.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

/**Classe de configuração do processo de auditoria
 * 
 * @Component - tranasformar em um Bean gerenciavel pelo Spring
 * 
 * @EnableJpaAuditing(auditorAwareRef = "auditorAware") habilitar o uso de auditoria
 * dando a referencia ao bean de auditoria
 * 
 * @author Danilo Silva
 *
 */
@Component
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SpringAuditingConfig {
	
	/**a classe SpringSecurityAuditor é extendida de AuditorAware
	 * 
	 * pois é o tipo de retorno que o objeto está preparado em receber
	 * 
	 * apartir dessas configuraão ele vai conseguir obter as informações e registrar no BD
	 * 
	 * @return SpringSecurityAuditor - 
	 */
	@Bean
	public AuditorAware<String> auditorAware() {
		
		return new SpringSecurityAuditor();
	}
	
}
