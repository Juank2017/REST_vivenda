package com.melilla.REST_vivienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.melilla.REST_vivienda.DTO.ExpedienteCortoDTO;
import com.melilla.REST_vivienda.DTO.ExpedienteDTO;
import com.melilla.REST_vivienda.model.Expediente;

public interface ExpedienteService {

	Page<Expediente> findAll(Pageable pageable);
	List<ExpedienteCortoDTO> findTop100();
	Expediente crearExpediente(ExpedienteDTO expediente);
	Optional<Expediente> obtenerExpedientePorId(Long idExpediente);
}
