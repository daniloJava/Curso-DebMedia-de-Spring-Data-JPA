package br.com.projeto.blog.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.projeto.blog.entity.Avatar;

public class AvatarValidator implements Validator{
	
	/**Serve somente para testar se o objeto que esta recebendo 
	 * na validação é o mesmo objeto que você quer validar
	 * 
	 */
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return Avatar.class.equals(arg0);
	}

	/**Onde cria as regras de validação
	 * 
	 * @param arg0 - é o Objeto recebido para validação
	 * @param arrors = são os campos do formulário que verifica se está com erro ou não
	 * 
	 */
	public void validate(Object arg0, Errors errors) {
		
		Avatar avatar = (Avatar) arg0;
		if(avatar.getFile()!= null){
			if(avatar.getFile().getSize() == 0){
				errors.rejectValue("file", "file", "Selecione um avatar até 100kb");
			}
		}
		
	}

}
