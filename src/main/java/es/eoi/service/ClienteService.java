package es.eoi.service;

import java.util.List;

import es.eoi.entity.Cliente;
import es.eoi.repository.ClienteRepository;

public class ClienteService {

	private ClienteRepository repository = new ClienteRepository();

	public List<Cliente> getAll() {
		return repository.getAll();
	}

	public Cliente findByDni(String dni) {
		return repository.findByDni(dni);
	}

	public void create(Cliente cliente) {
		repository.create(cliente);
	}

	public void delete(Cliente cliente) {

		repository.delete(cliente);
	}

	public void update(Cliente cliente) {
		repository.update(cliente);
	}

}
