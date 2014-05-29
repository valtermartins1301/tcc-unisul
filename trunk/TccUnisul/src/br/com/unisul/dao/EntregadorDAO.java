package br.com.unisul.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.unisul.bean.Entregador;

public class EntregadorDAO {
	EntityManagerFactory emf;

	public EntregadorDAO() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
	}
	
	public List<Entregador> listAll(){
		EntityManager em = emf.createEntityManager();
		try{
			return em.createQuery("from Entregador where flag_excluido != 't'", Entregador.class).getResultList();
		}finally{
			em.close();
		}
	}
	
	public void salvar(Entregador entregador){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{
				et.begin();
				em.persist(entregador);
				et.commit();
			}finally{
				if(et.isActive())
					et.rollback();
			}
		}finally{
			em.close();
		}		
	}
	
	public void excluir(Long id){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{
				Entregador entregador = em.find(Entregador.class, id);
				if(entregador != null){
					et.begin();
					entregador.setFlagExcluido(true);
					et.commit();
				}
			}finally{
				if(et.isActive())
					et.rollback();
			}
		}finally{
			em.close();
		}
	}
	
	public void editar(Entregador entregador){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{				
				Entregador entregadorEncontrado = em.find(Entregador.class, entregador.getIdEntregador());
				
				if(entregadorEncontrado != null){
					et.begin();
					em.merge(entregador);
					et.commit();
				}
			}finally{
				if(et.isActive())
					et.rollback();
			}
		}finally{
			em.close();
		}
	}
	
	public Entregador buscarEntregadorPeloId(Long id) {
		EntityManager entityManager = emf.createEntityManager();
		Entregador entregador = null;
			try {				
				Query query = entityManager.createQuery("select e from Entregador e where e.id = :id");
				query.setParameter("id", id);
				
				entregador = (Entregador) query.getSingleResult();
			} finally {
				entityManager.close();
			}
		return entregador;
	}
}
