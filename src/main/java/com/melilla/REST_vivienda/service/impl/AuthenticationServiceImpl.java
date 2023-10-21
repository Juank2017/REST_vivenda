package com.melilla.REST_vivienda.service.impl;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.melilla.REST_vivienda.DTO.JwtResponseDTO;
import com.melilla.REST_vivienda.model.RefreshToken;
import com.melilla.REST_vivienda.repository.UserRepository;
import com.melilla.REST_vivienda.service.AuthenticationService;
import com.melilla.REST_vivienda.service.JWTService;
import com.melilla.REST_vivienda.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;


@Service
@RequiredArgsConstructor
@Log
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticacionManager;
	
	  @Autowired
	  RefreshTokenService refreshTokenService;

	@Autowired
	private JWTService jwtService;
	
	public JwtResponseDTO login(String userName, String password) {
		
		
		authenticacionManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		
		var user = userRepository.findByUsername(userName).orElseThrow(()->new IllegalArgumentException("Usuario o password no válidos"));
		log.info(user.getUsername());
		log.info(user.getAuthorities().toString());
		var jwt = jwtService.generateToken(user);
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
		
		return JwtResponseDTO.builder()
				.estado(HttpStatus.OK)
				.userName(user.getUsername())
				.roles(user.getAuthorities().stream().collect(Collectors.toList()))
				.token(jwt)
				.refreshToken(refreshToken.getToken())
				.build();
	}

}
