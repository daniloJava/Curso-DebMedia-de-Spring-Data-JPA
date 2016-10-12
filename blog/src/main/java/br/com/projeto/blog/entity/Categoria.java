package br.com.projeto.blog.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "CATEGORIAS")
public class Categoria extends AbstractPersistable<Long>{
	
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

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

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
