package br.com.unisul.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.unisul.bean.StatusLote;

public class StatusLoteDAO {
	EntityManagerFactory emf;

	public StatusLoteDAO() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
	}
	
	public List<StatusLote> listAll(){
		EntityManager em = emf.createEntityManager();
		try{
			return em.createQuery("from StatusLote", StatusLote.class).getResultList();
		}finally{
			em.close();
		}
	}
}
