package br.com.projeto.blog.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "COMENTARIOS")
public class Comentario extends AbstractPersistable<Long>{

	@Column(name = "TEXTO", nullable = false, columnDefinition = "TEXT")
	private String texto;
	
	@Column(name = "DATA_COMENTARIO")
	private LocalDateTime dataComentario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POSTAGEM_ID")
	private Postagem postagem;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USARIO_ID")
	private Usuario usuario;


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public LocalDateTime getDataComentario() {
		return dataComentario;
	}


	public void setDataComentario(LocalDateTime dataComentario) {
		this.dataComentario = dataComentario;
	}


	public Postagem getPostagem() {
		return postagem;
	}


	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}
	
	
	
}
