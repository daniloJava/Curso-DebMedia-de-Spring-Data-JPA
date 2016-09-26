package br.com.projeto.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.blog.entity.Autor;
import br.com.projeto.blog.service.AutorService;

@Controller
@RequestMapping("autor")
public class AutorController {
	
	@Autowired
	private AutorService autorService;
	
	@RequestMapping(value = "/perfil/{id}", method = RequestMethod.GET)
	public ModelAndView getAutor(@PathVariable("id") Long id){
		ModelAndView view = new ModelAndView();
		
		Autor autor = autorService.findById(id);
		
		view.addObject("autor", autor);
		view.setViewName("autor/perfil");;
		
		return view;
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("autor") Autor autor){
		
		autorService.save(autor);
		return "redirect:/autor/perfil/"+ autor.getId();
		
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addAutor(@ModelAttribute("autor") Autor autor){
		return new ModelAndView("autor/cadastro");
		
	}
}
