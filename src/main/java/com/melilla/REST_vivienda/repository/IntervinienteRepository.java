package com.melilla.REST_vivienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.melilla.REST_vivienda.model.Interviniente;

public interface IntervinienteRepository extends JpaRepository<Interviniente, Long>, RevisionRepository<Interviniente, Long, Long> {

}
