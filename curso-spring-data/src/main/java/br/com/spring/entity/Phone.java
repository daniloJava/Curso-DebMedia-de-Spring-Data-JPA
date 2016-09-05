package br.com.spring.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PHONES")
public class Phone implements Serializable{

	public enum TypePhones{
		RESIDENCIAL, CELULAR, COMERCIAL
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PHONE")
	private Long idPhone;
	
	//tipo de dados que será armazenados no banco pelo Enum
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_PHONE", nullable = false)
	private TypePhones tipoPhone;
	
	@Column(name = "NUMERO_PHONE", nullable = false)
	private String Numero;
	
	/*Uma pessoa pode ter muitos telefones cadastrados
	 * 
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PESSOA_ID")
	private Person pessoa;
	
	
	public Phone(){
		super();
	}

	
	public Phone(TypePhones tipoPhone, String numero) {
		super();
		this.tipoPhone = tipoPhone;
		Numero = numero;
	}


	/**
	 * @return o atributo idPhone
	 */
	public Long getIdPhone() {
		return idPhone;
	}

	/**
	 * @param idPhone the idPhone to set
	 */
	public void setIdPhone(Long idPhone) {
		this.idPhone = idPhone;
	}

	/**
	 * @return o atributo tipoPhone
	 */
	public TypePhones getTipoPhone() {
		return tipoPhone;
	}

	/**
	 * @param tipoPhone the tipoPhone to set
	 */
	public void setTipoPhone(TypePhones tipoPhone) {
		this.tipoPhone = tipoPhone;
	}

	/**
	 * @return o atributo numero
	 */
	public String getNumero() {
		return Numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		Numero = numero;
	}

	/**
	 * @return o atributo pessoa
	 */
	public Person getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Person pessoa) {
		this.pessoa = pessoa;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Phone [idPhone=" + idPhone + ", tipoPhone=" + tipoPhone + ", Numero=" + Numero + ", pessoa=" + pessoa
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPhone == null) ? 0 : idPhone.hashCode());
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
		Phone other = (Phone) obj;
		if (idPhone == null) {
			if (other.idPhone != null)
				return false;
		} else if (!idPhone.equals(other.idPhone))
			return false;
		return true;
	}
	
	
	
}
