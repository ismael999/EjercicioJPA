package es.eoi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes", schema = "jpa")
public class Cliente {

	@Id
	private String dni;

	@Column(name = "nombre", length = 20)
	private String nombre;

	@Column(name = "direccion", length = 40)
	private String direccion;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade=CascadeType.ALL)
	private List<Cuenta> cuentas;

	
	public Cliente() {
		
	}
	
	public Cliente(String dni, String nombre, String direccion) {
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public String toString() {
		return "Nombre: " + this.nombre + " | Dirección: " + this.direccion;
	}

}
