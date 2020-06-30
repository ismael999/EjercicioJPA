package es.eoi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bancos", schema = "jpa")
public class Banco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre", length = 20)
	private String nombre;

	@Column(name = "ciudad", length = 20)
	private String ciudad;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "banco", cascade=CascadeType.ALL)
	private List<Cuenta> cuentas;

	public Banco() {
		
	}
	
	
	
	public Banco(String nombre, String ciudad) {
		this.nombre = nombre;
		this.ciudad = ciudad;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String toString() {
		return "Nombre: " + this.nombre + " | Ciudad: " + this.ciudad;
	}
	
}
