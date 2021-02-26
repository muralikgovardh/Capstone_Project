package com.tavant.samplerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.samplerestapi.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
