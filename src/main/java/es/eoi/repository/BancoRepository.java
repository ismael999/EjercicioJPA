package es.eoi.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import es.eoi.app.MundoBancario;
import es.eoi.entity.Banco;
import es.eoi.entity.Cliente;

public class BancoRepository {

	private static EntityManager em;
	
	public BancoRepository() {
		em = MundoBancario.em;
	}
	
	public List<Banco> getAll() {
		
		List<Banco> bancos = new ArrayList<Banco>();
		
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("FROM Banco");
			bancos = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bancos;
	}

	public Banco findById(int id) {
		Banco banco = null;
		
		try {
			banco = em.find(Banco.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return banco;
	}

	public void create(Banco banco) {
		try {
			em.getTransaction().begin();
			em.persist(banco);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Banco banco) {
		try {
			em.getTransaction().begin();
			em.merge(banco);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try {
			Banco banco = findById(id);
			em.getTransaction().begin();
			banco = em.merge(banco);
			em.remove(banco);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
