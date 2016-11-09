package br.com.projeto.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.blog.entity.Autor;
import br.com.projeto.blog.repository.AutorRepository;

/**Service, metodo de somente os serviçõs que faz a ligação com o 
 * objeto Repository.
 * nesse metoro contem os servições de usuário necessarios.
 * 
 * @author Danilo Silva
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AutorService {
	
	@Autowired
	private AutorRepository repository;
	
	
	public Page<Autor> findByPagination(int page, int size){
		Pageable pageable = new PageRequest(page, size);
		
		return repository.findAllByOrderByNomeAsc(pageable);
	}
	
	
	/**Metodo para salvar o Autor
	 * 
	 * A ideia de usar um @Transactional(readOnly = false)
	 * é que por padrão, o Spring-Data intende que todos os metodos
	 * sao apenas por consulta, aplicando false, podemos usar o metodo para fazer alteração. 
	 * 
	 * @param autor - autor com os atributos
	 */
	@Transactional(readOnly = false)
	public void save(Autor autor){
		if(autor.getId() == null)
			repository.save(autor);
		else
			repository.updateNomeAndBiografia(autor.getNome(), autor.getBiografia(), autor.getId());
	}
	
	/**Lista todos os Autores
	 * 
	 * @return
	 */
	public List<Autor> findAll(){
		return repository.findAll();
	}
	
	/**Procura o Autor por ID
	 * 
	 * @param id - id para Procura
	 * @return Autor - Apenas um autor
	 */
	public Autor findById(Long id){
		return repository.findOne(id);
		
	}
	/**Procura o Autor pelo Nome
	 * 
	 * @param nome String - nome pa ser procurado
	 * @return Autor - um Registro com o nome
	 */
	public Autor findByNome(String nome){
		return repository.findByNome(nome);
	}
	
	/**metodo par adeletar o usuário pelo ID
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.delete(id);
	}

	/**Metodo para procurar Autor pelo Id do usuario
	 * 
	 * @param id
	 * @return
	 */
	public Autor findByUsuario(Long id) {
		return repository.findByUsuarioId(id);
	}
}
