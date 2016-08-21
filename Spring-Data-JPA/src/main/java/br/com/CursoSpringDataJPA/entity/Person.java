package br.com.CursoSpringDataJPA.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(
	name = "PERSONS",
	indexes = {@Index(columnList = "PRIMEIRO_NOME, ULTIMO_NOME", name = "IDX_PERSON_NAME", unique = true)}
)
/**Modelo para a a entidade Person
 * e tambem a criação de uma tabela no banco de dados
 * 
 *
 * @author Danilo Silva
 *
 */
public class Person implements Serializable {
	//aplicando ID e auto incremento
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PERSON")
	private Long id;
	
	@Column(name = "PRIMEIRO_NOME", nullable = false, length = 30)
	private String firstName;
	
	@Column(name = "ULTIMO_NOME", nullable = false, length = 60)
	private String lastName;
	
	@Column(name = "IDADE", nullable = false)
	private Integer age;
	
	/*cacade para cada a inserçao ou upadate.
	 * FetchType.EAGER = para cada interação com o person, o documente tambem é retornado
	 * 
	 * 
	 */
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_Documents")
	private Document doc;

	/*mappedBy é a forma que se diz qual metodo ou variavel da classe Phone
	 * que ele vai se relacionar.
	 * 
	 */
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Phone> cel;
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//mesmo mapeamento de Address
	@JoinTable(
			name = "PERSONS_ENDERECO",
			joinColumns = @JoinColumn(name = "ID_ENDERECO"),
			inverseJoinColumns = @JoinColumn(name = "ID_PERSON")
	)
	private List<Address> endereco;
	
	
	
	
	/**
	 * @return o atributo endereco
	 */
	public List<Address> getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(List<Address> endereco) {
		this.endereco = endereco;
	}

	/**Classe que adiciona os numeros de telefones
	 * caso não esteja inicializado a lista de telefones.
	 * 
	 * @param fones Phones - telefone
	 */
	public void addPhone(Phone fones){
		if(cel == null)
			cel = new ArrayList<Phone>();
		/*pega a propria instancia e add no fone
		 * para que o ID de pessoa esteja em Fones
		*/
		fones.setPessoa(this);
		cel.add(fones);
		
	}
	
	/**
	 * @return o atributo cel
	 */
	public List<Phone> getCel() {
		return cel;
	}

	/**
	 * @param cel the cel to set
	 */
	public void setCel(List<Phone> cel) {
		this.cel = cel;
	}

	/**
	 * @return o atributo doc
	 */
	public Document getDoc() {
		return doc;
	}

	/**
	 * @param doc the doc to set
	 */
	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
	}	
}