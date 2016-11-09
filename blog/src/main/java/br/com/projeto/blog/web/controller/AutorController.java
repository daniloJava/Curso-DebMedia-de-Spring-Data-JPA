package br.com.projeto.blog.web.controller;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.blog.entity.Autor;
import br.com.projeto.blog.entity.Usuario;
import br.com.projeto.blog.entity.UsuarioLogado;
import br.com.projeto.blog.service.AutorService;
import br.com.projeto.blog.service.UsuarioService;

@Controller
@RequestMapping("autor")
public class AutorController {
	
	@Autowired
	private AutorService autorService;

	@Autowired
	private UsuarioService usuarioService;
	
	/**Metodo para adicionar uma paginação na lista de autores
	 * 
	 * @param pagina - recupera a pagina indicada
	 * @return ModelAndView - com o atributo page mapeado com o registros.
	 */
	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ModelAndView pageAutores(@PathVariable("page") Integer pagina){
		ModelAndView view = new ModelAndView("autor/perfil");
		
		Page<Autor> page = autorService.findByPagination(pagina -1, 5);
		
		view.addObject("page", page);
		view.addObject("urlPagination", "/autor/page");
		return view;
		
	}
	
	/**Metodo para deletar o Autor.
	 * 
	 * @param id - recupera paramentro pela url
	 * @return String - redireciona para a pagina adicionar usuário.
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		
		autorService.delete(id);
		
		return "redirect:/autor/add";
	}
	
	
	/**conseguindo alterar os dados do Autor.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET )
	public ModelAndView preUpdate(@PathVariable("id") Long id){
		ModelAndView vieew = new ModelAndView();
		Autor autor = autorService.findById(id);
		
		vieew.addObject("autor",autor);
		vieew.setViewName("autor/perfil");
		return vieew;
	}
	
	
	
	/**Buscando um Autor  ou todos os autroes usando o Optionl,
	 * para descobrir as condi��es do id..
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"/perfil/{id}", "/list"}, method = RequestMethod.GET)
	public ModelAndView getAutor(@PathVariable("id") Optional<Long> id){
		ModelAndView view = new ModelAndView("autor/perfil");
		
		if(id.isPresent()){
			Autor autor = autorService.findById(id.get());
			view.addObject("autores", Arrays.asList(autor));
			
		}else{
			
			Page<Autor> page = autorService.findByPagination(0, 5);
			view.addObject("page", page);
			view.addObject("urlPagination", "/autor/page");
//			Anterior:
//			List<Autor> autores = autorService.findAll();
//			view.addObject("autores", autores);
		}
		
		return view;
	}
	
	/**Metodo para Salvar o Autor.
	 * 
	 * o BindingResult - é a classe de validação que recebe o erro  
	 * 
	 * @param autor
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("autor") @Validated Autor autor, BindingResult result
					, @AuthenticationPrincipal() UsuarioLogado logado){
		
		if(result.hasErrors())
			return "autor/cadastro";
		
		if(logado.getId() != null){
			Usuario usuario = usuarioService.findById(logado.getId());
			autor.setUsuario(usuario);
		}
		
		autorService.save(autor);
		return "redirect:/autor/perfil/"+ autor.getId();
		
	}
	
	
	/**Metodo para adicionar um autor.
	 * 
	 * @param autor
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addAutor(@ModelAttribute("autor")  Autor autor,
				@AuthenticationPrincipal() UsuarioLogado logado){
		
		autor = autorService.findByUsuario(logado.getId());
		
		if(autor == null){
			return new ModelAndView("autor/cadastro");
		}
		
		
		return new ModelAndView("redirect:/autor/perfil/"+autor.getId());
		
		
	}
}
