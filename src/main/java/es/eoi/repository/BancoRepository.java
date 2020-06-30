package es.eoi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import es.eoi.app.MundoBancario;
import es.eoi.entity.Banco;

public class BancoRepository {

	private static EntityManager em;
	
	public BancoRepository() {
		em = MundoBancario.em;
	}
	
	public List<Banco> getAll() {
		em.getTransaction().begin();
		Query query = em.createQuery("FROM Banco");
		List<Banco> bancos = query.getResultList();
		return bancos;
	}

	public Banco findById(int id) {
		Banco banco = em.find(Banco.class, id);
		return banco;
	}

	public void create(Banco banco) {
		em.getTransaction().begin();
		em.persist(banco);
		em.getTransaction().commit();
	}

	public void update(Banco banco) {
		em.getTransaction().begin();
		em.merge(banco);
		em.getTransaction().commit();
	}
	
	public void delete(int id) {
		Banco banco = findById(id);
		em.getTransaction().begin();
		banco = em.merge(banco);
		em.remove(banco);
		em.getTransaction().commit();
	}

}
