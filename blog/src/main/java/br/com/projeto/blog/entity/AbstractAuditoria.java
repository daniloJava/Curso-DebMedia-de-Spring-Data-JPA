package br.com.projeto.blog.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**Classe Responsavel para armazenar no banco de dados
 * todos os registros selecionados por Log.
 * 
 * 
 * @EntityListeners - Responsavel por transformar para uma classe listener que fica esperando 
 * por uma alteração que foi mapeada e faz a persistencia no banco de dados	
 * 
 * 
 * AuditingEntityListener.class - classe para utilizar como ouvinte para o Spring
 * 
 * PK - Id da Classe e é serializable
 * 
 * AbstractPersistable<PK> - quando for trabalhar com uma entidade, será substituido pelo nosso Abstract Auditoria
 * 
 * @author Danilo Silva
 *
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractAuditoria<PK extends Serializable> extends AbstractPersistable<PK>{

	@Column(name = "CREATED_BY")
	@CreatedBy //especifica da auditoria que vai receber os dados que está inserindo ou alterando a entidade 
	private String createdBy;
	
	@Column(name = "CREATED_DATE")
	@CreatedDate  
	private LocalDateTime createdDate;
	
	@Column(name = "LAST_MODIFIED_BY")
	@LastModifiedBy  
	private String lastModifiedBy;//quem fez a ultima modificação
	
	@Column(name = "LAST_MODIFIED_DATE")
	@LastModifiedDate  
	private LocalDateTime lastModifeidDate; //data de quem alterou o objeto

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public LocalDateTime getLastModifeidDate() {
		return lastModifeidDate;
	}

	public void setLastModifeidDate(LocalDateTime lastModifeidDate) {
		this.lastModifeidDate = lastModifeidDate;
	}

	@Override
	public void setId(PK id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}
	
	
	
}



