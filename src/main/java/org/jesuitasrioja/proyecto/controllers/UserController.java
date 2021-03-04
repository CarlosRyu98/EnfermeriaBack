package org.jesuitasrioja.proyecto.controllers;

import java.util.List;

import org.jesuitasrioja.proyecto.modelo.user.UserEntity;
import org.jesuitasrioja.proyecto.persistencia.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {
	
	@Autowired
	private UserEntityService ues;
	
	// Registrar nuevo usuario
	@PostMapping("/user")
	public ResponseEntity<UserEntity> nuevoUsuario(@RequestBody UserEntity newUser) {
		
		try {
			// Si ya está en el sistema
			if (!ues.findByUserName(newUser.getUsername()).isPresent()) {
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(ues.nuevoUsuario(newUser));
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	// Mostrar todos los usuarios
	@GetMapping("/users")
	public ResponseEntity<List<UserEntity>> getUsuarios() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(ues.findAll());
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
