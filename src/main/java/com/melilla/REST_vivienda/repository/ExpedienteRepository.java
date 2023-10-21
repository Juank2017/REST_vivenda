package com.melilla.REST_vivienda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import com.melilla.REST_vivienda.DTO.ExpedienteCortoDTO;
import com.melilla.REST_vivienda.model.Expediente;


public interface ExpedienteRepository extends  JpaRepository<Expediente, Long> ,RevisionRepository<Expediente, Long, Long>{

	@Query(value="SELECT NEXT VALUE FOR exp_seq", nativeQuery = true)
	Long proximoNumExp();
	
	Page<Expediente> findAll(Pageable pageable);
	
	List<ExpedienteCortoDTO> findTop100ByOrderByFechaEntradaDesc();
	
}
