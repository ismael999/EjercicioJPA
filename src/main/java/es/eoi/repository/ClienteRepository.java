package es.eoi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import es.eoi.app.MundoBancario;
import es.eoi.entity.Cliente;

public class ClienteRepository {

	private static EntityManager em;
	
	public ClienteRepository() {
		em = MundoBancario.em;
	}
	
	public List<Cliente> getAll(){
		Query query = em.createQuery("FROM Cliente");
		List<Cliente> clientes = query.getResultList();
		return clientes;
	}
	
	public Cliente findByDni(String dni) {
		Cliente cliente = em.find(Cliente.class, dni);
		return cliente;
	}
	
	public void create(Cliente cliente) {
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
	}
	
	public void delete(Cliente cliente) {
		em.getTransaction().begin();
		cliente = em.merge(cliente);
		em.remove(cliente);
		em.getTransaction().commit();
	}
	
	public void update (Cliente cliente) {
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
	}
}
