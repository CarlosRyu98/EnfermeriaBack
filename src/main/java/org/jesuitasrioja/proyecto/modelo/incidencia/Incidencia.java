package org.jesuitasrioja.proyecto.modelo.incidencia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.jesuitasrioja.proyecto.modelo.alumno.Alumno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incidencias")
public class Incidencia {
	
	@Id
	private String identificador;
	@Column
	private String sintomatologia;
	@Column
	private Date fecha;
	@ManyToOne
	private Alumno alumno;
	

}
