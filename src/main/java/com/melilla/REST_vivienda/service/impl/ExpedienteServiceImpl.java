package com.melilla.REST_vivienda.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.melilla.REST_vivienda.DTO.ExpedienteCortoDTO;
import com.melilla.REST_vivienda.DTO.ExpedienteDTO;
import com.melilla.REST_vivienda.DTO.ExpedienteToExpedienteDTOConverter;
import com.melilla.REST_vivienda.model.Expediente;
import com.melilla.REST_vivienda.repository.ExpedienteRepository;
import com.melilla.REST_vivienda.repository.Expediente_has_intervinienteRepository;
import com.melilla.REST_vivienda.repository.IntervinienteRepository;
import com.melilla.REST_vivienda.repository.ObjetoRepository;
import com.melilla.REST_vivienda.service.ExpedienteService;
import com.melilla.REST_vivienda.DTO.IntervinienteDTO;
import com.melilla.REST_vivienda.model.Expediente_has_Interviniente;
import com.melilla.REST_vivienda.model.Informe;
import com.melilla.REST_vivienda.model.Interviniente;
import com.melilla.REST_vivienda.model.Objeto;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;

@Service
@Transactional
@Log
public class ExpedienteServiceImpl implements ExpedienteService {
	@Autowired
	private ExpedienteRepository expedienteRepository;
	@Autowired
	private IntervinienteRepository intervinienteRepository;
	@Autowired
	private Expediente_has_intervinienteRepository relacionRepository;
	@Autowired
	private ObjetoRepository objetoRepository;
	
	
	private ExpedienteToExpedienteDTOConverter converter;

	@Override
	public Page<Expediente> findAll(Pageable pageable) {
		
		return expedienteRepository.findAll(pageable) ;
	}

	@Override
	public Expediente crearExpediente(ExpedienteDTO expedienteDTO) {
		Expediente nuevoExpediente = new Expediente();

		
		// obtiene el siguiente número de expediente de la secuencia en la BBDD
		nuevoExpediente.setNumExp(expedienteRepository.proximoNumExp());
		// TODO: obtener el año de trabajo de la BBDD
		nuevoExpediente.setYear(2023);

		nuevoExpediente.setFechaEntrada(expedienteDTO.getFechaEntrada());
		nuevoExpediente.setTipo(expedienteDTO.getTipo());

		if (expedienteDTO.getFechaResolucion().equals(LocalDate.EPOCH)) {
			nuevoExpediente.setFechaResolucion(null);
		} else {
			nuevoExpediente.setFechaResolucion(expedienteDTO.getFechaResolucion());
		}
		nuevoExpediente.setDestinatario(expedienteDTO.getNdireccion());
		nuevoExpediente.setObservaciones(expedienteDTO.getObservaciones());
		nuevoExpediente.setPeticion(expedienteDTO.getPeticion());
		nuevoExpediente.setEstado(expedienteDTO.getEstado());

		Expediente expedienteGuardado = expedienteRepository.save(nuevoExpediente);
		if (!expedienteDTO.getInforme().getFechaSolicitud().equals(LocalDate.EPOCH)) {
			Informe informe = Informe.builder().organismo(expedienteDTO.getInforme().getOrganismo())
					.estado(expedienteDTO.getInforme().getEstado())
					.fechaSolicitud(expedienteDTO.getInforme().getFechaSolicitud())
					.fechaRecepcion(expedienteDTO.getInforme().getFechaRecepcion())
					.observaciones(expedienteDTO.getInforme().getObservaciones()).expediente(expedienteGuardado)
					.build();
			expedienteGuardado.setInforme(informe);

		}
		log.info(expedienteGuardado.toString());
		// log.info(expedienteGuardado.getInforme().getExpediente().getNum_exp());

		List<IntervinienteDTO> intervinientes = expedienteDTO.getIntervinientes();

		if (!intervinientes.isEmpty()) {

			for (IntervinienteDTO intervinienteDTO : intervinientes) {

				Interviniente interviniente = intervinienteRepository.save(Interviniente.builder()
						.id_Interviniente(intervinienteDTO.getIdInterviniente()).nombre(intervinienteDTO.getNombre())
						.tipo_documento(intervinienteDTO.getTipo_documento()).numdoc(intervinienteDTO.getNum_doc())
						.estadoCivil(intervinienteDTO.getEstadoCivil().getName())
						.nacionalidad(intervinienteDTO.getNacionalidad()).build());
				Expediente_has_Interviniente relacion = new Expediente_has_Interviniente();

				relacion.setCalidad(intervinienteDTO.getCalidad());
				relacion.setObservaciones(intervinienteDTO.getObservaciones());
				relacion.setInterviniente(interviniente);
				relacion.setExpediente(expedienteGuardado);
				relacionRepository.save(relacion);
			}
		}
		
		List<Objeto> objetos = expedienteDTO.getObjetos();
		
		if(!objetos.isEmpty()) {
			objetos.forEach(objeto->{
				objeto.setExpediente(expedienteGuardado);
				
				expedienteGuardado.getObjetos().add(objetoRepository.save(objeto));
			});
		}

		return expedienteRepository.save(expedienteGuardado);
	}

	@Override
	public List<ExpedienteCortoDTO> findTop100() {
		converter = new ExpedienteToExpedienteDTOConverter();
		return  expedienteRepository.findTop100ByOrderByFechaEntradaDesc();
	}

	@Override
	public Optional<Expediente> obtenerExpedientePorId(Long idExpediente) {
		
		

		return expedienteRepository.findById(Long.valueOf(idExpediente));
	}

}
