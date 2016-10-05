package br.com.projeto.blog.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "POSTAGENS")
public class Postagem extends AbstractPersistable<Long>{
	
	@Column(name = "TITULO", nullable = false, unique = true, length = 60)
	private String titulo;
	
	//columnDefinition para especificar qual o tamanho LONGTEXT.
	@Column(name = "TEXTO", nullable = false, columnDefinition = "LONGTEXT")
	private String texto;
	
	@Column(name = "PERMA_LING", nullable = false, unique = true, length = 60)
	private String permaLink;
	
	@Column(name = "DATA_POSTAGEM", nullable = false)
	private LocalDateTime dataPostagem;
	
	@ManyToOne
	@JoinColumn(name = "AUTOR_ID")// adiciona o atributo que terá na tabela de postagem
	private Autor autor;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name= "POSTAGENS_HAS_CATEGORIAS",
			joinColumns = @JoinColumn(name= "POSTAGEM_ID"),
			inverseJoinColumns = @JoinColumn(name = "CATEGORIA_ID")
			
			)
	
	private List<Categoria> categorias;
	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getPermaLink() {
		return permaLink;
	}

	public void setPermaLink(String permaLink) {
		this.permaLink = permaLink;
	}

	public LocalDateTime getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(LocalDateTime dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	
	
}