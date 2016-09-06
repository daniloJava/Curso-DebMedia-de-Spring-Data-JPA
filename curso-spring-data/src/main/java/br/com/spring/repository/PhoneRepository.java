package br.com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long>{

}
