package br.com.projeto.blog.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.projeto.blog.entity.Avatar;
import br.com.projeto.blog.repository.AvatarRepository;

@Service
//Operação controlada no Service tanto commit quanto Rowback
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AvatarService {

	@Autowired
	private AvatarRepository repository;
	
	/**Metodo para salvar um arquivo 
	 * ou dar um update, utilizando que o Spring gerencie
	 * as atualizações, se em um banco que existe um relacionamento e
	 * fizer uma atualização em uma tabela e der erro na outra
	 * ele mesmo gerencia essas mudanças do RowBack.
	 * 
	 * @param avatar
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdate(Avatar avatar){
		
		repository.save(avatar);
	}
	
	
	/**para ter acesso ao objeto Avatar quando for ter acesso por um insert, 
	 * ele cria um objeto recuperado na pagina.
	 * 
	 * @param file
	 * @return
	 */
	public Avatar getAvatarByUpload(MultipartFile file){
		Avatar avatar = new Avatar();
		
		if(file != null && file.getSize() > 0){
			try {
				avatar.setTitulo(file.getOriginalFilename());
				avatar.setTipo(file.getContentType());
				avatar.setAvatar(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		
		return avatar;
	}
	
	
}
