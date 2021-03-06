package br.com.projeto.blog.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**Diz para o Spring que todos os recursos que estaria no arquivo
 * web.xml estara nessa classe
 * 
 * @author Danilo Silva
 *
 */
public class SpringWebXmlConfig implements WebApplicationInitializer{
	
	/**esse o metodo que irá startar apartir
	 * do contexto do servidor.
	 * é apartir daqui eu diz qual classe de configuração.
	 * 
	 */
	public void onStartup(ServletContext servletContext) throws ServletException {
		// apartir dela que diz para o spring que a aplicação está baseada em anotação.
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		
		//registra mais uma aplciaçãao
		servletContext.addListener(new ContextLoaderListener(webContext));
		
		webContext.register(SpringMvcConfig.class);//Registra as configuraçãoes
		webContext.setServletContext(servletContext);//contexto da aplicação são todos os registros de config
		
		//Classe para usar o servlet do Dispacher
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
		//quando for adicionado uma pagina de erro, 404;
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		
		//Registrar o Dispachar criado a cima
		ServletRegistration.Dynamic registrationDinamyc 
				= servletContext.addServlet("dispacher", dispatcherServlet);
		
		registrationDinamyc.setLoadOnStartup(1); // 
		registrationDinamyc.addMapping("/"); // URL inicial 

		//força as requizições para UTF-8
		FilterRegistration.Dynamic enconding = 
				servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
		enconding.setInitParameter("encoding", "UTF-8");
		enconding.setInitParameter("forceEncoding", "true");
		enconding.addMappingForUrlPatterns(null, true, "/*");// tudo que estiver dentro do barra como UTF-8
		
		//para deixar uma sessão aberta ao abrir a pagina até que o processo de renderização da pagina seja concluida.
		FilterRegistration.Dynamic inViewSession = servletContext.addFilter("Spring OpenEntityManagerInViewFilter",
				new OpenEntityManagerInViewFilter());
		inViewSession.setAsyncSupported(Boolean.TRUE);
		inViewSession.addMappingForUrlPatterns(null, true, "/*");
		
		//trabalhando com Spring Scurity
		FilterRegistration.Dynamic securityFilter = 
				servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
		securityFilter.setAsyncSupported(Boolean.TRUE);
		securityFilter.addMappingForUrlPatterns(null, true, "/*");
		
	}

}
