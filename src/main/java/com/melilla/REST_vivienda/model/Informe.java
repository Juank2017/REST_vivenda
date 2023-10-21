package com.melilla.REST_vivienda.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class Informe  {

		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long id_Informe;
		
		@NonNull
	    private String organismo;
		@NonNull
		@Temporal(TemporalType.DATE)
		@DateTimeFormat(iso= ISO.DATE)
	    private LocalDate fechaSolicitud;
		
		@Temporal(TemporalType.DATE)
		@DateTimeFormat(iso= ISO.DATE)
	    private LocalDate fechaRecepcion;
	    private String estado;
	    private String observaciones;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name ="expediente.id_Expediente")	  
	    @JsonIgnore
	    private Expediente expediente;
	    
	    
}
