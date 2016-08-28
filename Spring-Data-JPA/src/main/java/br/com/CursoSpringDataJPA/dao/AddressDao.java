package br.com.CursoSpringDataJPA.dao;

import java.util.List;

import br.com.CursoSpringDataJPA.entity.Address;

public class AddressDao extends GenericDao<Address>{

	public AddressDao() {
		super(Address.class);
	}
	
	/**Prcura uma cidade pelo nome dela
	 * 
	 * @param city - recebe o nome da lista
	 * @return Todos os registro com o nome passado por maremetro.
	 */
	public List<Address> findByCity(String city){
		String sql = "from Address a where a.city like ?";
		return find(sql, city);
		
	}
}
