package org.jesuitasrioja.proyecto.persistencia.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.jesuitasrioja.proyecto.modelo.user.UserEntity;
import org.jesuitasrioja.proyecto.modelo.user.UserRole;
import org.jesuitasrioja.proyecto.persistencia.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserEntityService extends BaseService<UserEntity, String, UserEntityRepository> {
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	public Optional<UserEntity> findByUserName(String username) {
		return this.repositorio.findByUsername(username);
	}
	
	// Registrar nuevo usuario
	public UserEntity nuevoUsuario(UserEntity userEntity) {
		
		Set<UserRole> defaultRoles = new HashSet<UserRole>();
		userEntity.setId(String.valueOf(Math.abs(new Random().nextInt())));
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		if (userEntity.getRoles() == null) {
			defaultRoles.add(UserRole.USER);
			userEntity.setRoles(defaultRoles);
		} else if (userEntity.getRoles().size() == 0) {
			defaultRoles.add(UserRole.USER);
			userEntity.setRoles(defaultRoles);
		}
		return this.repositorio.save(userEntity);
	}

}
