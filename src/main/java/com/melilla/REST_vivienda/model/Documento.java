package com.melilla.REST_vivienda.model;

import java.time.LocalDate;


import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.melilla.REST_vivienda.envers.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Audited
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "documento")
public class Documento extends Auditable {
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_Documento;
	
	private String tipo;
	
	private String nombre;
	
	private String url;
	
	@ManyToOne
	@JoinColumn(name ="expediente.idExpediente")
	@JsonIgnore
	private Expediente expediente;
	
	@NonNull
	private LocalDate fechaEntrada;

	public Documento(long id, String tipo, String nombre, String url, @NonNull LocalDate fechaEntrada) {
		super();
		this.id_Documento = id;
		this.tipo = tipo;
		this.nombre = nombre;
		this.url = url;
		this.fechaEntrada = fechaEntrada;
	}
	
	


}
