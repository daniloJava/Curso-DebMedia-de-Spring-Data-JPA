package br.com.projeto.blog.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.blog.entity.Avatar;
import br.com.projeto.blog.entity.Usuario;
import br.com.projeto.blog.service.AvatarService;
import br.com.projeto.blog.service.UsuarioService;

@Controller
@RequestMapping("avatar")
public class AvatarController {

	private static final Logger LOG = Logger.getLogger(Avatar.class);
	@Autowired
	private AvatarService avatarService;
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("avatar") Avatar avatar, 
						@RequestParam("file") MultipartFile file){
		//recuperando o id do avatar
		Long id = avatar.getId();
		//recupero o avatar pelo arquivo
		avatar = avatarService.getAvatarByUpload(file);
		avatar.setId(id);
		
		avatarService.saveOrUpdate(avatar);
		
		//fazendo uma consulta atravez do objeto avatar.
		Usuario user =  usuarioService.findByAvatar(avatar);
		
		
		
		return "redirect:/usuario/perfil/" + user.getId();
	}
	
	/**Metodo para somente consulta antes do Update
	 * 
	 * @param id
	 * @param avatar
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView preUpdate(@PathVariable("id") Long id, 
			@ModelAttribute("avatar") Avatar avatar){
		
		ModelAndView view = new ModelAndView("avatar/atualizar");
		//retornando o id para o avatar.
		view.addObject("id", id);
		
		return view;
	}
	
	
	

	/**Inserindo o coneudo do Avatar na lista 
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> loadAvatar(@PathVariable("id") Long id) {
		Avatar avatar = avatarService.findById(id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf(avatar.getTipo()));

		InputStream is = new ByteArrayInputStream(avatar.getAvatar());
		try {
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(is), headers, HttpStatus.OK);
		} catch (IOException e) {
			LOG.error("Ocorreu um erro ao recuperar o Avatar", e.getCause());

		} finally {

			try {
				is.close();
			} catch (Exception e) {
				LOG.error("Ocorreu um erro ao Fechar o Stream do arquivo", e.getCause());
			}
		}
		return null;
	}
	
}
