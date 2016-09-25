package br.com.projeto.blog.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		List<Usuario> usuarios = usuarioService.findAll();
		
		model.addAttribute("usuarios", usuarios);
		
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
