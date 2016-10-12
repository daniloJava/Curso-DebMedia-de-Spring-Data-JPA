package br.com.projeto.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.projeto.blog.entity.Comentario;
import br.com.projeto.blog.entity.Postagem;
import br.com.projeto.blog.service.ComentarioService;
import br.com.projeto.blog.service.PostagemService;

@Controller
@RequestMapping("comentario")
public class ComentarioController {
	
	@Autowired
	private ComentarioService comentarioService;
	
	@Autowired
	private PostagemService postagemService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("comentario") Comentario comentario,
			@RequestParam("permaLink") String permaLink
			){
		Postagem postagem = postagemService.findByPermaLink(permaLink);
		comentario.setPostagem(postagem);
		
		comentarioService.save(comentario);
		
		return "redirect:/" + permaLink;
	}
}
