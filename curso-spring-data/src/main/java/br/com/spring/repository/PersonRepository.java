package br.com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.entity.Person;

/**Criando uma interface para repositorio de pessoas e extendendo da Classe JPA
 * 
 * @author Aluno
 *
 */
public interface PersonRepository extends JpaRepository<Person, Long>{

	 
	
}
