package com.info.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.info.dto.UserDTO;
import com.info.entity.User;
import com.info.repo.UserRepository;

@Service
public class UserService {
  private PasswordEncoder passwordEncoder;
  private UserRepository repo;
  public UserService(PasswordEncoder passwordEncoder,UserRepository repo) {
	  this.passwordEncoder = passwordEncoder;
	  this.repo = repo;
  }
  public User register(UserDTO userDto) {
	  User user = new User();
	  user.setEmail(userDto.getEmail());
	  user.setPassword(passwordEncoder.encode(userDto.getPassword()));
	  user.setRole(userDto.getRole());
	  return repo.save(user);
  }
}
