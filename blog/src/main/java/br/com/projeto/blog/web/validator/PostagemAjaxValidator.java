package br.com.projeto.blog.web.validator;

import org.springframework.validation.BindingResult;

import br.com.projeto.blog.entity.Postagem;

/**Processo para validar um formulário 
 * enviado por Ajax, Como todo o fluxo dos dados 
 * é comunicação com o JS e com HTML, não tendo os dados.
 * 
 * então é criado os atributos.
 * 
 * @author Danilo Silva
 *
 */
public class PostagemAjaxValidator {

	private Postagem postagem;
	
	private String status;

	private String tituloError;
	
	private String textoError;

	/**Metodo para validar os campos.
	 * 
	 * testando os atributos, ele simplismente instancia com a mensagem padão,
	 * 
	 * 
	 * @param result - é Objeto que será informado .
	 */
	public void validar(BindingResult result){
		if(result.hasFieldErrors("titulo")){
			this.tituloError = result.getFieldError("titulo").getDefaultMessage();
		}
		if(result.hasFieldErrors("texto")){
			this.textoError = result.getFieldError("texto").getDefaultMessage();
		}

	}
	
	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTituloError() {
		return tituloError;
	}

	public void setTituloError(String tituloError) {
		this.tituloError = tituloError;
	}

	public String getTextoError() {
		return textoError;
	}

	public void setTextoError(String textoError) {
		this.textoError = textoError;
	}
	
	
	
}
