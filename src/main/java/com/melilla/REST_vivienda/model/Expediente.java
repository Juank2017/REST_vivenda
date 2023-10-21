package com.melilla.REST_vivienda.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.melilla.REST_vivienda.envers.Auditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import lombok.Setter;



@Entity
@Audited
@Getter
@Setter
@NoArgsConstructor
@Table(name = "expediente")
public class Expediente extends Auditable {

	public Expediente(Long num_expediente, @NonNull int year, @NonNull LocalDate fechaEntrada,
			@NonNull String estado, LocalDate fechaResolucion, String tipo, String destinatario,
			String observaciones, String peticion, Set<Expediente_has_Interviniente> interviniente,
			Set<Objeto> objetos) {
		super();
		this.numExp = num_expediente;
		this.year=year;
		this.fechaEntrada = fechaEntrada;
		this.estado = estado;
		this.fechaResolucion = fechaResolucion;
		this.tipo = tipo;
		this.destinatario = destinatario;
		this.observaciones = observaciones;
		this.peticion = peticion;
		this.interviniente = interviniente;
		this.objetos = objetos;
	}


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_expediente")
	private long id;

	@Column(name="num_exp")	
	private Long numExp;

	
	@Column(name="ano")
	private int year;

	@NonNull
	private LocalDate fechaEntrada;

	@NonNull
	private String estado;

	private LocalDate fechaResolucion;

	private String tipo;

	private String destinatario;

	private String observaciones;
	
	private String autoriza;

	private String peticion;
	
	@OneToOne(mappedBy = "expediente", cascade = CascadeType.ALL)
	private Informe informe;
	
	@NotAudited
	@OneToMany(mappedBy = "expediente", cascade = CascadeType.ALL)
	private Set<Expediente_has_Interviniente> interviniente = new HashSet<Expediente_has_Interviniente>();

	@NotAudited
	@OneToMany(mappedBy = "expediente", cascade = CascadeType.ALL)
	private Set<Objeto> objetos= new HashSet<Objeto>();
	
	@NotAudited
	@OneToMany(mappedBy = "expediente", cascade = CascadeType.ALL)
	private Set<Documento> documentos= new HashSet<Documento>();

}
