package org.jesuitasrioja.proyecto.modelo.alumno;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDTO implements Serializable {

	private String dni;
	private String nombre;
	private String fechaNacimiento;
	private String telefono;
	private String direccion;
	
}
