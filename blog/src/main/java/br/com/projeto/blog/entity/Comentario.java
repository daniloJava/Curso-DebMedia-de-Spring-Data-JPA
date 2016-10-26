package br.com.projeto.blog.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "COMENTARIOS")
public class Comentario extends AbstractPersistable<Long> implements Comparable<Comentario>{

	@NotBlank(message = "Este valor não aceita valores em Branco") //não permite enviar vazio ou spaço em Branco.
	@Length(min = 5, max= 255, message = "Seu comentário tem que ter entre 5 e 255 caracteres")//Limite de caracteres no campo minimo e maximo.
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


	/**Metodo de ordenaçao do comentario
	 * 
	 */
	public int compareTo(Comentario comentario) {
		return this.dataComentario.compareTo(comentario.getDataComentario());
	}
	
	
	
}
