package br.com.projeto.blog.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**Utilizando os validadores do Hibernate- Validator
 * 
 * @author Danilo Silva
 *
 */
@Entity
@Table(name = "AUTORES")
public class Autor extends AbstractPersistable<Long>{
	
	@NotBlank //não permite enviar vazio ou spaço em Branco.
	@Length(min = 3, max= 50)//Limite de caracteres no campo minimo e maximo.
	@Column(nullable = false, unique = true, length = 50)
	private String nome;
	
	
	@NotBlank(message = "Este valor não aceita valores em Branco") //não permite enviar vazio ou spaço em Branco.
	@Length(min = 5, max= 255, message = "este campo aceita entre 5 e 255 caracteres")//Limite de caracteres no campo minimo e maximo.
	@Column(nullable = false, length = 255)
	private String biografia;
	
	@OneToOne
	@JoinColumn(name = "USUARIO_ID")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "autor")
	private List<Postagem> postagens;
	
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Postagem> getPostagem() {
		return postagens;
	}

	public void setPostagem(List<Postagem> postagens) {
		this.postagens = postagens;
	}
	
	
}
