package br.com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.entity.Address;

/**Trabalhando com palavras Chaves do Spring 
 * 
 * StartingWith
 * EndingWith
 * Contaning
 * OderBy (Atributo para ordenação "Type")Desc
 * 
 * @author Aluno
 *
 */
public interface AddressRepository extends JpaRepository<Address, Long>{

	/**como se fosse o comessando com, como se fosse usando o % do SQL
	 * 
	 * @return
	 */
	List<Address> findByCityStartingWith(String street);

	/**Termina com.
	 * 
	 * @return
	 */
	List<Address> findByStreetEndingWith(String city);

	
	/**Se tiver uma palavra ou sequencia de caracteres do parametro, 
	 * ira buscar.
	 * 
	 * @param street String - Rua
	 * @return uma lista de Endereços que tanha as letras do parametro.
	 */
	List<Address> findByStreetContaining(String street);
	
	/**Mesclando duas opçoes, começo de uma cidade OU o que tem  final de uma rua informada
	 * 
	 * @param city - cidade para busca
	 * @param street - rua para busca
	 * @return - lista da consulta
	 */
	List<Address> findByCityStartingWithOrStreetEndingWith(String city, String street);
	
	/**serve para ordenar os resultados passando se é Crescente ou Decrescente
	 * Trabalha passano o atributo criado na Classe. Type 
	 * 
	 * @param city cidade buscada
	 * @return - retorna uma lista pela City informada e ordenado Decrescente por tipo
	 */
	List<Address> findByCityOrderByTipoDesc(String city);
	
	
	
}
