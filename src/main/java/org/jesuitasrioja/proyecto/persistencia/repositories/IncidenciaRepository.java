package org.jesuitasrioja.proyecto.persistencia.repositories;

import org.jesuitasrioja.proyecto.modelo.incidencia.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, String> {
	
}
