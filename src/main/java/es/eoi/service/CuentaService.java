package es.eoi.service;

import java.util.List;

import javax.persistence.EntityManager;

import es.eoi.entity.Cliente;
import es.eoi.entity.Cuenta;
import es.eoi.repository.CuentaRepository;

public class CuentaService {
	private CuentaRepository repository = new CuentaRepository();

	public List<Cuenta> getAll() {
		return repository.getAll();
	}

	public Cuenta findById(int id) {
		return repository.findById(id);
	}

	public void create(Cuenta cuenta) {
		repository.create(cuenta);
	}

	public void delete(Cuenta cuenta) {
		repository.delete(cuenta);
	}

	public void update(Cuenta cuenta) {
		repository.update(cuenta);
	}
	
	public List<Cuenta> findByBanco(int idBanco) {
		return repository.findByBanco(idBanco);
	}
	
	public List<Cuenta> findByCliente(String dni){
		return repository.findByCliente(dni);
	}
}
