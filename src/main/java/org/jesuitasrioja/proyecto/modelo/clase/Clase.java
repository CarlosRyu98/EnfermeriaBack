package org.jesuitasrioja.proyecto.modelo.clase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clase")
public class Clase {
	
	@Id
	private String identificador;
	@Column
	private String codigoBreve;
	@Column
	private String descripcion;

}
