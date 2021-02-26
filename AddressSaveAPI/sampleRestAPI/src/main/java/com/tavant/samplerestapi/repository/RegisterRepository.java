package com.tavant.samplerestapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.tavant.samplerestapi.models.Register;



@Repository
public interface RegisterRepository extends JpaRepository<Register, String>  {
	 Optional<Register> findByEmail(String email);
//
	   Register findByEmailAndPassword(String mail, String password);
//
	    Optional<Register> findByName(String name);

	    Boolean existsByName(String name);

	    Boolean existsByEmail(String email);

}
