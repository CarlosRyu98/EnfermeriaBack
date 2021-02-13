package org.jesuitasrioja.proyecto.persistencia.repositories;

import org.jesuitasrioja.proyecto.modelo.alumno.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, String> {

}
