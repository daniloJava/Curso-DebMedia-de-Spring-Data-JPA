package br.com.projeto.blog.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("br.com.projeto.blog.repository")
@EnableTransactionManagement
@PropertySource("classpath:/application.properties")
public class SpringDataConfig {
	
	//usar as propriedades do Hibernate
	@Autowired
	private Environment env;
	
	/**Configurar a parte de transação, é onde indica
	 * que o Spring ira controlar as transações
	 * 
	 */
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(factory);
		manager.setJpaDialect(new HibernateJpaDialect());
		return manager;
	}
	
	/**onde vai utilizar o Hibernate como o framework ORM
	 * 
	 * @return
	 */
	@Bean
	public HibernateJpaVendorAdapter jpaVendorAdapter(){
				HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
				adapter.setShowSql(env.getProperty("hibernate.show.sql", Boolean.class));
				
				adapter.setGenerateDdl(env.getProperty("hibernate.ddl", Boolean.class));

		return adapter;
	}
	
	/**Responsavel para o spring trabalhar com Hibernate
	 *Com um pequeno problema em apresentar o sql, porem simplesmente é aplicar o metodo assim e faz a chamada direto no metodo
	 * @return
	 */
	@Bean
	public EntityManagerFactory entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(jpaVendorAdapter());
		factory.setPackagesToScan(env.getProperty("hibernate.package.scan"),
				env.getProperty("java.time.jpa.converter"));
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();
		
		return factory.getObject();
	}
	
	/**Criação do Bean para a conexão com o Banco de dados.
	 * 
	 * @return - Retorno do Objeto configurado para conexão
	 */
	@Bean(name = "dataSource")//usa o Id como onome do metodo ou @Bean(name = "DATASOURCE") 
	public DataSource dataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClass"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		return dataSource;
		
	}
	

}
