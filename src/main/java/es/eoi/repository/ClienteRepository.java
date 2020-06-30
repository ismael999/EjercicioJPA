package es.eoi.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.eoi.app.MundoBancario;
import es.eoi.entity.Cliente;

public class ClienteRepository {

	private static EntityManager em;
	
	public ClienteRepository() {
		em = MundoBancario.em;
	}
	
	public List<Cliente> getAll(){
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			Query query = em.createQuery("FROM Cliente");
			clientes = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public Cliente findByDni(String dni) {
		Cliente cliente = null;
		try {
			cliente = em.find(Cliente.class, dni);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}
	
	public void create(Cliente cliente) {
		try {
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Cliente cliente) {
		try {
			em.getTransaction().begin();
			em.remove(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update (Cliente cliente) {
		try {
			em.getTransaction().begin();
			em.merge(cliente);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
