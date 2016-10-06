package br.com.projeto.blog.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
}
