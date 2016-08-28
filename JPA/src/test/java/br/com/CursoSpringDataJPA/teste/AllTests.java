package br.com.CursoSpringDataJPA.teste;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.CursoSpringDataJPA.dao.PersonDao;
import br.com.CursoSpringDataJPA.entity.Document;
import br.com.CursoSpringDataJPA.entity.Person;

@RunWith(Suite.class)
@SuiteClasses({})
public class AllTests {

	public void testesDosJPA(){
		Person pessoa = new Person();
		Document doc = new Document("32478928777", 435550135);
		pessoa.setAge(28);
		pessoa.setFirstName("Danilo");
		pessoa.setLastName("Silva");
		pessoa.setDoc(doc);
		
		new PersonDao().save(pessoa);
		System.out.println(pessoa.toString());
		
	}
}
