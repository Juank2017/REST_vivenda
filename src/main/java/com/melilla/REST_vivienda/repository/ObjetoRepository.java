package com.melilla.REST_vivienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.melilla.REST_vivienda.model.Objeto;

public interface ObjetoRepository extends JpaRepository<Objeto, Long>, RevisionRepository<Objeto, Long, Long> {

}
