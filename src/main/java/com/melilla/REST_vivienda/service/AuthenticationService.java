package com.melilla.REST_vivienda.service;

import com.melilla.REST_vivienda.DTO.JwtResponseDTO;

public interface AuthenticationService {
    
	JwtResponseDTO login (String userName,String password);
}
