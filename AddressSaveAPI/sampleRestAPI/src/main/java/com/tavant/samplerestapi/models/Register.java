package com.tavant.samplerestapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "register")
@Entity
public class Register {
	@Id
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	
	
	
}