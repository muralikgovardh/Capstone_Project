package com.tavant.samplerestapi.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tavant.samplerestapi.models.Register;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RegisterPrincipal implements UserDetails{
	

	private String name;
	@JsonIgnore
	private String email;
	@JsonIgnore
	private String password;
	
	
	private Collection <? extends GrantedAuthority> authorities ;

	public RegisterPrincipal(String name, String email, String password)
			 {
		super();
		this.name = name;
		this.email = email;
		this.password = password;

		
	}
	
	public static RegisterPrincipal create(Register register) {

		
		return new RegisterPrincipal(register.getName(), 
				 register.getEmail(), 
				register.getPassword());

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}
    // mapping register password as login credential 
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
    // mapping register name as login Username 
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean equals(Object o) {
		if (this==o)
			return true;
		if (o==null || getClass() !=o.getClass())
			return false;
		RegisterPrincipal that = (RegisterPrincipal) o;
		return Objects.equals(name, that.name);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	

	

}
















