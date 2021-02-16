package org.jesuitasrioja.proyecto.modelo.alumno;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AlumnoDTOConverter {
	
	private final ModelMapper modelMapper = new ModelMapper();
	
	public AlumnoDTO convertAlumnoToAlumnoDTO(Alumno alumno) {
		
		AlumnoDTO dto = modelMapper.map(alumno, AlumnoDTO.class);
		
		return dto;
	}

}
