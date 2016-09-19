package br.com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.spring.entity.Phone;
import br.com.spring.entity.Phone.TypePhones;

@Transactional(readOnly = true)// apenas metodos de leitura
public interface PhoneRepository extends JpaRepository<Phone, Long>{

	/**Fazendo um metodo que possamos fazer uma atualização
	 * com o Spring data
	 * 
	 *Transalções de Update e Delete Utilizando o @Modifying para que informe ao @query a alteração dos dados. 
	 *lembrando que o @Transactional(readOnly = false) 
	 *precisa ser aplicado para que o Spring entenda que não seja somente um plasse de Consulta,
	 * mas sim o metodo passa a ser como metodo de escrita.
	 * @param number
	 * @param id
	 * @return
	 */
	@Modifying
	// dizendo que ele não é uma transação de leitura mas sim de escrita
	@Transactional(readOnly = false)
	@Query("UPDATE Phone p set p.number = ?1 where p.id = ?2")
	int setPhoneNumber(String number, Long id);
	
	@Modifying
	@Transactional(readOnly = false)
	@Query("UPDATE Phone p set p.type = ?1 where p.id = ?2")
	int setPhoneNumber(TypePhones tipo, Long id);
	
	/**Metodo para deletar
	 * 
	 * @param tipo
	 * @param id
	 * @return
	 */
	@Modifying
	@Transactional(readOnly = false)
	@Query("Delete from Phone p where p.number like ?1")
	int deleteByPhoneNumber(String number);
}
