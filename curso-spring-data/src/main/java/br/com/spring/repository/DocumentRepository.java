package br.com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import br.com.spring.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

	/**#{#entityName} Usando uma El para que o Springa Data 
	 * procure o nome da entidade dentro da classe Document.
	 * 
	 * @param start
	 * @return
	 */
	@Query("select d from #{#entityName} d where d.cpfDocument like :start%")
	List<Document> findByCpfStartWith(@Param("start") String start);
	
	
	/**Chamaando diretamente um procedure no banco de dados.
	 * 
	 * @param id Long - ID
	 * @return = retorna o resultado a Procedure.
	 */
	@Procedure(procedureName = "procReplaceCPF")
	String replaceCPF(Long id);
	
	/**Chamando a procedure com o Nomede na Entidade
	 * 
	 * @param id
	 * @return
	 */
	@Procedure(name = "docs.procedureReplaceCPF")// para que não busque pelo nome do metodo
	String procReplaceCPF(@Param("ID_IN")Long id);
	
	
	
}
