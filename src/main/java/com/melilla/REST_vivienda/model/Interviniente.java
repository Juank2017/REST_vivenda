package com.melilla.REST_vivienda.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name= "interviniente")
public class Interviniente  {
	
	@Column(name="id_Interviniente")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Interviniente;
	
	@NonNull
    private String nombre;
	
	@NonNull
    private String tipo_documento;
	
	@NonNull
    private String numdoc;
	
	
    private String nacionalidad;
    
    private String estadoCivil;
    

    
   @OneToMany(mappedBy = "interviniente", cascade = CascadeType.ALL)
   @JsonIgnore
   private Set<Expediente_has_Interviniente>expediente;
    
}
