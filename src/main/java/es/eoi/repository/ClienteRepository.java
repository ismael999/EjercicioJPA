package es.eoi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import es.eoi.entity.Cliente;

public class ClienteRepository {

	private EntityManager openConnection() {
		return Persistence.createEntityManagerFactory("EjercicioJPA").createEntityManager();
	}
	
	public List<Cliente> getAll(){
		EntityManager em = openConnection();
		Query query = em.createQuery("FROM Cliente");
		List<Cliente> clientes = query.getResultList();
		em.close();
		return clientes;
	}
	
	public Cliente findByDni(String dni) {
		EntityManager em = openConnection();
		Cliente cliente = em.find(Cliente.class, dni);
		em.close();
		return cliente;
	}
	
	public void create(Cliente cliente) {
		EntityManager em = openConnection();
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete(Cliente cliente) {
		EntityManager em = openConnection();
		em.getTransaction().begin();
		cliente = em.merge(cliente);
		em.remove(cliente);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update (Cliente cliente) {
		EntityManager em = openConnection();
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
		em.close();
	}
}
