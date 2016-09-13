package br.com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.entity.Person;

/**
 * Criando uma interface para repositorio de pessoas e extendendo da Classe JPA
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
	List<Person> findByFistNameLike(String firstName);
	
	/**procura todos os nomes que não contenha o parametro recebido.
	 * 
	 * @param firstName
	 * @return
	 */
	List<Person> findByFistNameNotLike(String firstName);
	
	/**Utilizando operador logico AND para que busque somente um nome
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	Person findByFistNameAndLastName(String firstName, String lastName);
	
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
	List<Person> findByFirsttNameGreaterThan(String firstName);
	
	
}
