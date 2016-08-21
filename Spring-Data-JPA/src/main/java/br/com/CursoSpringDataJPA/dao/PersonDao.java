package br.com.CursoSpringDataJPA.dao;

import java.util.List;

import br.com.CursoSpringDataJPA.entity.Person;

/**Dao Person
 * apenas extendendo a classe fenerica dao, 
 * não precisa implemenar nenhuma classe
 *
 * @author Danilo Silva
 *
 */
public class PersonDao extends GenericDao<Person>{
	
	public PersonDao(){
		super(Person.class);
	}
/**Procura por nome 
 * 	
 * @param lastName - nome a ser procurado
 * @return
 */
public List<Person> findByLastName(String lastName) {
		//JPQL para procurar o nome da pessoa
		String jpql = "from Person p where p.lastName like ?";
		
		return find(jpql, lastName);
	}
	/**QUery que procura entre idades.
	 * 
	 * @param min - numero minimo
	 * @param max - numero maximo
	 * @return List<Person> - retorna lista de pssoa conforme a query
	 */
	public List<Person> findAgeIsBetween(int min, int max) {
		//JPQL para procurar o nome da pessoa
		String jpql = "from Person p where p.age between ? and ?";
		
		return find(jpql, min, max);
	}
	/**recupera a pessoa por nome e segundo nome 
	 * 
	 * @param firstName - primeiro nome
	 * @param lastName - segundo nome
	 * @return - Unico registro 
	 */
	public Person findByFullName(String firstName, String lastName) {

		String jpql = "from Person p where p.firstName like ? and p.lastName like ?";
		
		return findOne(jpql, firstName, lastName);
	}
	
	/**Recupera a pessoa pelo CPF
	 * de forma pelo relacionamento entre documentos e pessoas
	 * 
	 * @param cpf - qual cpf a ser procurado
	 * @return - a essoa trelada aquele CPF.
	 */
	public Person findByCpf(String cpf){
	
		String sql = "select p from Person p, Document d where d.cpfDocument like ?"
				+ "and p.doc.idDocument = d.idDocument";
				
		return findOne(sql, cpf);
		
	}
}
