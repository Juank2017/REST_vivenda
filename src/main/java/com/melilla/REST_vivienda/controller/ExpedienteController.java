package com.melilla.REST_vivienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.melilla.REST_vivienda.DTO.ExpedienteDTO;
import com.melilla.REST_vivienda.exceptions.exceptions.ExpedienteNotFoundException;
import com.melilla.REST_vivienda.model.ApiResponse;
import com.melilla.REST_vivienda.model.Expediente;
import com.melilla.REST_vivienda.service.ExpedienteService;


import jakarta.transaction.Transactional;

@RestController
@Transactional
public class ExpedienteController {
	
	@Autowired
	private ExpedienteService expedienteService;
	
	@GetMapping("/expediente")
	ResponseEntity<ApiResponse>listAll(){
		ApiResponse response = new ApiResponse();
		response.setEstado(HttpStatus.OK);
		response.getPayload().addAll(expedienteService.findTop100());
		response.setMensaje("lista de los 100 últimos expedientes.");
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/expediente/crear")
	ResponseEntity<ApiResponse> crearExpediente(@RequestPart ExpedienteDTO expediente){
		ApiResponse response = new ApiResponse();
		response.setEstado(HttpStatus.CREATED);
		response.getPayload().add(expedienteService.crearExpediente(expediente));
		response.setMensaje("Creado expediente con id: "+expediente.getIdExpediente());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/expediente/{idExpediente}")
	ResponseEntity<ApiResponse> obtenerExpediente(@PathVariable Long idExpediente){
		ApiResponse response = new ApiResponse();
		Expediente expedienteBuscado = expedienteService.obtenerExpedientePorId(idExpediente).orElseThrow(()->new ExpedienteNotFoundException(idExpediente));
		response.setEstado(HttpStatus.OK);
		response.getPayload().add(expedienteBuscado);
		response.setMensaje("Obetnido el expediente de la base de datos");
		return ResponseEntity.ok(response);
	}

}
