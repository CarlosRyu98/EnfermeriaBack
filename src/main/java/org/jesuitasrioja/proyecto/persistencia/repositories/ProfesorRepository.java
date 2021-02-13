package org.jesuitasrioja.proyecto.persistencia.repositories;

import org.jesuitasrioja.proyecto.modelo.profesor.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, String>{

}
