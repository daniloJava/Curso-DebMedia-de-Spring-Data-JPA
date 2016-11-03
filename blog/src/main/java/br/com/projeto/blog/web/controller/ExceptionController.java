package br.com.projeto.blog.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public ModelAndView genericException(HttpServletRequest req, Exception exception){
		ModelAndView view = new ModelAndView("error");
		view.addObject("mensagem", "Ocorreu um erro");
		view.addObject("url", req.getRequestURL());
		view.addObject("excecao", exception);
		
		return view;
		
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView valorMaximoDaImagemException(HttpServletRequest req, MaxUploadSizeExceededException exception){
		ModelAndView view = new ModelAndView("error");
		view.addObject("mensagem", "O arquivo não pode ser maior que 100KB");
		view.addObject("url", req.getRequestURL());
		view.addObject("excecao", exception);
		
		return view;
	}
	
}

