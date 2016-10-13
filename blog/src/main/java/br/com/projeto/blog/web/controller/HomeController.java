package br.com.projeto.blog.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.blog.entity.Comentario;
import br.com.projeto.blog.entity.Postagem;
import br.com.projeto.blog.service.PostagemService;

/**Controller para a pagina inicial
 * 
 * @author Danilo Silva
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	public  PostagemService postagemService;
	
	/**Metodo SObrecarregado para organizar a 
	 * paginação com a URL correta
	 * 
	 * @param texto
	 * @param pagina
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/search/texto/{texto}/page/{page}", method = RequestMethod.GET)
	public ModelAndView search(@PathVariable("texto") String texto, 
							@PathVariable("page") Integer pagina, ModelMap model) {

		Page<Postagem> page = postagemService.findByTexto(pagina -1, 5, texto);
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/search/texto/"+ texto +"/page");

		return new ModelAndView("post", model);

	}

	/**Metodo para colocar o buscador, do metodo tradicional
	 * como o fomulário do HTML.
	 * 
	 * @param texto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam("texto") String texto, ModelMap model){
		
		Page<Postagem> page = postagemService.findByTexto(0, 5, texto);
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/search/texto/"+ texto +"/page");
		
		return new ModelAndView("post", model);
		
	}
	
	/**Metodo para abrir o unico post
	 * 
	 * @param permalink
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{permaLink}", method = RequestMethod.GET)
	public ModelAndView openPostagem(
			@ModelAttribute("comentario") Comentario comentario,
			@PathVariable("permaLink") String permalink, ModelMap model){
		
		Postagem postagem= postagemService.findByPermaLink(permalink);
		model.addAttribute("postagem", postagem);
		model.addAttribute("urlPagination", "/page");
		
		return new ModelAndView("posts", model);
		
	}
	/**Metodo para adicionar a paginação na Home.
	 * 
	 * @param pagina
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ModelAndView pageHome(@PathVariable("page") Integer pagina ,ModelMap model){
		
		Page<Postagem> page = postagemService.findByPaginetion(pagina - 1, 5);
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/page");
		
		return new ModelAndView("post", model);
		
	}
	
	/**Metodo para recuperar sempre a pagina principal
	 * 
	 * passando todos os posts cadastrados
	 * 
	 * @param model - ModelMap para adicionar um atributo
	 * @return
	 */
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public ModelAndView home(ModelMap model,
			@ModelAttribute("comentario") Comentario comentario
			){
		
//		Antigo:
//		List<Postagem> postagens = postagemService.findAll();
//		model.addAttribute("postagens", postagens);
		
		Page<Postagem> page = postagemService.findByPaginetion(0, 5);
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/page");
		
		return new ModelAndView("post", model);
	}
	
	/**classe para que busca todas as postagens referente a Categoria
	 * atravez do Link.
	 * 
	 * @param permaLink
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/categoria/{link}/page/{page}", method = RequestMethod.GET)
	public ModelAndView postByCategoria(@PathVariable("link") String permaLink,  
			@PathVariable("page") Integer pagina,ModelMap model){
		
//		Antigo:
//		List<Postagem> postagens = postagemService.findByCategoria(permaLink);
//		model.addAttribute("postagens", postagens);
		
		Page<Postagem> page = postagemService.findByPaginetionByCategoria(pagina -1, 5, permaLink);
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/categoria/" + permaLink + "/page");
		
		return new ModelAndView("post", model);
		
	}
	
	/**Metodo para ir at� o link das postagens daquele autor especifico
	 * 
	 * @param nome
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/autor/{id}/page/{page}", method= RequestMethod.GET)
	public ModelAndView postsByAutor(@PathVariable("id") Long id, ModelMap model,
					@PathVariable("page") Integer pagina){
//		Antigo
//		List<Postagem> postagens = postagemService.findByAutor(nome);
//		model.addAttribute("postagens", postagens);

		Page<Postagem> page = postagemService.findByPaginetionByAutor(pagina -1 , 5, id);
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/autor/" + id + "/page");
		
		return new ModelAndView("post", model);
	}
	
	
}
