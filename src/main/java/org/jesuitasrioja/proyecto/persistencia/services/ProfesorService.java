package org.jesuitasrioja.proyecto.persistencia.services;

import org.jesuitasrioja.proyecto.modelo.profesor.Profesor;
import org.jesuitasrioja.proyecto.persistencia.repositories.ProfesorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService extends BaseService<Profesor, String, ProfesorRepository> {

}
