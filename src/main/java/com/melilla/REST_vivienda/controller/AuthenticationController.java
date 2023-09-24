package com.melilla.REST_vivienda.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.melilla.REST_vivienda.DTO.JwtResponseDTO;
import com.melilla.REST_vivienda.DTO.LoginDTO;

import com.melilla.REST_vivienda.service.AuthenticationService;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor	
@RequestMapping("/vivienda")
public class AuthenticationController {

	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginDTO loginDTO){
		
		return ResponseEntity.ok(authenticationService.login(loginDTO.getUserName(), loginDTO.getPassword()));
	}
	

}
