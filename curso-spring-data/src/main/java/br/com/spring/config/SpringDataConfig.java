package br.com.spring.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**Classe de configuração baseado em código java
 * 
 * @author Danilo Silva
 *
 */
@Configuration //informando que é uma classe de configuração
@EnableJpaRepositories(basePackages = "br.com.spring.repository")// habilitando o uso do JPA.
@EnableTransactionManagement//habilitando o uso de transaçoes
@PropertySource(value = "classpath:application.properties")//habilitar o Spring a ler o Arquivo de propriedades
public class SpringDataConfig {
	
	//Configurando o Data Sources
	@Value(value = "${jdbc.user}")
	private String username;
	@Value(value = "${jdbc.pass}")
	private String password;
	@Value(value = "${jdbc.driver}")
	private String driver;
	@Value(value = "${jdbc.url}")
	private String url;

	
	/**Configurar a parte de transação
	 * 
	 */
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(factory);
		manager.setJpaDialect(new HibernateJpaDialect());
		return manager;
	}
	
	@Bean
	public HibernateJpaVendorAdapter jpaVendorAdapter(){
		//<property name="jpaVendorAdapter">
				HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
				//<property name="showSql" value="true"></property>
				adapter.setShowSql(true);
				//<property name="generateDdl" value="true"></property>
				adapter.setGenerateDdl(true);

		return adapter;
	}
	
	/**Responsavel para o spring trabalhar com Hibernate
	 *Com um pequeno problema em apresentar o sql, porem simplesmente é aplicar o metodo assim e faz a chamada direto no metodo
	 * @return
	 */
	@Bean
	public EntityManagerFactory entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		//<property name="jpaVendorAdapter">
		factory.setJpaVendorAdapter(jpaVendorAdapter());
		//<property name="packagesToScan" value="br.com.spring.entity">
		factory.setPackagesToScan("br.com.spring.entity");
		//<property name="dataSource" ref="dataSource">
		factory.setDataSource(dataSource());
		//finalizando a factory
		factory.afterPropertiesSet();
		
		return factory.getObject();
	}
	
	/**Criação do Bean para a conexão com o Banco de dados.
	 * 
	 * @return - Retorno do Objeto configurado para conexão
	 */
	@Bean(name = "dataSource")//usa o Id como onome do metodo ou @Bean(name = "DATASOURCE") 
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		
		return dataSource;
		
	}
	
}
