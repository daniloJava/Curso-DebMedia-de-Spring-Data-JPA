package br.com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.spring.entity.Person;

/**
 * Criando uma interface para repositorio de pessoas e extendendo da Classe JPA
 * Utilizando tambem as palavras Chaves do Spring
 * 
 * Somente as palavras chaves, sempre começa com findBy + atributo + outra palavra chave + outro atrigunto ......
 * findByAge (pelo atributo)
 * Not
 * Like
 * NotLike
 * And
 * Or
 * Between
 * GreaterThan
 * LessThan 
 * GreaterThanEqual
 * LessThanEqual
 * In
 * NotIn
 * IgnoreCase
 * IsNull
 * IsNotNull
 * 
 * @author Danilo Silva
 *
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

	/**
	 * Retorna uma lista de pessoas igual ao parametro. Quando for construir um
	 * metodo que não vá ter relação com outros campos, apenas adicionar o
	 * Parametro do Metodo, que ele já ira Buscar
	 * 
	 * Importante que depois do By(colocar o Adributo) e nome do parametro tem
	 * que ser tambem o mesmo
	 * 
	 */
	List<Person> findByAge(Integer age);

	/**
	 * Seria a Negação do da consulta, ou seja irá buscar todas as idades
	 * diferente do que está no parametro.
	 * 
	 * @param age
	 *            - parametro passado para localizar no banco de dados.
	 * @return - lista de pessoas com a idade diferente do Parametro.
	 */
	List<Person> findByAgeNot(Integer age);

	
	/**Mesmo principio de mapear do mesmo modo que está no modelo..
	 * atravez dos atribustos, porem agora usando o LIKE para procurar um string que esteja em 
	 * qualquer linha ou lugar
	 * 
	 * @param firstName - parametro para procura no banco
	 * @return List<Person> - de todas os registros que tenha aquele parametro
	 */
	List<Person> findByFirstNameLike(String firstName);
	
	/**procura todos os nomes que não contenha o parametro recebido.
	 * 
	 * @param firstName
	 * @return
	 */
	List<Person> findByFirstNameNotLike(String firstName);
	
	/**Utilizando operador logico AND para que busque somente um nome
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	Person findByFirstNameAndLastName(String firstName, String lastName);
	
	/**Usando a logia do Operador OR para o Firstname e o AGE
	 * 
	 * @param firstName - String para o primeiro nome
	 * @param age - Idade
	 * @return - List<Person> - com todos os registros com os parametros
	 */
	List<Person> findByAgeOrFirstName(String firstName, Integer age);
	
	/**Retorna entre um valor e outro o recurso BETWEEN
	 * 
	 * @param min - idade minima
	 * @param max - idade maxima
	 * @return List<Person> - que tera todas as idades de min para max 
	 */
	List<Person> findByAgeBetween(int min, int max);
	
	/**Ou seja, procura tudo que é o Last name do parametro E tudo que for entre a idade mmin e a max
	 * 
	 * @param lastName String - Last name para procura
	 * @param min int - idade Minima
	 * @param max int - Idade maxima
	 * @return List<Person> - uma lista com a consulta. 
	 */
	List<Person> findByLastNameAndAgeBetween(String lastName, int min, int max);
	
	/**é como se fosse um MAIOR QUE  > 
	 * 
	 * ou seja, ele procura o que for maior que o parametro
	 * 
	 * @param age int - parametro da idade 
	 * @return List<Person> - que for MAIOR que age
	 */
	List<Person> findByAgeGreaterThan(int age);
	
	/**é como se fosse um MENOR QUE  < 
	 * 
	 * ou seja, ele procura o que for menor que o parametro
	 *@param age int - parametro da idade
	 * @return List<Person> - que for MENOR que age 
	 */
	List<Person> findByAgeLessThan(int age);
	
	
	
	/**é como se fosse um MAIOR QUE E IGUAL  >= 
	 * 
	 * ou seja, ele procura o que for maior que o parametro
	 * 
	 * @param age int - parametro da idade 
	 * @return List<Person> - que for MAIOR que ou igual age
	 */
	List<Person> findByAgeGreaterThanEqual(int age);

	/**é como se fosse um MENOR QUE E IGUAL  <= 
	 * 
	 * ou seja, ele procura o que for menor que o parametro
	 *@param age int - parametro da idade
	 * @return List<Person> - que for MENOR que ou igual age 
	 */
	List<Person> findByAgeLessThanEqual(int age);
	
	/**Procura por Firstname que for maior que o valor informado
	 * 
	 * @param firstName String - valor para procurar o que for maior
	 * @return List<Person>  - de pessoas maior que o parametro
	 */
	List<Person> findByFirstNameGreaterThan(String firstName);
	
	
	//lista de parametros em uma consulta
	/**Recupera uma lista conforme a Lista de parametros passados
	 * por parametros.
	 * 
	 * @param args - lista de parametros
	 * @return - retorna a consulta
	 */
	List<Person> findByAgeIn(Integer... args);
	
	
	/**Recupera uma lista que não esteja na lista passada por parametro
	 * por parametros.
	 * 
	 * @param args - lista de parametros
	 * @return - retorna a consulta
	 */
	List<Person> findByAgeNotIn(Integer... args);
	
	
	/**Eliminar a distinção de maiusculas e minusculas
	 * 
	 * @param fistname String - passando o nome
	 * @return Retorna lista com a consulta.
	 */
	List<Person> findByFirstNameIgnoreCase(String fistname);
	
	/**Buscando todas os registros que o documento que estiver Null
	 * 
	 * @return - retorna consulta
	 */
	List<Person> findByDocIsNull();
	
	/**Retorna todos os registros de documentos que não são nulos
	 * 
	 * @return - retorna consulta.
	 */
	List<Person> findByDocIsNotNull();
	
	/**fazendo uma consluta apartir d euma lista de telefones, ou seja, utilizando um atributo que é Objeto
	 * 
	 * Seria o nome da Classe, e o Atrinbuto dentro dessa lasse.
	 * 
	 * @return
	 */
	List<Person> findByCelStartingWith(String cel);
	
	/**Passando uma idade,
	 * ele ira buscar que tenha idade maior que o valor do parametro,
	 * ordenando pelo primeiro nome e depois por segundo nome
	 * 
	 * @param idade
	 * @return - retorna consulta.
	 */
	List<Person> findByAgeGreaterThanOrderByFirstNameAscLastNameAsc(Integer idade);
	
	/**Usando o @Query seria para o caso de que você escrevesse o JPQL
	 * Sendo que o nome do metodo pode ser qualquer nome, o Spring ira verificar somente o Anotação
	 * 
	 * ?1 - é a ordem dos parametos.
	 * Tomar cuidado com os parametros na declaração, porque ele identifica o Tipo na JPQL
	 * causando uma exessão se tiver alguma incompatibilidade.
	 * 
	 * @param fistname
	 * @return
	 */
	@Query("select p from Person p where p.firstName like ?1")
	List<Person> findByFirstName(String fistname);
	
	/**Mesma coisa do metodo a cima.
	 * 
	 * @param fistname
	 * @param age
	 * @return
	 */
	@Query("select p from Person p where p.firstName like ?1 or p.age = ?2")
	List<Person> findByFirstNameOrAge(String fistname, Integer age);
	
	/**Utilizando um Atributo Objeto.
	 * 
	 * @param cpfValor
	 * @return
	 */
	@Query("select p from Person p where p.doc.cpfDocument like %?1")
	List<Person> findByDocEndWith(String cpfDocument);
}
