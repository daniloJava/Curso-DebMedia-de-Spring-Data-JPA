package br.com.CursoSpringDataJPA.dao;

import br.com.CursoSpringDataJPA.entity.Phone;

public class PhoneDao extends GenericDao<Phone>{

	public PhoneDao() {
		super(Phone.class);
	}
}
