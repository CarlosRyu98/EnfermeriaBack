package org.jesuitasrioja.proyecto.persistencia.repositories;

import org.jesuitasrioja.proyecto.modelo.clase.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, String> {

}
