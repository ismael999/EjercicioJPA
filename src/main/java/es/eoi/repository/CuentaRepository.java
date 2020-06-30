package es.eoi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import es.eoi.app.MundoBancario;
import es.eoi.entity.Cliente;
import es.eoi.entity.Cuenta;

public class CuentaRepository {

	private static EntityManager em;

	public CuentaRepository() {
		em = MundoBancario.em;
	}

	public List<Cuenta> getAll() {
		Query query = em.createQuery("FROM Cuenta");
		List<Cuenta> cuentas = query.getResultList();
		return cuentas;
	}

	public Cuenta findById(int id) {
		Cuenta cuenta = em.find(Cuenta.class, id);
		return cuenta;
	}

	public List<Cuenta> findByBanco(int idBanco) {
		Query query = em.createQuery("FROM Cuenta c WHERE c.banco.id = :id");
		query.setParameter("id", idBanco);
		List<Cuenta> cuentas = null;
		cuentas = query.getResultList();
		return cuentas;
	}

	public List<Cuenta> findByCliente(String dni) {
		Query query = em.createQuery("FROM Cuenta c WHERE c.cliente.dni = :dni");
		query.setParameter("dni", dni);
		List<Cuenta> cuentas = null;
		cuentas = query.getResultList();
		return cuentas;
	}

	public void create(Cuenta cuenta) {
		em.getTransaction().begin();
		em.persist(cuenta);
		em.getTransaction().commit();
	}

	public void delete(Cuenta cuenta) {
		em.getTransaction().begin();
		cuenta = em.merge(cuenta);
		em.remove(cuenta);
		em.getTransaction().commit();
	}

	public void update(Cuenta cuenta) {
		em.getTransaction().begin();
		em.merge(cuenta);
		em.getTransaction().commit();
	}

}
