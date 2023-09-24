package com.melilla.REST_vivienda.DTO;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponseDTO {

	private String userName;
	private List<GrantedAuthority> roles;
	private String token;
}
