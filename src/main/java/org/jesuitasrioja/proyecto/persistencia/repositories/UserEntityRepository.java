package org.jesuitasrioja.proyecto.persistencia.repositories;

import java.util.Optional;

import org.jesuitasrioja.proyecto.modelo.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, String> {
	
	Optional<UserEntity> findByUsername(String username);
	
}
