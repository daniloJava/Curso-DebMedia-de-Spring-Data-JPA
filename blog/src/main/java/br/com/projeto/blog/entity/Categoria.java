package br.com.projeto.blog.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "CATEGORIAS")
public class Categoria extends AbstractAuditoria<Long>{
	
	@NotBlank(message = "Este valor não aceita valores em Branco") //não permite enviar vazio ou spaço em Branco.
	@Length(min = 5, max= 255, message = "este campo aceita entre 5 e 255 caracteres")//Limite de caracteres no campo minimo e maximo.
	@Column(name = "DESCRICAO",  nullable = false, unique = true, length = 30)
	private String descricao;
	
	@Column(name = "PERMA_LINK", unique = true, nullable = false, length = 30)
	private String permaLink;
	
	@ManyToMany
	@JoinTable(
			name = "POSTAGENS_HAS_CATEGORIAS", // nome da tablea de relacionamento 
			joinColumns = @JoinColumn(name = "CATEGORIA_ID"),
			inverseJoinColumns = @JoinColumn(name = "POSTAGEM_ID")
	)
	private List<Postagem> postagem;


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPermaLink() {
		return permaLink;
	}

	public void setPermaLink(String permaLink) {
		this.permaLink = permaLink;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	
	
}
