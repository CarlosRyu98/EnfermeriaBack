package org.jesuitasrioja.proyecto.modelo.alumno;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.jesuitasrioja.proyecto.modelo.clase.Clase;
import org.jesuitasrioja.proyecto.modelo.profesor.Profesor;
import org.jesuitasrioja.proyecto.modelo.responsable.Responsable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alumnos")
public class Alumno {
	
	@Id
	private String identificador;
	@Column
	private String dni;
	@Column
	private String nombre;
	@Column
	private String fechaNacimiento;
	@Column
	private String telefono;
	@Column
	private String direccion;
	@ManyToOne
	private Profesor tutor;
	@ManyToOne
	private Responsable responsable;
	@ManyToOne
	private Clase clase;

}
