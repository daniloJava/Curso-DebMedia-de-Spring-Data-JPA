package br.com.projeto.blog.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.blog.entity.Avatar;
import br.com.projeto.blog.entity.Perfil;
import br.com.projeto.blog.entity.Usuario;
import br.com.projeto.blog.service.AvatarService;
import br.com.projeto.blog.service.UsuarioService;
import br.com.projeto.blog.web.editor.PerfilEditorSuporte;


@Controller
@RequestMapping("usuario")//URL Base que é redirecionada para esse controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AvatarService avatarService;
	
	/**Metodo Aplicar uma paginação de usuário com 
	 * uma ordenação.
	 * 
	 * @param pagina - pagina atual
	 * @param order - tipo de ordenação
	 * @param field - o campo que será ordenado.
	 * @return
	 */
	@RequestMapping(value = "/sort/{order}/{field}/page/{page}", method = RequestMethod.GET)
	public ModelAndView pageUsuario(@PathVariable("page") Integer pagina,
			@PathVariable("order") String order,
			@PathVariable("field") String field){
		
		ModelAndView view = new ModelAndView("usuario/list");
		Page<Usuario> page = usuarioService.findByPaginationByOrderField(pagina- 1, 5, field, order);
		view.addObject("page", page);
		view.addObject("urlPagination", "/usuario/sort/" + order + "/" + field + "/page");
		
		return view;
	}
	
	
	/**Metodo para aplicar a paginação do usuário
	 * 
	 * @param pagina - não precisa trabalhar com o ModelAttribute por que não tem um formulário
	 * 
	 * @return - a mesma pagina 
	 */
	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ModelAndView pageUsuarios(@PathVariable("page") Integer pagina){
		
		ModelAndView view = new ModelAndView("usuario/list");
		//Recupera do Service uma paginação 
		Page<Usuario> page = usuarioService.findByPagination(pagina -1 , 5);
		
		view.addObject("page", page);
		view.addObject("urlPagination", "/usuario/page");
		
		return view;
	}
	
	
	
	/**Metodo para fazer o resete de senha..
	 * 
	 * 
	 * @param id - se tiver um Id.
	 * @param user = usuario recuperado da pagina.
	 * @return
	 */
	@RequestMapping(value = {"/update/senha/{id}", "/update/senha"},
			method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateSenha(@PathVariable("id") Optional<Long> id ,
			@ModelAttribute("usuario") Usuario user){
		
		ModelAndView view = new ModelAndView();
		
		if(id.isPresent()){
			user = usuarioService.findById(id.get());
			view.addObject("usuario", user);
			view.setViewName("usuario/atualizar");
			return view;
		}
		
		usuarioService.updateSenha(user);
		view.setViewName("redirect:/usuario/perfil/" + user.getId());
		return view;
		
	}
	
	
	
	/**Metodo para fazer um update no perfil
	 * 
	 * é possivel passar mais de uma URL de forma de Array.
	 * 
	 * @PathVariable("id") - faz com  que esse metodo sempre tenha um ID na URL
	 * 
	 * 
	 */
	@RequestMapping(value = {"/update/{id}", "/update"}, 
			method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView update(@PathVariable("id") Optional<Long> id, 
			@ModelAttribute("usuario") Usuario user){
		ModelAndView view = new ModelAndView();
		//é um teste para verificar se existe um ID registrado vindo pela URL /update/{id}
		if(id.isPresent()){
			usuarioService.findById(id.get());
			view.addObject("usuario", user);
			view.setViewName("usuario/atualizar");
			return view;
		}
		
		// se vir por /update
		usuarioService.updateNomeAndEmail(user);
		
		view.setViewName("redirect:/usuario/perfil/" + user.getId());
		
		return view;
		
	}
	
	
	/**Classe para fazer as converções da opção de Enum
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Perfil.class, new PerfilEditorSuporte());
	}
	
	
	
	/**Metodoa para listar todos os perfils
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listUsuarios(ModelMap model){
		
		//List<Usuario> usuarios = usuarioService.findAll();
		//model.addAttribute("usuarios", usuarios);
		Page<Usuario> page = usuarioService.findByPagination(0 , 5);
		
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/usuario/page");
		
		return new ModelAndView("usuario/list", model); 
	}
	
	/**Recuperando o perfil pelo Id
	 * 
	 * @param id - esta pegaando o parametro {id} direto na URL
	 * @return
	 */
	@RequestMapping(value = "/perfil/{id}", method = RequestMethod.GET)
	public ModelAndView perfil(@PathVariable("id") Long id){
		ModelAndView view = new ModelAndView();
		
		Usuario user = usuarioService.findById(id);
		
		view.addObject("usuario", user);
		
		view.setViewName("usuario/perfil");
		
		return view;
		
	}
	

	/**Metodo para salvar o usuário.
	 * 
	 * @param user - Usuario do Formulario
	 * @param file - Recuperando o parametro com @RequestParam, que não faz parte do Objeto usuario.
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("usuario") Usuario user, 
					@RequestParam(value = "file", required = false) MultipartFile file){
		
		Avatar avatar = avatarService.getAvatarByUpload(file);
		
		user.setAvatar(avatar);
		
		usuarioService.save(user);
		//usando o Redirect é somente controler e o metodo dentro do controler
		return "redirect:/usuario/perfil/" + user.getId();
		
	}

	
	/**metodo para acesso ao formulário
	 * 
	 * @ModelAttribute("usuario") - é o metodo que estará lá 
	 * no formulário.
	 * 
	 * @param usuario
	 * @return
	 */
	//quando clica em usuario/add virá para esse controller
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("usuario") Usuario usuario){
		
		return new ModelAndView("usuario/cadastro");
	}

	
		
}
