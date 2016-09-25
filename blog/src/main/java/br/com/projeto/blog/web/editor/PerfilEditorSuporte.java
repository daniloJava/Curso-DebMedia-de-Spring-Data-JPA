package br.com.projeto.blog.web.editor;

import java.beans.PropertyEditorSupport;

import br.com.projeto.blog.entity.Perfil;

public class PerfilEditorSuporte extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.equals("ADIMIN"))
			super.setValue(Perfil.ADIMIN);
		else if (text.equals("AUTOR"))
			super.setValue(Perfil.AUTOR);
		else
			super.setValue(Perfil.LEITOR);

	}

}
