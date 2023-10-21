package com.melilla.REST_vivienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.melilla.REST_vivienda.DTO.JwtResponseDTO;
import com.melilla.REST_vivienda.DTO.LoginDTO;
import com.melilla.REST_vivienda.exceptions.exceptions.TokenRefreshException;
import com.melilla.REST_vivienda.model.RefreshToken;
import com.melilla.REST_vivienda.service.AuthenticationService;
import com.melilla.REST_vivienda.service.JWTService;
import com.melilla.REST_vivienda.service.RefreshTokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor	
public class AuthenticationController {

	  @Autowired
	  RefreshTokenService refreshTokenService;
	
	private final AuthenticationService authenticationService;
	
	@Autowired
	JWTService jwtService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginDTO loginDTO){
		
		return ResponseEntity.ok(authenticationService.login(loginDTO.getUserName(), loginDTO.getPassword()));
	}
	
	  @PostMapping("/refreshtoken")
	  public ResponseEntity<?> refreshtoken(@Valid @RequestBody String request) {
	    

	    return refreshTokenService.findByToken(request)
	        .map(refreshTokenService::verifyExpiration)
	        .map(RefreshToken::getUser)
	        .map(user -> {
	          String token = jwtService.generateTokenFromUsername(user.getUsername());
	          return ResponseEntity.ok(JwtResponseDTO.builder().estado(HttpStatus.OK).token(token).refreshToken(request).userName(user.getUsername()).build());
	        })
	        .orElseThrow(() -> new TokenRefreshException(request,
	            "Refresh token is not in database!"));
	  }

}
