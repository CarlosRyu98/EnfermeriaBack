package org.jesuitasrioja.proyecto.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jesuitasrioja.proyecto.modelo.alumno.Alumno;
import org.jesuitasrioja.proyecto.modelo.incidencia.Incidencia;
import org.jesuitasrioja.proyecto.persistencia.services.AlumnoService;
import org.jesuitasrioja.proyecto.persistencia.services.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncidenciaController {
	
	@Autowired
	private IncidenciaService is;
	
	@Autowired
	private AlumnoService as;
	
	// Crear una nueva incidencia para un alumno
	@PostMapping("incidencia/alumno/{idAlumno}")
	public ResponseEntity<?> postIncidencia(
			@RequestBody Incidencia nuevaIncidencia, @PathVariable String idAlumno) {
		Optional<Alumno> alumno = as.findById(idAlumno);
		if (alumno.isPresent()) {
			nuevaIncidencia.setAlumno(alumno.get());
			Incidencia i = is.save(nuevaIncidencia);
			return ResponseEntity.status(HttpStatus.OK).body(i);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	// Eliminar incidencia por su id
	@DeleteMapping("incidencia/{idIncidencia}")
	public ResponseEntity<?> deleteClase(@PathVariable String idIncidencia) {
		is.deleteById(idIncidencia);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// Mostrar incidencia por id
	@GetMapping("incidencia/{idIncidencia}")
	public Optional<Incidencia> getIncidencia(@PathVariable String idIncidencia) {
		return is.findById(idIncidencia);
	}
	
	// Ver todas las incidencias de un alumno por su id
	@GetMapping("incidencias/alumno/{idAlumno")
	public ResponseEntity<?> getIncidenciasPorAlumno(@PathVariable String idAlumno) {
		List<Incidencia> incidencias = is.findAll();
		Optional<Alumno> alumno = as.findById(idAlumno);
		List<Incidencia> incidenciasPorAlumno = new ArrayList<Incidencia>();
		for (Incidencia incidencia: incidencias) {
			if (incidencia.getAlumno().equals(alumno.get())) {
				incidenciasPorAlumno.add(incidencia);
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(incidencias);
	}
	
	// Ver todas las incidencias en un rango de fechas
	@GetMapping("incidencias?from=<fechaInicio>&to=<fechaFin>")
	public ResponseEntity<?> getIncidenciasPorFecha(
			@PageableDefault(size=10, page=0) Pageable pageable,
			@PathVariable String idAlumno, Date dateFrom, Date dateTo) {
			
			List<Incidencia> incidencias = is.findAll();
			List<Incidencia> incidenciasEnRango = new ArrayList<Incidencia>();
			for (Incidencia incidencia: incidencias) {
				if (incidencia.getFecha().compareTo(dateFrom) > 0 && incidencia.getFecha().compareTo(dateTo) < 0) {
					incidenciasEnRango.add(incidencia);
				}
			}
			Page<Incidencia> pagina = new PageImpl<Incidencia>(incidenciasEnRango, pageable, incidenciasEnRango.size());
			
		return ResponseEntity.status(HttpStatus.OK).body(pagina);
	}
	

}
