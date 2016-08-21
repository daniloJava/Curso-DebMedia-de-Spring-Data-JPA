package br.com.CursoSpringDataJPA.dao;

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

}
