package com.melilla.REST_vivienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.melilla.REST_vivienda.model.Role;



public interface RoleRepository extends  JpaRepository<Role, Long> ,RevisionRepository<Role, Long, Long> {

}
