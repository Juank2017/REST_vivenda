package com.melilla.REST_vivienda.DTO;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.melilla.REST_vivienda.model.Documento;
import com.melilla.REST_vivienda.model.Informe;
import com.melilla.REST_vivienda.model.Objeto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpedienteDTO {
	
	private long idExpediente;
	private String tipo;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso= ISO.DATE)
	private LocalDate fechaEntrada;
	private String estado;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso= ISO.DATE)
	private LocalDate fechaResolucion;
	private Informe informe;
	//private String organismo;
	//private LocalDate fechaEnvioInforme;
	//private String estadoInforme;
	//private LocalDate fechaRecepcionInforme;
	//private String observacionesInforme;
	private String ndireccion;
	private String	autoriza;
	private String peticion;
	private String observaciones;
	@Builder.Default
	private List<IntervinienteDTO> intervinientes = new ArrayList<IntervinienteDTO>();
	@Builder.Default
	private List<Objeto> objetos = new ArrayList<Objeto>();
	@Builder.Default
	private List<Documento> documentos = new ArrayList<Documento>();

}
