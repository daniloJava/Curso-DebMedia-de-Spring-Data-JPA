package br.com.spring;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

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
 * @author Danilo SIlva
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
//		testeDelete();
//		testeSavePersons();
//		testeDeletePersons();
//		testFindAndSort();
//		testFindByIDS();
//		testeExistes();
//		testePagination();
		
	}
	
	/**Criando com paginação
	 * 
	 */
	private void testePagination() {
		//criando uma paginação começãndo em 0 e contendo 4 linhas por pagina
		// se não tiver mais ele não traz nada.
		Page<Person> page = personRepository.findAll(new PageRequest(0, 4));
		page.getContent().forEach(System.out::println);
		
		Page<Person> page2 = personRepository.findAll(new PageRequest(1, 4));
		page2.getContent().forEach(System.out::println);
		
		Page<Person> page3 = personRepository.findAll(new PageRequest(2, 4));
		page3.getContent().forEach(System.out::println);
		
	}

	/**Um valor bolleando se existe ou não
	 * 
	 */
	private void testeExistes() {
		boolean b1 = personRepository.exists(10L);
		
		System.out.println("\n\n\n\n\n\n");
		System.out.println("B1 é " + b1);
		
		boolean b2 = personRepository.exists(61L);
		System.out.println("B2 é " + b2);

	}

	/**Tipo de consulta por uma lista de IDs
	 * 
	 * 
	 */
	private void testFindByIDS() {
		
		List<Person> persons = personRepository.findAll(Arrays.asList(10L, 11L, 13L));
		persons.forEach(System.out::println);
	}

	/** ORdenando a consulta
	 * 
	 */
	private void testFindAndSort() {
		
		Order orderAsc = new Order(Direction.ASC, "lastName");
		Order orderDesc = new Order(Direction.DESC, "firstName");
		Sort sort = new Sort(orderAsc, orderDesc);
		
		List<Person> persons = personRepository.findAll(sort);
		
		persons.forEach(System.out::println);
	}
	
	/**Deletando por uma lista de objetos
	 * 
	 */
	private void testeDeletePersons() {
		Person p1 = personRepository.findOne(11L);
		Person p2 = personRepository.findOne(12L);
		Person p3 = personRepository.findOne(13L);
		
		/*Dessa forma o Spring vai abrir a conexão, chamar um transação, executar, executar o commit e fechar tudo.
		 * ele vai executar para cada objeto, ou seja em 3 vezes conforme o array
		 * 
		 */
		personRepository.delete(Arrays.asList(p1, p2, p3));
		System.out.println("***********************************");
		
		Person p4 = personRepository.findOne(14L);
		Person p5 = personRepository.findOne(15L);
		Person p6 = personRepository.findOne(16L);
		/*nesse caso, será excluido em uma unica operação, onde teoricamente e passa os 3 ids de uma vez
		 * 
		 */
		personRepository.deleteInBatch(Arrays.asList(p4, p5, p6));
	}

	/**Salvando por um lista de Objetos
	 * 
	 */
	private void testeSavePersons() {
		Person p1 = new Person();
		p1.setFirstName("Dan");
		p1.setLastName("Juqui");
		p1.setAge(98);
		p1.setDoc(new Document("465.111.111-88", 45555565));
		
		Person p2 = new Person();
		p2.setFirstName("aline");
		p2.setLastName("amaral");
		p2.setAge(23);
		p2.setDoc(new Document("478.439.148-88", 58557765));
		
		Person p3 = new Person();
		p3.setFirstName("Carlos");
		p3.setLastName("silva");
		p3.setAge(45);
		p3.setDoc(new Document("478.439.144-48", 5834465));
		
		Person p4 = new Person();
		p4.setFirstName("Juquinha");
		p4.setLastName("Locaa0");
		p4.setAge(12);
		p4.setDoc(new Document("248.349.148-88", 58554565));
		
		Person p5 = new Person();
		p5.setFirstName("Vinicius");
		p5.setLastName("oliveira");
		p5.setAge(73);
		p5.setDoc(new Document("478.119.457-22", 58676365));
		
		
		Person p6 = new Person();
		p6.setFirstName("Carlinha");
		p6.setLastName("Show");
		p6.setAge(44);
		p6.setDoc(new Document("118.479.438-38", 58239965));
		
		//adiciona todos os objetos criados passando uma interable.
		List<Person> persons = personRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
		persons.forEach(System.out::println);
	}
	
	/**Deletando uma lista
	 * 
	 */
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
