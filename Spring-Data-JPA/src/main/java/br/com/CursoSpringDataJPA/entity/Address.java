package br.com.CursoSpringDataJPA.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESSES")
public class Address implements Serializable{
	
	public enum TypeAddress{
		RESIDENCIAL, COMERCIAL
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name ="ID_ENDERECO")
	private Long idAddresses;
	
	
	@Column(name = "CITY_ENDERECO")
	private String city; 
	
	@Column(name ="STREET_ENDERECO")
	private String street;
	
	@Enumerated(EnumType.STRING)
	@Column(name ="")
	private TypeAddress tipo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	/*Precisamos de alguns parametros, porque é a tabela gerada do relacionamento. N for N
	 * Name - nome da terceira tabela que armazenas as Chaves primarias
	 * joinColumns - a chave de endereço
	 * inverseJoinColumns - a chave de pessoa.
	 * 
	 * dessa forma a tabela é gerada com os IDs.
	 */
	@JoinTable(name = "PERSONS_ENDERECO", 
				joinColumns = @JoinColumn(name = "ID_ENDERECO"),
				inverseJoinColumns = @JoinColumn(name = "ID_PERSON")
	)
	@Column(name ="")
	private List<Person> pessoa;


	/**
	 * @return o atributo idAddresses
	 */
	public Long getIdAddresses() {
		return idAddresses;
	}


	/**
	 * @param idAddresses the idAddresses to set
	 */
	public void setIdAddresses(Long idAddresses) {
		this.idAddresses = idAddresses;
	}


	/**
	 * @return o atributo city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * @return o atributo street
	 */
	public String getStreet() {
		return street;
	}


	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}


	/**
	 * @return o atributo tipo
	 */
	public TypeAddress getTipo() {
		return tipo;
	}


	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TypeAddress tipo) {
		this.tipo = tipo;
	}


	/**
	 * @return o atributo pessoa
	 */
	public List<Person> getPessoa() {
		return pessoa;
	}


	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(List<Person> pessoa) {
		this.pessoa = pessoa;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAddresses == null) ? 0 : idAddresses.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (idAddresses == null) {
			if (other.idAddresses != null)
				return false;
		} else if (!idAddresses.equals(other.idAddresses))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Address [idAddresses=" + idAddresses + ", city=" + city + ", street=" + street + ", tipo=" + tipo
				+ ", pessoa=" + pessoa + "]";
	}
	
	
	
}
