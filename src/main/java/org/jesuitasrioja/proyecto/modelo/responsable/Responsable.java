package org.jesuitasrioja.proyecto.modelo.responsable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Responsable {
	
	@Id
	private long identificador;
	@Column
	private String telefono;
	@Column
	private String parentesco;
	@Column
	private String nombre;
	
	
	public Responsable(long identificador, String telefono, String parentesco, String nombre) {
		super();
		this.identificador = identificador;
		this.telefono = telefono;
		this.parentesco = parentesco;
		this.nombre = nombre;
	}
	
	
	public Responsable() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getIdentificador() {
		return identificador;
	}


	public void setIdentificador(long identificador) {
		this.identificador = identificador;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getParentesco() {
		return parentesco;
	}


	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return "Responsable [identificador=" + identificador + ", telefono=" + telefono + ", parentesco=" + parentesco
				+ ", nombre=" + nombre + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (identificador ^ (identificador >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((parentesco == null) ? 0 : parentesco.hashCode());
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
		Responsable other = (Responsable) obj;
		if (identificador != other.identificador)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (parentesco == null) {
			if (other.parentesco != null)
				return false;
		} else if (!parentesco.equals(other.parentesco))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

}
