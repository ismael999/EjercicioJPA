package es.eoi.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.eoi.app.MundoBancario;
import es.eoi.entity.Cuenta;

public class CuentaRepository {

	private static EntityManager em;

	public CuentaRepository() {
		em = MundoBancario.em;
	}

	public List<Cuenta> getAll() {
		
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		
		try {
			Query query = em.createQuery("FROM Cuenta");
			cuentas = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuentas;
	}

	public Cuenta findById(int id) {
		Cuenta cuenta = null;
		
		try {
			cuenta = em.find(Cuenta.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cuenta;
	}

	public List<Cuenta> findByBanco(int idBanco) {
		
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		
		try {
			Query query = em.createQuery("FROM Cuenta c WHERE c.banco.id = :id");
			query.setParameter("id", idBanco);
			cuentas = null;
			cuentas = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuentas;
	}

	public List<Cuenta> findByCliente(String dni) {
		
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		
		try {
			Query query = em.createQuery("FROM Cuenta c WHERE c.cliente.dni = :dni");
			query.setParameter("dni", dni);
			cuentas = null;
			cuentas = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuentas;
	}

	public void create(Cuenta cuenta) {
		try {
			em.getTransaction().begin();
			em.persist(cuenta);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Cuenta cuenta) {
		try {
			em.getTransaction().begin();
			em.remove(cuenta);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Cuenta cuenta) {
		try {
			em.getTransaction().begin();
			em.merge(cuenta);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
