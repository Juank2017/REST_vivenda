package com.melilla.REST_vivienda.DTO;




import com.melilla.REST_vivienda.model.enums.EstadoCivil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntervinienteDTO {


    private long idInterviniente;
	
	
    private String nombre;
	
	
    private String tipo_documento;
	

    private String num_doc;
	
	
    private String nacionalidad;
    
    private EstadoCivil estadoCivil;
    
    private String calidad;
    
    private String observaciones;
    
}
