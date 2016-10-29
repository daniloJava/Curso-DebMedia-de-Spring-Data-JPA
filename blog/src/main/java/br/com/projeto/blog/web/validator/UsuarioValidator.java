package br.com.projeto.blog.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.projeto.blog.entity.Usuario;

public class UsuarioValidator implements Validator{

	/**Serve somente para testar se o objeto que esta recebendo 
	 * na validação é o mesmo objeto que você quer validar
	 * 
	 */
	public boolean supports(Class<?> arg0) {
		return Usuario.class.equals(arg0);
	}
	
	/**Onde cria as regras de validação
	 * 
	 * @param arg0 - é o Objeto recebido para validação
	 * @param arrors = são os campos do formulário que verifica se está com erro ou não
	 * 
	 */
	public void validate(Object arg0, Errors errors) {
		
		Usuario usuario = (Usuario) arg0;
		if(usuario.getNome()!= null){
			//Rejeita se for fazio ou com espaços em branco, como se fosse o Blank
			/*Os parametros são os campos dos formulários
			 * o nome do Path la no input
			 * Nome do campo de erro lá na pagina
			 * e a mensagem 
			*/
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "nome", "Este campo é obrigatorio");
		}
		
		if(usuario.getEmail()!=null){
			//cria uma ER para comparar o email
			Pattern pattern = Pattern.compile(".+@.+\\..+");
			//ablica no atributo do usuario
			Matcher matcher = pattern.matcher(usuario.getEmail());
			
			if(!matcher.matches()){
				/**Primeiro parametro,
				 * é o path do input
				 * e deposi do erro
				 * e deposi mensagem
				 * 
				 */
				errors.rejectValue("email", "email", "Insira um e-mail valido");
			}
			
		}
		
		if(usuario.getSenha()!=null){
			if(usuario.getSenha().length() > 8 || usuario.getSenha().length() < 5){
				errors.rejectValue("senha", "senha", "A senha deve conter entre 5 e 8 caracteres");
			}
		}
			
		if(usuario.getFile() != null){
			if(usuario.getFile().getSize() == 0){
				errors.rejectValue("file", "file", "Selecione um imagen até 100KBs");
			}
		}
		
	}

}
