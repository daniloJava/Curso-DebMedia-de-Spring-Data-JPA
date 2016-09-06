package br.com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
