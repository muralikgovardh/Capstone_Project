package com.tavant.samplerestapi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name="Address")
@Table(name="address")

public class Address {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	private String houseNumber;
	private String houseName;
	private String poi;
	private String street;
	private String subSubLocality;
	private String subLocality;
	private String locality;
	private String village;
	private String subDistrict;
	private String district;
	private String city;
	private String state;
	private String pincode;
	private String formattedAddress;
	private String eLoc;
	private Double latitude;
	private Double longitude;
	private String geocodeLevel;
	private float confidenceScore;
	

}
