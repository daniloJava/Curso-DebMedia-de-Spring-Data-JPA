package br.com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

}
