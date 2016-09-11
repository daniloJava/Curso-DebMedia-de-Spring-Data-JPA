package br.com.spring;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
import org.springframework.data.domain.Sort;

import br.com.spring.entity.Address;
import br.com.spring.entity.Address.TypeAddress;
import br.com.spring.entity.Phone.TypePhones;
import br.com.spring.entity.Document;
import br.com.spring.entity.Person;
import br.com.spring.entity.Phone;
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
//@ImportResource(value="spring-data.xml")//dizendo para o Spring que precisa trabalhar com o arquivo Spring-data.xml
public class CursoSpringDataApplication implements CommandLineRunner{
	//pessoa
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private DocumentRepository documentRepository;
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
		
//		testConfiguration();
//		testSave();
//		testUpdate();
		testeDelete();
		
	}
	private void testeDelete() {
		List<Person> persons = personRepository.findAll();
		persons.forEach(System.out::println);
		//deletando pelo ID
		personRepository.delete(1L);
		
		//deletando pelo objeto completo
		Person pes = personRepository.findOne(6L);
		
		personRepository.delete(pes);
		
		persons = personRepository.findAll();
		persons.forEach(System.out::println);
		
	}

	//teste de updatedes
	private void testUpdate() {
		Person person = personRepository.findOne(7L);
		System.out.println(person.getLastName());
		
		person.setLastName("Manoel");
		//Spring data não possue um metodo Update, 
		//ele verifica que o objeto já tem um ID, e ele executa então o update
		personRepository.save(person);
		
		Person person2 = personRepository.findOne(7L);
		System.out.println(person2.getLastName());
	}

	//Metodos para testar os salvars
	private void testSave() {
		Person pessoa1 = new Person();
		pessoa1.setFirstName("Danilo");
		pessoa1.setLastName("Juquinha");
		pessoa1.setAge(24);
		pessoa1.setDoc(new Document("425.789.168-88", 455550135));
		
		Address endereco = new Address();
		endereco.setCity("Carapicuiba");
		endereco.setStreet("Rua dos Bobos numero 0");
		endereco.setTipo(TypeAddress.RESIDENCIAL);

		
		pessoa1.setEndereco(Arrays.asList(endereco));
		pessoa1.addPhone(new Phone(TypePhones.CELULAR, "994585013"));
		
		personRepository.save(pessoa1);
		
		Person p2 = personRepository.findOne(pessoa1.getId());
		
		System.out.println(p2.toString());
		
	}

	//simples para ver se está funcionando
	private void testConfiguration() {
		long total = personRepository.count();
		System.out.println("tEM " + total);
		
		long total2 = addressRepository.count();
		System.out.println("tEM " + total2);
		
		long total3 = documentRepository.count();
		System.out.println("tEM " + total3);
		
		long total4 = phoneRepository.count();
		System.out.println("tEM " + total4);
		
		
		List<Person> persons = personRepository.findAll();
		persons.forEach(System.out::println);
	}
	
}
