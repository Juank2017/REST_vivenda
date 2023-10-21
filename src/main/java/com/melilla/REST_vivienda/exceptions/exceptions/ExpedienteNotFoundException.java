package com.melilla.REST_vivienda.exceptions.exceptions;

public class ExpedienteNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExpedienteNotFoundException(Long idExpediente) {
		super("No se ha podido encontrar el expediente " + idExpediente);
	}

}
