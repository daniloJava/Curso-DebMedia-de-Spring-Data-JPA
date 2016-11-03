package br.com.projeto.blog.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**Classe Controller para tratar as exeções 
 * 
 *  da Pagina.
 * 
 * @author Danilo Silva
 *
 */
@ControllerAdvice
public class ExceptionController {

	/**Tratamento Generico, qualque Exeção é chamado esse metodo
	 * 
	 * @param req 
	 * @param exception
	 * @return - para uma pagina de erro com as mensagens
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView genericException(HttpServletRequest req, Exception exception){
		ModelAndView view = new ModelAndView("error");
		view.addObject("mensagem", "Ocorreu um erro");
		view.addObject("url", req.getRequestURL());
		view.addObject("excecao", exception);
		
		return view;
		
	}
	
	/**Tratamento de uma exeção especifica.
	 * nesse caso é da exeção da seleção de um arquivo muito grande do limite
	 * 
	 * @param req
	 * @param exception
	 * @return -  para uma pagina de erro com as mensagens
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView valorMaximoDaImagemException(HttpServletRequest req, MaxUploadSizeExceededException exception){
		ModelAndView view = new ModelAndView("error");
		view.addObject("mensagem", "O arquivo não pode ser maior que 100KB");
		view.addObject("url", req.getRequestURL());
		view.addObject("excecao", exception);
		
		return view;
	}
	
	/**Metodo para a exeção de erro 404
	 * 
	 * Exeção chamada de view page, por conta que não chega até o controller
	 * 
	 * @param req
	 * @param exception
	 * @return - para uma pagina de erro com as mensagens
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND)
	public ModelAndView noHandlerFoundException(HttpServletRequest req, 
			NoHandlerFoundException exception){
		
		ModelAndView view = new ModelAndView("error");
		
		view.addObject("mensagem", "OPS.! <br> Essa Pagina não existe por aqui");
		view.addObject("url", req.getRequestURL());
		view.addObject("excecao", exception);
		
		return view;
		
		
	}
	
}

