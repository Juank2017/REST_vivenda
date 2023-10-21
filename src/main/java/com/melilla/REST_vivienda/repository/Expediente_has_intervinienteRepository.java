package com.melilla.REST_vivienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.melilla.REST_vivienda.model.Expediente_has_Interviniente;

public interface Expediente_has_intervinienteRepository extends JpaRepository<Expediente_has_Interviniente, Long>, RevisionRepository<Expediente_has_Interviniente, Long, Long> {

}
