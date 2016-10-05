package br.com.projeto.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.blog.entity.Categoria;
import br.com.projeto.blog.service.CategoriaService;

@Controller
@RequestMapping("categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView cadastro(@ModelAttribute("categoria") Categoria categoria){
		ModelAndView view = new ModelAndView();
		
		view.addObject("categorias", service.findAll());
		view.setViewName("categoria/cadastro");
		
		return view;
		
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("Categoria")  Categoria categoria){
		service.saveOrUpdate(categoria);
		
		return "redirect:/categoria/add";
		
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id){
		service.delete(id);
		return "redirect:/categoria/add";
		
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model){
		Categoria categoria = service.findById(id);
		
		model.addAttribute("categoria", categoria);
		model.addAttribute("categorias", service.findAll());
		
		return new ModelAndView("categoria/cadastro", model);
	}
	
}
