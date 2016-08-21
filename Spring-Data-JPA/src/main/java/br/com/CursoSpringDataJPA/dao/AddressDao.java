package br.com.CursoSpringDataJPA.dao;

import br.com.CursoSpringDataJPA.entity.Address;

public class AddressDao extends GenericDao<Address>{

	public AddressDao() {
		super(Address.class);
	}
}
