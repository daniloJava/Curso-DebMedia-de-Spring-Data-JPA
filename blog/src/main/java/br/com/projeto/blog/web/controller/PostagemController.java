package br.com.projeto.blog.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.blog.entity.Postagem;
import br.com.projeto.blog.service.CategoriaService;
import br.com.projeto.blog.service.PostagemService;
import br.com.projeto.blog.web.editor.CategoriaEditorSuporte;
import br.com.projeto.blog.web.validator.PostagemAjaxValidator;

@Controller
@RequestMapping("postagem")
public class PostagemController {

	@Autowired
	private PostagemService postagemService;
	
	/**Processo de conversão.
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(List.class, 
				new CategoriaEditorSuporte(List.class, categoriaService));
	}
	
	@Autowired	
	private CategoriaService categoriaService;
	
	/**Recupera os dados os dados via JSON e salva os registros
	 * lembrando que os names do formulário precisa ser exatamente iguais
	 * pra que a conversão do JSON funcione.
	 * 
	 * @ResponseBody - tem como função transformar o Opjeto para um JSON.
	 * 
	 * @param postagem
	 * @return
	 */
	@RequestMapping(value = "/ajax/save", method = RequestMethod.POST)
	public @ResponseBody PostagemAjaxValidator saveAjax(@Validated Postagem postagem, 
						BindingResult result){
		
		PostagemAjaxValidator validator = new PostagemAjaxValidator();
		if(result.hasErrors()){
			//Status para que o JS entenda
			validator.setStatus("FAIL");
			//preencher as variaveis com as mensagens de erro.
			validator.validar(result);
			
			return 	validator;
		}
		
		postagemService.saveOrUpdadte(postagem);
		
		validator.setPostagem(postagem);
		
		return validator;
		
	}
	
	/**Abri a pagina que trabalha com ajax
	 * 
	 */
	@RequestMapping(value = "/ajax/add", method = RequestMethod.GET)
	public ModelAndView cadastroAjax(){
		ModelAndView view = new ModelAndView("postagem/cadastro-ajax");
		view.addObject("categorias", categoriaService.findAll());
		
		return view;
	}
	
	/**Metodo que recupera a Paginação  por ajax
	 * 
	 * @param pagina
	 * @return
	 */
	@RequestMapping(value = "/ajax/titulo/{titulo}/page/{page}", method = RequestMethod.GET)
	public ModelAndView searchByAjax(@PathVariable("titulo") String titulo,
									@PathVariable("page") Integer pagina){
		ModelAndView view = new ModelAndView("postagem/table-rows");
		
		Page<Postagem> page = postagemService.findByTitulo(pagina -1, 5, titulo);
		view.addObject("page", page);
		
		return view;
	}
	
	/**Com Ajax, agora ele recuoera as postagems e aplica no Table-Rows,
	 * 
	 * @param pagina
	 * @return
	 */
	@RequestMapping(value = "/ajax/page/{page}", method = RequestMethod.GET)
	public ModelAndView pagePostagens(@PathVariable("page") Integer pagina){
		
		//Agora com Ajax, ele chama paenas o Include que foi aplicado.
		ModelAndView view = new ModelAndView("postagem/table-rows");
		
		Page<Postagem> page = postagemService.findByPaginetion(pagina -1, 5);
		
		view.addObject("page", page);
//		view.addObject("urlPagination", "/postagem/page");
		
		return view;
		
	}
	
	
	/**Metodo para pe update das postagens
	 * 
	 * @param id - recupera o ID pela url
	 * @param model - recupera um Model Map pra alogar Atributps
	 * @return Retorna para a pagina Lista atualizado.
	 */
	@RequestMapping(value = "/update/{id}" , method = RequestMethod.GET)
	public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model){
		
		Postagem postagem = postagemService.findById(id);
		
		model.addAttribute("postagem", postagem);
		model.addAttribute("categorias", categoriaService.findAll());
		
		return new ModelAndView("postagem/cadastro", model);
	}
	
	/**Metodo de deleção de um registro passado por parametro
	 * 
	 * @param id
	 * @return = redireciona para a pagina List.
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id){
		postagemService.delete(id);
		
		return "redirect:/postagem/list";
	}
	
	/**Listando todas as postagens adicionando em uma paginação
	 * um atributo para identificação 
	 * 
	 * @param model
	 * @return - redireciona para a pagina List.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listPostagens(ModelMap model){
		
		Page<Postagem> page = postagemService.findByPaginetion(0, 5);
		
//		Antigo:
//		model.addAttribute("postagem", postagemService.findAll());
		model.addAttribute("page", page);
//		model.addAttribute("urlPagination", "/postagem/page");
		
		return new ModelAndView("postagem/list", model);
	}
	
	/**salvando as postagens pelo atributo adicionado.
	 * 
	 * @param postagem - recebe postagem
	 * @param result - resultado de algum erro
	 * 
	 * @return - retorna para a pagina de cadastro de postagem se estiver errado ou para lista de postagem
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("postagem") @Validated Postagem postagem,
			BindingResult result){
		
		if(result.hasErrors()){
			
			return new ModelAndView("postagem/cadastro", "categorias", categoriaService.findAll());
		}
		postagemService.saveOrUpdadte(postagem);
		
		return new ModelAndView("redirect:/postagem/list");
	}
	
	/**Adicionar um nova Categoria
	 * 
	 * @param postagem
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView cadastro(@ModelAttribute("postagem") Postagem postagem){
		ModelAndView view = new ModelAndView("postagem/cadastro");
		view.addObject("categorias", categoriaService.findAll());
		
		return view;
	}
}
