package br.com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
	
}
