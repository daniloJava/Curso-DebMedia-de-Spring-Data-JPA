package br.com.projeto.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**Classe de configuração para o MVC
 * 
 * Extendendo WebMvcConfigurerAdapter para que herder alguns recursos
 * @author Danilo Silva
 *
 */
@Configuration
@EnableWebMvc
//onde procura as classe com as anotações para injetar as dependencias
@ComponentScan("br.com.projeto.blog")
public class SpringMvcConfig extends WebMvcConfigurerAdapter{
	
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**")
			.addResourceLocations("/WEB-INF/resources/css/");
		
	}


	/**Classe para configurações de arquivos pelo site.
	 * 
	 * @return
	 */
	@Bean
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(100000); //100k tamanho do arquivo.
		return multipartResolver;
	}
	
	
	/**é quem vai fazer a resolução das nossas paginas.
	 * que irar trabalhar com jsp, onde estão e o gerencimanto ou recursos das paginas JSP
	 * 
	 */
	@Bean(name = "jspViewResolver")
	public InternalResourceViewResolver resourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");//Dizendo para o Springonde estão nossas paginas
		resolver.setSuffix(".jsp");// o sufixo jsp, para não precisar colocar .jsp no final da pagina 
		resolver.setViewClass(JstlView.class);//que irádizer que usará JSTL
		return resolver;
	}
	
	

}
