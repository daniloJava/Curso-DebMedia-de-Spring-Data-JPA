package br.com.projeto.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**Controller para usuário Logado.
 * 
 * @author tapower
 *
 */
@Controller
@RequestMapping("auth")
public class LoginController {
	
	/**Metodo para ir para a pagina de login.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String loginPage(){
		return "login";
	}
	
	
	/**Metodo para realizar o login do metorodo.
	 * 
	 * @param error - não é obrigado a estar no Request
	 * @param logout- não é obrigado a estar no Request
	 * @param model - adicionar qualquer parametro que precise enviar no ModelAndView
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) boolean error, 
			@RequestParam(value = "logout", required = false) boolean logout , ModelMap model) {
		
		// validando se entra no sistema
		if(error){
			model.addAttribute("error", "Login Invalido, Senha ou Login incorretos");
			return new ModelAndView("login", model);
		}
		
		// realizando o logout
		if(logout){
			model.addAttribute("logout", "Usuário saiu do Sistema com sucesso");
			return new ModelAndView("login", model);
		}
		
		return new ModelAndView("redirect:/");
	}
	
	/**Metodo para que qualquer metodo de acesso negado.
	 * ou seja, quand sestá logado no sistema e tenta acessar coisa que não pode
	 * 
	 * 
	 * @return - retornoa uma mensagem de de acesso negado.
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView acessoNegado(){
		
		return new ModelAndView("error", "mensagem", "Acesso Negado, area Restrita.");
	}
}
