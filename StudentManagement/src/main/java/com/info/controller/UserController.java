package com.info.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.dto.AuthResponse;
import com.info.dto.UserDTO;
import com.info.entity.User;
import com.info.service.UserService;
import com.info.util.JwtUtill;

@RestController
@RequestMapping("/user")
public class UserController {
   private UserService service;
   private JwtUtill jwtUtil;
	private UserDetailsService detailService;
	private AuthenticationManager authenticationManager;
   public UserController(UserService service , AuthenticationManager authenticationManager , JwtUtill jwtUtil , UserDetailsService detailService ) {
	   this.service = service;
	   this.authenticationManager = authenticationManager;
	   this.jwtUtil = jwtUtil;
	   this.detailService =  detailService;
   }
   @PostMapping("/register")
   public  ResponseEntity<User> registerUser( @RequestBody UserDTO userDto) {
	   return ResponseEntity.ok().body(service.register(userDto));
   }
   @PostMapping("/login")
   public ResponseEntity<AuthResponse> login (@RequestBody UserDTO userdto){
   	 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userdto.getEmail(), userdto.getPassword()));
   	   UserDetails userDetails =  detailService.loadUserByUsername(userdto.getEmail());
   	String token = jwtUtil.generateToken(userDetails);
   	AuthResponse authResponse = new AuthResponse(token,userdto.getEmail());
   	return ResponseEntity.ok(authResponse);
   }
}
