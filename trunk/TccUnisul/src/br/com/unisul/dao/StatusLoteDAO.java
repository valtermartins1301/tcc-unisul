package br.com.unisul.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

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
	
	public StatusLote buscarStatusLotePeloId(Long id) {
		EntityManager entityManager = emf.createEntityManager();
		StatusLote statusLote = null;
			try {				
				Query query = entityManager.createQuery("select s from StatusLote s where s.id = :id");
				query.setParameter("id", id);
				
				statusLote = (StatusLote) query.getSingleResult();
			} finally {
				entityManager.close();
			}
		return statusLote;
	}
}
