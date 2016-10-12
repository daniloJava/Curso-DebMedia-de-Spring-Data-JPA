package br.com.CursoSpringDataJPA.dao;

import br.com.CursoSpringDataJPA.entity.Document;

public class DocumentDao extends GenericDao<Document>{

	public DocumentDao() {
		super(Document.class);
	}

}
