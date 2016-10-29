package br.com.projeto.blog.entity;

import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "AVATARES")
public class Avatar extends AbstractPersistable<Long>{
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false)
	private String tipo;
	
	@Lob
	@Column(nullable = false)
	private byte[] avatar;
	
	@Transient
	private MultipartFile file;
	

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	
}
