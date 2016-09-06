package br.com.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import br.com.spring.entity.Person;
import br.com.spring.repository.AddressRepository;
import br.com.spring.repository.DocumentRepository;
import br.com.spring.repository.PersonRepository;
import br.com.spring.repository.PhoneRepository;

/**Clase para os teste do Spring
 * a interface sendo implementada CommandLineRunner - é para que possa se trabalhar com linha de comando
 * @author Aluno
 *
 */
@SpringBootApplication
@ImportResource(value="spring-date.xml")//dizendo para o Spring que precisa trabalhar com o arquivo Spring-data.xml
public class CursoSpringDataApplication implements CommandLineRunner{
	//pessoa
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private DocumentRepository documenteRepository;  
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringDataApplication.class, args);
	}
	
	/**Inicializando a app esse metodo é executado.
	 * 
	 */
	@Override
	public void run(String... arg0) throws Exception {
		
		testConfiguration();
	}
	
	//simples para ver se está funcionando
	private void testConfiguration() {
		long total = personRepository.count();
		System.out.println("tEM " + total);
		
		long total2 = addressRepository.count();
		System.out.println("tEM " + total2);
		
		long total3 = documenteRepository.count();
		System.out.println("tEM " + total3);
		
		long total4 = phoneRepository.count();
		System.out.println("tEM " + total4);
		
		
		List<Person> persons = personRepository.findAll();
		persons.forEach(System.out::println);
	}
	
}
