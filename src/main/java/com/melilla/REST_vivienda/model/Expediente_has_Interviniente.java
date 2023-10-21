package com.melilla.REST_vivienda.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Entity

@Table(name = "expediente_has_interviniente")
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Expediente_has_Interviniente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   	private long id;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INTERVINIENTE_ID") 
	private Interviniente interviniente;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="expediente.id_Expediente")
	private Expediente expediente;
	private String calidad;
	private String observaciones;

	public String getCalidad() {
		return calidad;
	}

	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Interviniente getInterviniente() {
		return interviniente;
	}

	public void setInterviniente(Interviniente interviniente) {
		this.interviniente = interviniente;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}



}
