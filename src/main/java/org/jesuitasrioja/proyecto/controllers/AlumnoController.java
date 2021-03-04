package org.jesuitasrioja.proyecto.controllers;

import java.util.Optional;
import java.util.function.Function;

import org.jesuitasrioja.proyecto.modelo.alumno.Alumno;
import org.jesuitasrioja.proyecto.modelo.alumno.AlumnoDTO;
import org.jesuitasrioja.proyecto.modelo.alumno.AlumnoDTOConverter;
import org.jesuitasrioja.proyecto.modelo.profesor.Profesor;
import org.jesuitasrioja.proyecto.modelo.responsable.Responsable;
import org.jesuitasrioja.proyecto.persistencia.services.AlumnoService;
import org.jesuitasrioja.proyecto.persistencia.services.ProfesorService;
import org.jesuitasrioja.proyecto.persistencia.services.ResponsableService;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class AlumnoController {

	@Autowired
	private AlumnoService as;

	@Autowired
	private ProfesorService ps;
	
	@Autowired
	private ResponsableService rs;

	@Autowired
	private AlumnoDTOConverter alumnoDTOConverter;

	@ApiOperation(value="Obtener información de un alumno a través de su identificador")
	@GetMapping("alumno/{idAlumno}")
	public Optional<Alumno> getAlumno(
			@ApiParam(name = "identificador",
			type = "String",
			value ="Identificador de la persona que se quiere obtener")
			@PathVariable String idAlumno) {
		return as.findById(idAlumno);
	}

	@ApiOperation(value="Obtener información de todos los alumnos de forma paginada por patrón DTO")
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

	@ApiOperation(value="Eliminar un alumno mediante su identificador")
	@DeleteMapping("alumno/{idAlumno}")
	public ResponseEntity<?> deleteAlumno(
			@ApiParam(name = "identificador",
			type = "String",
			value ="Identificador de la persona que se quiere eliminar")
			@PathVariable String idAlumno) {
		
		as.deleteById(idAlumno);
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}

	@ApiOperation(value="Modificar un alumno")
	@PutMapping("alumno/{idAlumno}")
	public ResponseEntity<?> putAlumno(
			@ApiParam(name = "identificador",
			type = "String",
			value ="Identificador de la persona que se quiere modificar")
			@PathVariable String idAlumno) {
		as.editById(idAlumno);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation(value="Añadir un nuevo alumno")
	@PostMapping("alumno")
	public ResponseEntity<?> postAlumno(
			@ApiParam(name = "alumno",
			type = "Alumno",
			value ="Alumno que se va a añadir")
			@RequestBody Alumno nuevoAlumno) {
		Alumno a = as.save(nuevoAlumno);
		return ResponseEntity.status(HttpStatus.OK).body(a);
	}

	@ApiOperation(value="Asociar un profesor a un alumno")
	@PutMapping("alumno/{idAlumno}/profesor/{idProfesor}")
	public ResponseEntity<?> putProfesor(
			@ApiParam(name = "identificador",
			type = "String",
			value ="Identificador de la persona que se quiere obtener")
			
			@PathVariable String idAlumno, String idProfesor) {
		Optional<Alumno> alumno = as.findById(idAlumno);
		Optional<Profesor> profesor = ps.findById(idProfesor);
		if (alumno.isPresent() && profesor.isPresent()) {
			alumno.get().setTutor(profesor.get());
			as.edit(alumno.get());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@ApiOperation(value="Añadir un responsable a un alumno")
	@PostMapping("alumno/{idAlumno}/responsable")
	public ResponseEntity<?> postResponsable(@RequestBody Responsable nuevoResponsable, @PathVariable String idAlumno) {
		Optional<Alumno> alumno = as.findById(idAlumno);
		if (alumno.isPresent()) {
			alumno.get().setResponsable(nuevoResponsable);
			as.edit(alumno.get());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@ApiOperation(value="Eliminar un responsable de un alumno")
	@DeleteMapping("alumno/{idAlumno}/responsable/{idResponsable}")
	public ResponseEntity<?> deleteResponsable(
			@ApiParam(name = "identificador",
			type = "String",
			value ="Identificador de la persona que se quiere obtener")
			@PathVariable String idAlumno, String idResponsable) {
		Optional<Alumno> alumno = as.findById(idAlumno);
		Optional<Responsable> responsable = rs.findById(idResponsable);
		if (alumno.isPresent() && responsable.isPresent() && alumno.get().getResponsable() == responsable.get()) {
			alumno.get().setResponsable(null);
			as.edit(alumno.get());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
