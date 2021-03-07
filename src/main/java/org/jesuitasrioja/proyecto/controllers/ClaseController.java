package org.jesuitasrioja.proyecto.controllers;

import java.util.Optional;

import org.jesuitasrioja.proyecto.modelo.clase.Clase;
import org.jesuitasrioja.proyecto.persistencia.services.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClaseController {
	
	@Autowired
	private ClaseService cs;
	
	// Añadir nueva clase
	@PostMapping("clase")
	public ResponseEntity<?> postClase(@RequestBody Clase nuevaClase) {
		Clase c = cs.save(nuevaClase);
		return ResponseEntity.status(HttpStatus.OK).body(c);
	}
	
	// Eliminar clase por su id
	@DeleteMapping("clase/{idClase}")
	public ResponseEntity<?> deleteClase(@PathVariable String idClase) {
		cs.deleteById(idClase);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// Mostrar clase por id
	@GetMapping("clase/{idClase}")
	public Optional<Clase> getClase(@PathVariable String idClase) {
		return cs.findById(idClase);
	}

}
