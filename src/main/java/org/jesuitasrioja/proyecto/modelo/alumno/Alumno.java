package org.jesuitasrioja.proyecto.modelo.alumno;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.jesuitasrioja.proyecto.modelo.profesor.Profesor;
import org.jesuitasrioja.proyecto.modelo.responsable.Responsable;

@Entity
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

	public String getIdentificador() {
		return identificador;
	}


	public Alumno(String identificador, String dni, String nombre, String fechaNacimiento, String telefono, String direccion) {
		super();
		this.identificador = identificador;
		this.dni = dni;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.direccion = direccion;
	}


	public Alumno() {
		super();
	}


	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public Profesor getTutor() {
		return tutor;
	}


	public void setTutor(Profesor tutor) {
		this.tutor = tutor;
	}


	public Responsable getResponsable() {
		return responsable;
	}


	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}


	@Override
	public String toString() {
		return "Alumno [identificador=" + identificador + ", dni=" + dni + ", nombre=" + nombre + ", fechaNacimiento="
				+ fechaNacimiento + ", telefono=" + telefono + ", direccion=" + direccion + ", tutor=" + tutor
				+ ", responsable=" + responsable + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}
	

}
