package com.melilla.REST_vivienda.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.domain.AuditorAware;

import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.transaction.annotation.EnableTransactionManagement;


import com.melilla.REST_vivienda.envers.AuditorAwareImpl;





@Configuration
@ComponentScan(basePackages = {"com.melilla.REST_vivienda","com.melilla.REST_vivienda.repository"})
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableJpaRepositories(basePackages = {"com.melilla.REST_vivienda"},repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@EnableTransactionManagement
public class WebConfig {


	@Bean
	AuditorAware<String> auditorProvider(){
		return new AuditorAwareImpl();
	}
	
	
}
