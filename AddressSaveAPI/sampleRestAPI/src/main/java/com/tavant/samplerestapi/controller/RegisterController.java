package com.tavant.samplerestapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.tavant.samplerestapi.exception.PasswordsUnmatchedException;
import com.tavant.samplerestapi.exception.UserNotFoundException;
import com.tavant.samplerestapi.exception.WrongUserCredentialException;
import com.tavant.samplerestapi.models.Address;
import com.tavant.samplerestapi.models.Register;
import com.tavant.samplerestapi.payloads.ApiResponse;
import com.tavant.samplerestapi.payloads.SignUpRequest;
import com.tavant.samplerestapi.repository.AddressRepository;
import com.tavant.samplerestapi.repository.RegisterRepository;



@RestController
@RequestMapping("/api") // mapping the register controller to api
@CrossOrigin("*") // to enable all cross origin requests
public class RegisterController {
	@Autowired
	RegisterRepository res ; // calling the repository object
	
	@Autowired
    PasswordEncoder passwordEncoder; // calling the encoder object

 

    @Autowired
    private Environment env;
    @Autowired
	private AddressRepository addressRepository;
    
    @GetMapping("/{user}")
    public ResponseEntity<?> getUserById(@PathVariable("user") String username)   // function to get the user details by using username
    {
   
    	Optional<Register> auth = res.findByName(username);if (auth.isPresent()) { // finding the user by username
    		return ResponseEntity.ok(auth.get());
    } else
    {
    	ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Found"); // error response when user was not found
    }
    return null;
    }
    
    @PostMapping("/signup") // function to register the user using the data values provided by the user.
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (res.existsByName(signUpRequest.getName())) {
        	// condition evaluation to see if any values already exists
            return new ResponseEntity(new ApiResponse(false, "Username already exists!"), HttpStatus.BAD_REQUEST);
        }

 

        if (res.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email address alreadty exists!"), HttpStatus.BAD_REQUEST);
        }

        Register reg= new Register(); // creating a new register object
        reg.setName(signUpRequest.getName()); //getting an setting the fields from the users
        reg.setEmail(signUpRequest.getEmail());
       	reg.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

       	res.save(reg); // saving the values into the database
        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully");// status response
    }
    
    @PostMapping ("/saveaddress") // to add details into the db
	public ResponseEntity<?> addAddress(@RequestBody @Valid Address address) throws UserNotFoundException
	{
		
		Address add =  addressRepository.save(address);
		return  ResponseEntity.status(HttpStatus.OK).body("Address added"); // return response when file is added successfully 
	}

    

	
}


	    
	  
