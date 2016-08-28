package br.com.CursoSpringDataJPA.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name = "DOCUMENTS")
public class Document implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "ID_DOCUMENT")
	private Long idDocument;
	
	@Column(name= "CPF_DOCUMENT", nullable = false, unique = true, length = 14)
	private String cpfDocument;
	
	@Column(name= "RG_DOCUMENT", unique = true)
	private Integer rgDocument;

	public Document(){
	}
	
	public Document(String cpfDocument, Integer rgDocument) {
		super();
		this.cpfDocument = cpfDocument;
		this.rgDocument = rgDocument;
	}


	/**
	 * @return o atributo idDocument
	 */
	public Long getIdDocument() {
		return idDocument;
	}


	/**
	 * @param idDocument the idDocument to set
	 */
	public void setIdDocument(Long idDocument) {
		this.idDocument = idDocument;
	}


	/**
	 * @return o atributo cpfDocument
	 */
	public String getCpfDocument() {
		return cpfDocument;
	}


	/**
	 * @param cpfDocument the cpfDocument to set
	 */
	public void setCpfDocument(String cpfDocument) {
		this.cpfDocument = cpfDocument;
	}


	/**
	 * @return o atributo rgDocument
	 */
	public Integer getRgDocument() {
		return rgDocument;
	}


	/**
	 * @param rgDocument the rgDocument to set
	 */
	public void setRgDocument(Integer rgDocument) {
		this.rgDocument = rgDocument;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpfDocument == null) ? 0 : cpfDocument.hashCode());
		result = prime * result + ((idDocument == null) ? 0 : idDocument.hashCode());
		result = prime * result + ((rgDocument == null) ? 0 : rgDocument.hashCode());
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
		Document other = (Document) obj;
		if (cpfDocument == null) {
			if (other.cpfDocument != null)
				return false;
		} else if (!cpfDocument.equals(other.cpfDocument))
			return false;
		if (idDocument == null) {
			if (other.idDocument != null)
				return false;
		} else if (!idDocument.equals(other.idDocument))
			return false;
		if (rgDocument == null) {
			if (other.rgDocument != null)
				return false;
		} else if (!rgDocument.equals(other.rgDocument))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Document [idDocument=" + idDocument + ", cpfDocument=" + cpfDocument + ", rgDocument=" + rgDocument
				+ "]";
	}
	
	
	
}
