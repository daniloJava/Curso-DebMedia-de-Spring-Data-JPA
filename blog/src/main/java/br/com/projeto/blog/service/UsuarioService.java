package br.com.projeto.blog.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.blog.entity.Avatar;
import br.com.projeto.blog.entity.Usuario;
import br.com.projeto.blog.repository.UsuarioRepository;

/**Service, metodo de somente os serviçõs que faz a ligação com o 
 * objeto Repository.
 * nesse metoro contem os servições de usuário necessarios.
 * 
 * @author Danilo Silva
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	/**Metodo para dar uma paginação a propriedade de ordenar.
	 * 
	 * @param page - o numero da pagina
	 * @param size - quantidade dos registros por pagina
	 * @param campo - o campo a ser ordenado
	 * @param order - como será ordenado
	 * @return
	 */
	public Page<Usuario> findByPaginationByOrderField(int page, int size, String campo, String order){
		Sort sort = new Sort(new Order(Direction.fromString(order), campo));
		
		return repository.findAll(new PageRequest(page, size, sort));
		
	}
	
	
	/**Recupera a paginação dos usuários
	 * 
	 * @param page - numero de paginas
	 * @param size - quantos registros terá cada pagina
	 * @return - um Objeto de paginação de Usuário.
	 */
	public Page<Usuario> findByPagination(int page, int size){
		Pageable pageable = new PageRequest(page, size);
		
		return repository.findAllByOrderByNomeAsc(pageable);
		
	}
	
	
	/**Atualizar o usuário
	 * 
	 * A ideia de usar um @Transactional(readOnly = false)
	 * é que por padrão, o Spring-Data intende que todos os metodos
	 * sao apenas por consulta, aplicando false, podemos usar o metodo para fazer alteração. 
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void updateNomeAndEmail(Usuario user) {
		
		repository.updateNomeAndEmail(user.getNome(), user.getEmail(), user.getId());
	}
	
	
	@Transactional(readOnly = true)
	public void delete(Long id){
		
		repository.delete(id);
	}
	
	/**Salva um usuário atravez do Objeto recuperado.
	 * 
	 * A ideia de usar um @Transactional(readOnly = false)
	 * é que por padrão, o Spring-Data intende que todos os metodos
	 * sao apenas por consulta, aplicando false, podemos usar o metodo para fazer alteração. 
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void save(Usuario user){
		//recupera a data e coloca no Objeto usuário
		if(user.getDataCadastro() == null)
			user.setDataCadastro(LocalDate.now());
		
		String hash = new BCryptPasswordEncoder().encode(user.getSenha());
		user.setSenha(hash);
		
		repository.save(user);
		
	}
	
	/**Recupera um usuário pelo seu ID
	 * 
	 * @param id
	 * @return
	 */
	
	public Usuario findById(Long id){
		return repository.findOne(id);
	}
	
	/**Recupera um usuário pelo seu e-mail
	 * 
	 * @param email
	 * @return
	 */
	public Usuario findByEmail(String email){
		return repository.findByEmail(email);
	}

	/**Procura usuário por um avatar
	 * 
	 * @param avatar
	 * @return
	 */
	public Usuario findByAvatar(Avatar avatar){
		return repository.findByAvatar(avatar);
	}
	
	
	/**procura todos os usuários
	 * 
	 * @return
	 */
	public List<Usuario> findAll(){
		return repository.findAll();
	}

	/**Autalizar a senha do usuário
	 * 
	 * @param user - Recebe um objeto Usuario para saber qual será atualizado
	 */
	@Transactional(readOnly = false)
	public void updateSenha(Usuario user) {
		//recebe a senha Criptografada
		String hash = new BCryptPasswordEncoder().encode(user.getSenha());
		//insere no objeto
		user.setSenha(hash);
		//somente chama o metodo do UsuarioRepository para tualizar a senha.
		repository.updateSenha(user.getSenha(), user.getId());
	}
	
	
	
}
