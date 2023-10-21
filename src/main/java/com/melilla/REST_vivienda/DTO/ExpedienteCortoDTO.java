package com.melilla.REST_vivienda.DTO;

import java.time.LocalDate;

public interface ExpedienteCortoDTO {

	Long getId();
	Long getnumExp();
	int getYear();
	String getTipo();
	String getDestinatario();
	LocalDate getFechaEntrada();
	LocalDate getFechaResolucion();
	String getEstado();
}
