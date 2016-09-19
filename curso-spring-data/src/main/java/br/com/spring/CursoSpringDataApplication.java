package br.com.spring;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NamedQuery;

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
		
//		testeByAge();
//		testeFirstName();
//		testByAndOr();
//		testeBetween();
//		testelastNameANdeBetween();
//		testeGeaterAndLess();
//		testeGeaterAndLessEquals();
//		testeFirstGeaterthan();
//		testByStartEnd();
//		testByContaning();
//		testByAddressStartingEnding();
//		findByInAndNotIn();
//		testeByOderBy();
//		testIgnoreCase();
//		testIsNullAndNotNull();
//		testPhonesByNumber();
//		testByGreaterThanAndOrder();
//		findFirstNameWithQuery();
//		findFistNameAndAgeQuery();
//		findPersonByAgesParan();
//		findFirstNamesParam();
//		findDocumentByCpf();
//		findByAddressNamedQuery();
//		findByEndereco();
		
//		testeFunctionAnddress();
//		testeProcedureCPF();
//		updatePhones();
		deletePhoneNumber();
		
		
		
	}
	
	private void deletePhoneNumber() {
		
		int result = phoneRepository.deleteByPhoneNumber("94585013");
		System.out.println("Resultado " + result);
		
	}

	private void updatePhones() {
		
		int result = phoneRepository.setPhoneNumber("845850", 3L);
		System.out.println("Result = " + result);
		
		
		int result2 = phoneRepository.setPhoneNumber(TypePhones.RESIDENCIAL, 3L);
		System.out.println("Result = " + result2);
		
		
	}

	private void testeProcedureCPF() {
		
		String cpf1 = documentRepository.replaceCPF(2L);
		System.out.println(cpf1);
		
		String cpf2 = documentRepository.procReplaceCPF(2L);
		System.out.println(cpf2);
		
	}

	private void testeFunctionAnddress() {
		
		String ad1 = addressRepository.funcConcateAddress(7L);
		
		System.out.println(ad1);
		
		String ad2 = addressRepository.funcionNativeQueryConcatEndereco(7L);
		
		System.out.println(ad2);
		
		
	}

	private void findByEndereco() {
		List<Address> addres = addressRepository.buscaPorEndereco("Carapicuiba", "Rua dos Bobos numero 0");
		addres.forEach(System.out::println);
		
	}

	private void findByAddressNamedQuery() {
		List<Address> addres = addressRepository.buscaPorCidade("Carapicuiba");
		addres.forEach(System.out::println);
		
	}

	private void findDocumentByCpf() {
		List<Document> doc = documentRepository.findByCpfStartWith("42");
		doc.forEach(System.out::println);
		
	}

	private void findFirstNamesParam() {
		//Busca os nome ou com a idade informada usando o metodo com o @Query e @Paran.
		List<Person> p1 = personRepository.findByFirstNames("aline", "Juquinha", "Danilo");
		p1.forEach(System.out::println);
		
		
	}

	private void findPersonByAgesParan() {
		//Busca os nome ou com a idade informada usando o metodo com o @Query e @Paran.
		List<Person> p1 = personRepository.findByAgeBetween(10, 30);
		p1.forEach(System.out::println);
		
		
	}

	private void findFistNameAndAgeQuery() {
		//Busca os nome ou com a idade informada usando o metodo com o @Query.
		List<Person> p1 = personRepository.findByFirstNameOrAge("Danilo", 29);
		p1.forEach(System.out::println);
		
	}

	private void findFirstNameWithQuery() {
		//Busca os nomes usando o metodo com o @Query.
		List<Person> p1 = personRepository.findByFirstName("Danilo");
		p1.forEach(System.out::println);
		
	}

	private void testByGreaterThanAndOrder() {
		
		//retorna as pessoas com base no seu numero de telefone
		List<Person> p1 = personRepository.findByAgeGreaterThanOrderByFirstNameAscLastNameAsc(34);
		p1.forEach(System.out::println);
		
		
	}

	private void testPhonesByNumber() {
		//retorna as pessoas com base no seu numero de telefone
		List<Person> p1 = personRepository.findByCelStartingWith("98");
		p1.forEach(System.out::println);
	}

	private void testIsNullAndNotNull() {
		//Recupera lista que esteja Nulo os Documentos
		List<Person> p1 = personRepository.findByDocIsNull();
		p1.forEach(System.out::println);
		
		//Recupera lista que não esteja Nulo os Documentos
		List<Person> p2 = personRepository.findByDocIsNotNull();
		p2.forEach(System.out::println);
		
	}

	private void testIgnoreCase() {
		//Recupera lista ignorando as letras;
		List<Person> p1 = personRepository.findByFirstNameIgnoreCase("dAnIlO");
		p1.forEach(System.out::println);
		
	}

	private void testeByOderBy() {
		
		//lista de cidade Carakas ordenadas pelo tipo.
		List<Address> a1 = addressRepository.findByCityOrderByTipoDesc("Carakas");
		a1.forEach(System.out::println);
	}

	private void findByInAndNotIn() {
		
		List<Person> p1 = personRepository.findByAgeNotIn(14,15,45);
		p1.forEach(System.out::println);
		
		List<Person> p2 = personRepository.findByAgeIn(14,15,45);
		p2.forEach(System.out::println);
		
	}

	private void testByAddressStartingEnding() {
		//procura que começa com uma cidade OU termina com a rua informada 
		List<Address> a1 = addressRepository.findByCityStartingWithOrStreetEndingWith("Carakas","Dos Bobos");
		a1.forEach(System.out::println);
		
	}

	private void testByContaning() {
		
		//procura que contenha o parametro 
		List<Address> a1 = addressRepository.findByStreetContaining("Dos Bobos");
		a1.forEach(System.out::println);
		
	}
	
	private void testByStartEnd() {
		
		//procura que contenha o parametro 
		List<Address> a1 = addressRepository.findByCityStartingWith("Dos Bobos");
		a1.forEach(System.out::println);
		// inicia com;
		List<Address> a2 = addressRepository.findByStreetEndingWith("Rua");
		a2.forEach(System.out::println);
		
	}

	//Teste
	private void testeFirstGeaterthan() {
		//No caso, ele procura letra por letra da ordem alfabetica, nesse caso o registro Danilo não retorna.
		List<Person> p1 = personRepository.findByFirstNameGreaterThan("Danilo");
		p1.forEach(System.out::println);
		
	}

	private void testeGeaterAndLessEquals() {
		// TODO Auto-generated method stub
		//tudo que for >= 28
		System.out.println("--------- - - - - -- - -  - - -  -------");
		//recupera tudo que for <= 25
		List<Person> p2 = personRepository.findByAgeLessThanEqual(25);
		p2.forEach(System.out::println);
		
	}

	private void testeGeaterAndLess() {
		//tudo que for > 28
		List<Person> p1 = personRepository.findByAgeGreaterThan(25);
		p1.forEach(System.out::println);
		
		System.out.println("--------- - - - - -- - -  - - -  -------");
		//recupera tudo que for < 25
		List<Person> p2 = personRepository.findByAgeLessThan(25);
		p2.forEach(System.out::println);
		
	}

	private void testelastNameANdeBetween() {
		//Recupera tudo que for "Danilo" e a idade entre 14 e 80
		List<Person> p1 = personRepository.findByLastNameAndAgeBetween("Danilo", 14, 80);
		p1.forEach(System.out::println);
		
		
	}

	private void testeBetween() {
	
		//Recupera as idades de 14 até 80
		List<Person> p1 = personRepository.findByAgeBetween(14, 80);
		p1.forEach(System.out::println);
	
	}		

	//Teste do KeyWords AND OR
	private void testByAndOr() {
		//Somente pelo nome
		Person p1 = personRepository.findByFirstNameAndLastName("Juquinha", "Danilo");
		System.out.println(p1.toString());
		
		//tudo que for pelo nome e pela idade;
		List<Person> p2 = personRepository.findByAgeOrFirstName("Danilo", 22);
		p2.forEach(System.out::println);
		
	}

	//Teste dos novos metodos por LIKE
	private void testeFirstName() {
		
		List<Person> p1 = personRepository.findByFirstNameLike("Danilo");
		p1.forEach(System.out::println);
		
		System.out.println(" ------- - - - - --- - -- -- ");
		List<Person> p2 = personRepository.findByFirstNameNotLike("Danilo");
		p2.forEach(System.out::println);
		
		
	}

	//Testar o retorno de Age
	private void testeByAge() {
		//Recupera todos os regitros com a idade 23
		List<Person> p1 = personRepository.findByAge(23);
		
		p1.forEach(System.out::println);
		
		System.out.println("--- ---- ---- --- --- --- -- --- --");
		//recupera todos os registros que não tenha a idade 23
		List<Person> p2 = personRepository.findByAgeNot(23);
		
		p2.forEach(System.out::println);
		
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
