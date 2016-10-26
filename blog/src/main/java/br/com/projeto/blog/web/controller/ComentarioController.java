package br.com.projeto.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.blog.entity.Categoria;
import br.com.projeto.blog.entity.Comentario;
import br.com.projeto.blog.entity.Postagem;
import br.com.projeto.blog.service.ComentarioService;
import br.com.projeto.blog.service.PostagemService;

/**Classe controller para comentários
 * 
 * @author Danilo Silva
 *
 */
@Controller
@RequestMapping("comentario")
public class ComentarioController {
	
	@Autowired
	private ComentarioService comentarioService;
	
	@Autowired
	private PostagemService postagemService;
	
	/**Adiciona Comentário 
	 * 
	 * @param comentario - Classe Comentario
	 * @param result - é o erro retornado pelo Validated
	 * @param permaLink
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(
			@ModelAttribute("comentario") @Validated Comentario comentario,
			BindingResult result,
			@RequestParam("permaLink") String permaLink
			){
		
		Postagem postagem = postagemService.findByPermaLink(permaLink);
		
		if(result.hasErrors()){
			
			return new ModelAndView("post", "postagem", postagem);
		}
		
		comentario.setPostagem(postagem);
		
		comentarioService.save(comentario);
		
		return new ModelAndView("redirect:/" + permaLink);
	}
}
