package com.melilla.REST_vivienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.melilla.REST_vivienda.model.User;


public interface UserRepository extends  JpaRepository<User, Long> ,RevisionRepository<User, Long, Long>{
	Optional<User> findByUsername(String userName);
}
