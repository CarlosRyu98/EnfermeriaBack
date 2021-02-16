package org.jesuitasrioja.proyecto.controllers;

import java.util.Optional;
import java.util.function.Function;

import org.jesuitasrioja.proyecto.modelo.alumno.Alumno;
import org.jesuitasrioja.proyecto.modelo.alumno.AlumnoDTO;
import org.jesuitasrioja.proyecto.modelo.alumno.AlumnoDTOConverter;
import org.jesuitasrioja.proyecto.modelo.responsable.Responsable;
import org.jesuitasrioja.proyecto.persistencia.services.AlumnoService;
import org.jesuitasrioja.proyecto.persistencia.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlumnoController {

	@Autowired
	private AlumnoService as;
	
	@Autowired
	private ProfesorService ps;
	
	@Autowired
	private AlumnoDTOConverter alumnoDTOConverter;

	// Obtener información de un alumno a través de su identificador
	@GetMapping("alumno/{idAlumno}")
	public Optional<Alumno> getAlumno(@PathVariable String idAlumno) {
		return as.findById(idAlumno);
	}

	// Obtener información de todos los alumnos de forma paginada por patrón DTO
	@GetMapping("alumnos")
	public ResponseEntity<?> getAlumnos(@PageableDefault(size = 10, page = 0) Pageable pageable) {
		
		Page<Alumno> pagina = as.findAll(pageable);

		Page<AlumnoDTO> paginaDTO = pagina.map(new Function<Alumno, AlumnoDTO>() {
			@Override
			public AlumnoDTO apply(Alumno t) {
				return alumnoDTOConverter.convertAlumnoToAlumnoDTO(t);
			}
			
		});
		return ResponseEntity.status(HttpStatus.OK).body(paginaDTO);
	}

	// Eliminar un alumno mediante su identificador
	@DeleteMapping("alumno/{idAlumno}")
	public ResponseEntity<?> deleteAlumno(@PathVariable String idAlumno) {
		as.deleteById(idAlumno);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	// Modificar un alumno
	@PutMapping("alumno/{idAlumno}")
	public ResponseEntity<?> putAlumno(@PathVariable String idAlumno) {
		as.editById(idAlumno);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	// Añadir un nuevo alumno
	@PostMapping("alumno")
	public ResponseEntity<?> postAlumno(@RequestBody Alumno nuevoAlumno) {
		Alumno a = as.save(nuevoAlumno);
		return ResponseEntity.status(HttpStatus.OK).body(a);
	}

	// Asociar un profesor a un alumno
	@PutMapping("alumno/{idAlumno}/profesor/{idProfesor}")
	public ResponseEntity<?> putProfesor(@RequestBody String idAlumno, String idProfesor) {
		as.findById(idAlumno);
		ps.findById(idProfesor);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	// Añadir un responsable a un alumno
	@PostMapping("alumno/{idAlumno}/responsable")
	public ResponseEntity<?> postResponsable(@RequestBody Alumno idAlumno, Responsable nuevoResponsable) {
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	// Eliminar un responsable de un alumno
	@DeleteMapping("alumno/{idAlumno}/responsable/{idResponsable}")
	public ResponseEntity<?> deleteResponsable(@RequestBody String idAlumno, String idResponsable) {
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
