package es.eoi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import es.eoi.entity.Banco;

public class BancoRepository {

	private EntityManager openConnection() {
		return Persistence.createEntityManagerFactory("EjercicioJPA").createEntityManager();
	}

	public List<Banco> getAll() {
		EntityManager em = openConnection();
		em.getTransaction().begin();
		Query query = em.createQuery("FROM Banco");
		List<Banco> bancos = query.getResultList();
		em.close();
		return bancos;
	}

	public Banco findById(int id) {
		EntityManager em = openConnection();
		Banco banco = em.find(Banco.class, id);
		em.close();
		return banco;
	}

	public void create(Banco banco) {
		EntityManager em = openConnection();
		em.getTransaction().begin();
		em.persist(banco);
		em.getTransaction().commit();
		em.close();
	}

	public void update(Banco banco) {
		EntityManager em = openConnection();
		em.getTransaction().begin();
		em.merge(banco);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete(int id) {
		EntityManager em = openConnection();
		Banco banco = findById(id);
		em.getTransaction().begin();
		banco = em.merge(banco);
		em.remove(banco);
		em.getTransaction().commit();
		em.close();
	}

}
