package br.com.projeto.blog.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.blog.entity.Postagem;
import br.com.projeto.blog.repository.PostagemRepository;
import br.com.projeto.blog.util.MyReplaceString;

/**Classe para o Service de Postagens
 * 
 * @author Danilo Silva
 *
 */
@Service
//Operação controlada no Service tanto commit quanto Rowback
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PostagemService {

	@Autowired
	private PostagemRepository repository;

	/**Metodo para criar a pagina
	 * <p>cria uma paginação recuperando todos os registros, 
	 * ordenando pela data de postagem </p>
	 * 
	 * @param page - Numero da pagina
	 * @param size - quantidade de registro por pagina
	 * @return Pageable - paginação dos registros. 
	 */
	public Page<Postagem> findByPaginetion(int page, int size){
		Pageable pageable = new PageRequest(page, size);
		return repository.findAllByOrderByDataPostagemDesc(pageable);
		
	}
	
	
	/**Lista todas as Postagens
	 * 
	 * @return
	 */
	public List<Postagem> findAll(){
		return repository.findAll();
	}
	
	
	/**Retorna uma postagem pelo Linke
	 * 
	 * @param link String - Linke para recuperar a postagem
	 * @return
	 */
	public Postagem findByPermaLink(String link){
		return repository.findByPermaLink(link);
	}
	
	/**Procura postagem pelo ID
	 * 
	 * @param id
	 * @return
	 */
	public Postagem findById(Long id){
		return repository.findOne(id);
		
	}
	
	/**Metodo para ou atualizar a postagem ou para salvar uma postagem.
	 * 
	 * * A ideia de usar um @Transactional(readOnly = false)
	 * é que por padrão, o Spring-Data intende que todos os metodos
	 * sao apenas por consulta, aplicando false, podemos usar o metodo para fazer alteração.
	 * 
	 * 
	 * @param postagem
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdadte(Postagem postagem){
		
		if(postagem.getId() == null){
			save(postagem);
		}else {
			update(postagem);
		}
	}
	
	/** Metodo privatdo ser usado no metodo saveOrupdate 
	 * 
	 * @param postagem
	 */
	private void update(Postagem postagem) {
		Postagem persistente = repository.findOne(postagem.getId());
		
		if(!persistente.getTitulo().equals(postagem.getTitulo())){
			persistente.setTitulo(postagem.getTitulo());
		}
		
		if(!persistente.getTexto().equals(postagem.getTexto())){
			persistente.setTexto(postagem.getTexto());
			
		}
		
		if(persistente.getCategorias() != postagem.getCategorias()){
			persistente.setCategorias(postagem.getCategorias());
			
		}
		
		repository.save(persistente);
	}
	/**Metodo Privado para ser usado no saveOrUpadate, na intenção de salvar oo arquivo
	 * 
	 * 
	 * @param postagem
	 */
	private void save(Postagem postagem) {
		
		String permaLink = MyReplaceString.formatarPermalink(postagem.getTitulo());
		System.out.println(permaLink);
		postagem.setDataPostagem(LocalDateTime.now());
		
		postagem.setPermaLink(permaLink);
		repository.save(postagem);
		
	}
	
	/**Deleta o usuário atravez do id
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);
	}

	/**Busca as categorias pelo perma link
	 * 
	 * @param permaLink
	 * @return
	 */
	public List<Postagem> findByCategoria(String permaLink) {
		
		return repository.findByCategoriasPermaLink(permaLink);
	}
	
	/**lista todos os posts pelo Autor.
	 * 
	 * @param nome
	 * @return
	 */
	public List<Postagem> findByAutor(String nome) {
		return repository.findByAutor(nome);
	}


	/**Metodo para adicionar uma paginaçao atravez da categoria.
	 * 
	 * @param page - Numero da pagina
	 * @param size - quantidade de registros na pagina
	 * @param permaLink - Link da categoria
	 * @return
	 */
	public Page<Postagem> findByPaginetionByCategoria(int page, int size, String permaLink) {
		
		Pageable pageable = new PageRequest(page, size);
		
		return repository.findAllByCategoriasPermaLinkOrderByDataPostagemDesc(pageable, permaLink);
	}

	/**Metodo para cirar uma paginação de Autor atravez do ID
	 * 
	 * @param page - numero da pagina 
	 * @param size - quantidade dos registros por pagina
	 * @param id - Id do Autor
	 * @return Pageable - da consulta
	 */
	public Page<Postagem> findByPaginetionByAutor(int page, int size, Long id) {
		Pageable pageable = new PageRequest(page, size);
		
		return repository.findAllByAutorIdOrderByDataPostagemDesc(pageable, id);
	}
	
	
	
}
