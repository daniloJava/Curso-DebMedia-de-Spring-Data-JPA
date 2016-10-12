package br.com.projeto.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.blog.entity.Categoria;
import br.com.projeto.blog.repository.CategoriaRepository;
import br.com.projeto.blog.util.MyReplaceString;

/**Classe para serl� de Categoria
 * 
 * @author Danilo Silva
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;

	
	public List<Categoria> findAll(){
		Sort order = new Sort(new Order(Direction.ASC, "descricao"));
		return repository.findAll(order);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id){
		repository.delete(id);
		
	}
	
	public Categoria findByDescricao(String descricao){
		return repository.findByDescricao(descricao);
	}
	
	public Categoria findById(Long id){
		return repository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Categoria categoria){
		String permalink = MyReplaceString.formatarPermalink(categoria.getDescricao());
		categoria.setPermaLink(permalink);
		repository.save(categoria);
		
	}
	
	/**Metodo que retorna uma paginação do banco de dados
	 * 
	 * @param page - a pagina que estamos buscando, se no banco voultar 5 paginas é aqui que vai ficar
	 * @param size - a quantidade de dados que vai vir por pagina
	 * @return
	 */
	public Page<Categoria> findByPaginetion(int page, int size){
		
		Pageable pageable = new PageRequest(page, size);
		
		return repository.findAllByOrderByDescricaoAsc(pageable);
	}
	
}
