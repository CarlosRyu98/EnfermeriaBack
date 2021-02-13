package org.jesuitasrioja.proyecto.persistencia.services;

import java.util.Optional;

import org.jesuitasrioja.proyecto.modelo.alumno.Alumno;
import org.jesuitasrioja.proyecto.persistencia.repositories.AlumnoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService extends BaseService<Alumno, String, AlumnoRepository> {

	public void editById(String id) {

		Optional<Alumno> aOptional = repositorio.findById(id);
		if (aOptional.isPresent()) {
			Alumno a = aOptional.get();
			this.repositorio.save(a);
		}

	}
	
	public void editProfesor(String idAlumno, String idProfesor) {
		
		Optional<Alumno> aOptional = repositorio.findById(idAlumno);
		
	}

}
