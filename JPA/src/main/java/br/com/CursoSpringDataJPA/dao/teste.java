package br.com.CursoSpringDataJPA.dao;

import java.util.Arrays;

import br.com.CursoSpringDataJPA.entity.Address;
import br.com.CursoSpringDataJPA.entity.Document;
import br.com.CursoSpringDataJPA.entity.Person;
import br.com.CursoSpringDataJPA.entity.Phone;
import br.com.CursoSpringDataJPA.entity.Address.TypeAddress;
import br.com.CursoSpringDataJPA.entity.Phone.TypePhones;

public class teste {

	public static void main(String[] args) {
		
		insertAddress();
		
	}

	private static void insertAddress() {
		Address ad1 = new Address();
		ad1.setCity("Rio de janeiro");
		ad1.setStreet("rua dois dos dosiso");
		ad1.setTipo(TypeAddress.COMERCIAL);
		
		Address ad2 = new Address();
		ad1.setCity("São Paulo");
		ad1.setStreet("rua dos maldios locos");
		ad1.setTipo(TypeAddress.RESIDENCIAL);
		
		Person pes = new Person();
		pes.setFirstName("vida loca");
		pes.setLastName("Da silva");
		pes.setAge(33);
		pes.setDoc(new Document("43345444",123456));
		pes.addPhone(new Phone(TypePhones.CELULAR, "994578765"));
		pes.addPhone(new Phone(TypePhones.COMERCIAL, "080088765"));
		
		pes.setEndereco(Arrays.asList(ad1, ad2));
		
		new PersonDao().save(pes);
	}

	private static void insertPhoneByPerson() {
		
		Phone ph1 = new Phone(TypePhones.CELULAR, "994585013");
		Phone ph2 = new Phone(TypePhones.CELULAR, "944585034");
		Phone ph3 = new Phone(TypePhones.RESIDENCIAL, "41821508");
		Phone ph4 = new Phone(TypePhones.COMERCIAL, "43555555");
		Phone ph5 = new Phone(TypePhones.CELULAR, "98833455");
		
		Person pessoa = new Person();
		pessoa.setAge(23);
		pessoa.setFirstName("manol");
		pessoa.setLastName("Juquinha");
		pessoa.setDoc(new Document("423568888",553244566));
		
		ph1.setPessoa(pessoa);
		ph2.setPessoa(pessoa);
		ph3.setPessoa(pessoa);
		ph4.setPessoa(pessoa);
		ph5.setPessoa(pessoa);
		
		pessoa.setCel(Arrays.asList(ph1,ph2,ph3,ph4,ph5));
		
		new PersonDao().save(pessoa);
		
	}

}
