package com.app.services;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class CustomUserDetailsService implements UserDetailsService {

	
	public CustomUserDetailsService(){
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 User basicUser1 = new org.springframework.security.core.userdetails.User(
	                "patartimotius",
	                "evievi123",
	                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
		return basicUser1;
	}

}
