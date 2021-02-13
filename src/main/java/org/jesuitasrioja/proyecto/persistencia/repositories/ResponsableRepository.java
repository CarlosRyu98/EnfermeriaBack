package org.jesuitasrioja.proyecto.persistencia.repositories;

import org.jesuitasrioja.proyecto.modelo.responsable.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, String>{

}
