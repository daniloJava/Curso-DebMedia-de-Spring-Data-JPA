package br.com.projeto.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.blog.entity.Categoria;
import br.com.projeto.blog.service.CategoriaService;

/**Controler para as transações com as categorias
 * 
 * @author Danilo Silva
 *
 */
@Controller
@RequestMapping("categoria")
public class CategoriaController {
	
	//injeção de dependencia de CategoriaService 
	@Autowired
	private CategoriaService service;
	
	/**Criar Paginação para categorias
	 * 
	 * Numero da pagina recuperaro pela URL
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ModelAndView pageCategorias(@PathVariable("page") Integer pagina
				,@ModelAttribute("categoria") Categoria catg){
		
		ModelAndView view = new ModelAndView("categoria/cadastro");
		//adicionando uma page com 5 linhas de tamano
		Page<Categoria> page = service.findByPaginetion(pagina - 1, 5);
		view.addObject("page", page);
		
		return view;
	}
	
	
	/**Cadastro de uma nova categoria.
	 * 
	 * @param categoria - recupera o atributo da pagina que é um objeto de Categoria
	 * @return ModelAndView - retorna uma pagina com um objeto com todos as categorias
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView cadastro(@ModelAttribute("categoria") Categoria categoria){
		ModelAndView view = new ModelAndView();
		
		Page<Categoria> page = service.findByPaginetion(0, 5);
		
		//view.addObject("categorias", service.findAll());
		view.addObject("page", page);
		view.setViewName("categoria/cadastro");
		
		return view;
		
	}
	
	/**Salvar uma nova categoria.
	 * 
	 * atravez de um objeto recuperado de um atributo
	 * 
	 * @param categoria
	 * @return - redireciona para a pagina de add uma categoria
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("Categoria")  Categoria categoria){
		service.saveOrUpdate(categoria);
		
		return "redirect:/categoria/add";
		
	}
	
	/**metodo mapeado para deletar uma categoria
	 * 
	 * @param id - recupera o id passado pela URL
	 * @return - redireciona para a pagina de add um nova categoria
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id){
		service.delete(id);
		return "redirect:/categoria/add";
		
	}
	
	/**Metodo para alterar uma categoria, atravez do id passado pela Url.
	 * 
	 * Lista todas as categorias cadastradas e um objeto categoria para que recupere o ID.
	 * 
	 * @param id - passado pela Url
	 * @param model ModelMap - modelo recuperado da pagina para adicionar novos objetos 
	 * @return retorna uma View para o cadastro e o modelo mapeado com os atributos
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model){
		Categoria categoria = service.findById(id);
		
		Page<Categoria> page = service.findByPaginetion(0, 5);

		model.addAttribute("categoria", categoria);
		
		//model.addAttribute("categorias", service.findAll());
		
		model.addAttribute("page", page);
		
		
		return new ModelAndView("categoria/cadastro", model);
	}
	
	
	
}
