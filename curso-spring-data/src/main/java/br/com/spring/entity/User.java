package br.com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**A ideia é usar a classe do Spring AbstractPersistable.
 * faz uso da OOp para reduzir a quantidade de codigo nesses tipos de classe Entity.
 * 
 * Ele já implemente o Serializable
 * aos atibutos getters e setter do ID.
 * 
 * isNull seria se ele já existe um Id. como se fosse o toString
 * tem o Haschode, e o Equals tambem implementado.
 * 
 * @author Danilo Silva
 *
 */
@Entity
@Table(name="USERS")
public class User extends AbstractPersistable<Long>{
	
	
	@Column(name = "USERNAME", nullable = false)
	private String userName;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

		
	
	
}
