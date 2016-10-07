package br.com.projeto.blog.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.blog.entity.Postagem;
import br.com.projeto.blog.service.PostagemService;

/**Controller para a pagina inicial
 * 
 * @author Aluno
 *
 */
@Controller
public class HomeController {

	
	
	
	
	@Autowired
	public  PostagemService postagemService;
	
	
	
	/**Metodo para abrir o unico post
	 * 
	 * @param permalink
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{permalink}", method = RequestMethod.GET)
	public ModelAndView openPostagem(@PathVariable("permalink") String permalink, ModelMap model){
		
		Postagem postagem= postagemService.findByPermaLink(permalink);
		model.addAttribute("postagem", postagem);
		
		return new ModelAndView("post", model);
		
	}
	
	
	/**Metodo para recuperar sempre a pagina principal
	 * 
	 * passando todos os posts cadastrados
	 * 
	 * @param model - ModelMap para adicionar um atributo
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelMap model){
		
		List<Postagem> postagens = postagemService.findAll();
		
		model.addAttribute("Postagens", postagens);
		
		return new ModelAndView("posts", model);
	}
	
	/**classe para que busca todas as postagens referente a Categoria
	 * atravez do Link.
	 * 
	 * @param permaLink
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/categoria/{link}", method = RequestMethod.POST)
	public ModelAndView postByCategoria(@PathVariable("link") String permaLink,  ModelMap model){
		
		List<Postagem> postagens = postagemService.findByCategoria(permaLink);
		model.addAttribute("postagens", postagens);
		
		return new ModelAndView("posts", model);
		
	}
	
	/**Metodo para ir até o link das postagens daquele autor especifico
	 * 
	 * @param nome
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/autor/{nome}", method= RequestMethod.GET)
	public ModelAndView postsByAutor(@PathVariable("nome") String nome, ModelMap model){
		
		
		List<Postagem> postagens = postagemService.findByAutor(nome);
		
		model.addAttribute("postagens", postagens);
		
		return new ModelAndView("posts", model);
	}
	
	
}
