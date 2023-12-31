package com.melilla.REST_vivienda.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiResponse {

	private HttpStatus estado;
	
	private List<Object> payload = new ArrayList<Object>();
	
	private String mensaje;
}
