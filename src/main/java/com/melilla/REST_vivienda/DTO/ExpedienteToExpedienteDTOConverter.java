package com.melilla.REST_vivienda.DTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.melilla.REST_vivienda.model.Expediente;
import com.melilla.REST_vivienda.model.Expediente_has_Interviniente;
import com.melilla.REST_vivienda.model.enums.EstadoCivil;
import com.melilla.REST_vivienda.repository.IntervinienteRepository;

import lombok.Data;

@Data
public class ExpedienteToExpedienteDTOConverter {

	@Autowired
	private IntervinienteRepository intervinienteRepository;
	
	public  ExpedienteDTO convert(Expediente expediente) {
		
		Set<Expediente_has_Interviniente> intervinientes = expediente.getInterviniente();
		
		List<IntervinienteDTO> intervinientesDTO = new ArrayList<IntervinienteDTO>();
		
		intervinientes.forEach(interviniente ->{
			
			intervinientesDTO.add(IntervinienteDTO
									.builder()
									.calidad(interviniente.getCalidad())
									.observaciones(interviniente.getObservaciones())
									.idInterviniente(interviniente.getInterviniente().getId_Interviniente())
									.nombre(interviniente.getInterviniente().getNombre())
									.tipo_documento(interviniente.getInterviniente().getTipo_documento())
									.num_doc(interviniente.getInterviniente().getNumdoc())
									.nacionalidad(interviniente.getInterviniente().getNacionalidad())
									.estadoCivil(EstadoCivil.forName(interviniente.getInterviniente().getEstadoCivil()))
									.build());
		});
		
		return ExpedienteDTO.builder()
				.idExpediente(expediente.getId())
				.fechaEntrada(expediente.getFechaEntrada())
				.estado(expediente.getEstado())
				.fechaResolucion(expediente.getFechaResolucion())
				.ndireccion(expediente.getDestinatario())
				.peticion(expediente.getPeticion())
				.observaciones(expediente.getObservaciones())
				.informe(expediente.getInforme())
				.intervinientes(intervinientesDTO)
				.objetos(expediente.getObjetos().stream().toList())
				.documentos(expediente.getDocumentos().stream().toList())
				.build();
	}
}
