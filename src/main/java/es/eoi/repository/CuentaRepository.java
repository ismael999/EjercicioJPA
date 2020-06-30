package es.eoi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import es.eoi.entity.Cliente;
import es.eoi.entity.Cuenta;

public class CuentaRepository {
	
	private EntityManager openConnection() {
		return Persistence.createEntityManagerFactory("EjercicioJPA").createEntityManager();
	}
	
	public List<Cuenta> getAll(){
		EntityManager em = openConnection();
		Query query = em.createQuery("FROM Cuenta");
		List<Cuenta> cuentas = query.getResultList();
		//em.close();
		return cuentas;
	}
	
	public Cuenta findById(int id) {
		EntityManager em = openConnection();
		Cuenta cuenta = em.find(Cuenta.class, id);
		em.close();
		return cuenta;
	}
	
	public List<Cuenta> findByBanco(int idBanco){
		EntityManager em = openConnection();
		Query query = em.createQuery("FROM Cuenta c WHERE c.banco.id = :id");
		query.setParameter("id", idBanco);
		List<Cuenta> cuentas = null;
		cuentas = query.getResultList();
		return cuentas;
	}
	
	public List<Cuenta> findByCliente(String dni){
		EntityManager em = openConnection();
		Query query = em.createQuery("FROM Cuenta c WHERE c.cliente.dni = :dni");
		query.setParameter("dni", dni);
		List<Cuenta> cuentas = null;
		cuentas = query.getResultList();
		return cuentas;
	}
	
	public void create(Cuenta cuenta) {
		EntityManager em = openConnection();
		em.getTransaction().begin();
		em.persist(cuenta);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete (Cuenta cuenta) {
		EntityManager em = openConnection();
		em.getTransaction().begin();
		cuenta = em.merge(cuenta);
		em.remove(cuenta);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(Cuenta cuenta) {
		EntityManager em = openConnection();
		em.getTransaction().begin();
		em.merge(cuenta);
		em.getTransaction().commit();
		em.close();
	}
	
}
