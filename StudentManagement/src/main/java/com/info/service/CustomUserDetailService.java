package com.info.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.info.entity.User;
import com.info.repo.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
    private UserRepository userRepo;
    public CustomUserDetailService(UserRepository repo) {
    	this.userRepo = repo;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User dbUser =  userRepo.findByEmail(username)
				.orElseThrow();
		
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + dbUser.getRole().toUpperCase());
		
		return new org.springframework.security.core.userdetails
				.User(dbUser.getEmail(), dbUser.getPassword(), List.of(authority));
	}
    
}
