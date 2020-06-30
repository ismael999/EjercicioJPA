package es.eoi.service;

import java.util.List;

import javax.persistence.EntityManager;

import es.eoi.entity.Banco;
import es.eoi.repository.BancoRepository;

public class BancoService {
	
	private BancoRepository repository = new BancoRepository();
	
	public List<Banco> getAll() {
		return repository.getAll();
	}

	public Banco findById(int id) {
		return repository.findById(id);
	}

	public void create(Banco banco) {
		repository.create(banco);
	}

	public void update(Banco banco) {
		repository.update(banco);
	}
	
	public void delete(int id) {
		repository.delete(id);
	}
}
