package com.melilla.REST_vivienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melilla.REST_vivienda.model.User;

public interface UserRepository extends  JpaRepository<User, Long>{
	Optional<User> findByUsername(String userName);
}
