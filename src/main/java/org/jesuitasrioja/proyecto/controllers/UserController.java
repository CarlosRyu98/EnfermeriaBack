package org.jesuitasrioja.proyecto.controllers;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.jesuitasrioja.proyecto.modelo.user.GetUserDTO;
import org.jesuitasrioja.proyecto.modelo.user.UserDTOConverter;
import org.jesuitasrioja.proyecto.modelo.user.UserEntity;
import org.jesuitasrioja.proyecto.persistencia.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {
	
	@Autowired
	private UserEntityService ues;
	

	@Autowired
	private UserDTOConverter userDTOConverter;
	
	// Añadir nuevo usuario
	@PostMapping("/usuario")
	public ResponseEntity<UserEntity> nuevoUsuario(
			@RequestBody UserEntity nuevoUsuario) {
		
		try {
			// Si ya está en el sistema
			if (!ues.findByUserName(nuevoUsuario.getUsername()).isPresent()) {
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(ues.nuevoUsuario(nuevoUsuario));
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	// Eliminar usuario por id
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> deleteUsuario(
			@PathVariable String idUsuario) {
		ues.deleteById(idUsuario);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// Modificar usuario por id
	@PutMapping("/usuario/{id}")
	public ResponseEntity<?> modifyUsuario(
			@PathVariable String idUsuario) {
		Optional<UserEntity> usuarioOptional = ues.findById(idUsuario);
		if (usuarioOptional.isPresent()) {
			ues.save(usuarioOptional.get());
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// Mostrar usuarios paginado
	@GetMapping("/usuarios")
	public ResponseEntity<?> getUsuarios(
			@PageableDefault(size = 10, page = 0) Pageable pageable) {
		try {
			Page<UserEntity> pagina = ues.findAll(pageable);
			Page<GetUserDTO> paginaDTO = pagina.map(new Function<UserEntity, GetUserDTO>() {
				@Override
				public GetUserDTO apply(UserEntity usuario) {
					return userDTOConverter.convertUserEntityToGetUserDto(usuario);
				}
			});
			return ResponseEntity.status(HttpStatus.OK).body(paginaDTO);
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					e.getMessage());
		}
	}
	
	// Consultar información de usuario por id
	@GetMapping("/usuario/{id}")
	public ResponseEntity<UserEntity> getUsuario(
			@PathVariable String idUsuario) {
		Optional<UserEntity> usuario = ues.findById(idUsuario);
		if (usuario.isPresent()) {
		return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
