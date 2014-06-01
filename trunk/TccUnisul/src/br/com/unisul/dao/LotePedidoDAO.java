package br.com.unisul.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.unisul.bean.LotePedido;

public class LotePedidoDAO {
	EntityManagerFactory emf;

	public LotePedidoDAO() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
	}
	
	public List<LotePedido> listAll(){
		EntityManager em = emf.createEntityManager();
		try{
			return em.createQuery("from LotePedido order by idLotePedido", LotePedido.class).getResultList();
		}finally{
			em.close();
		}
	}
	
	public void salvar(LotePedido lotePedido){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{
				et.begin();
				em.persist(lotePedido);
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
				LotePedido lotePedido = em.find(LotePedido.class, id);
				if(lotePedido != null){
					et.begin();
					em.remove(lotePedido);
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
	
	public void editar(LotePedido lotePedido){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{				
				LotePedido lotePedidoEncontrado = em.find(LotePedido.class, lotePedido.getIdLotePedido());
				
				if(lotePedidoEncontrado != null){
					et.begin();
					em.merge(lotePedido);
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
	
	public LotePedido buscarLotePedidoPeloId(Long id) {
		EntityManager entityManager = emf.createEntityManager();
		LotePedido lotePedido = null;
			try {				
				Query query = entityManager.createQuery("select p from LotePedido p where p.id = :id");
				query.setParameter("id", id);
				
				lotePedido = (LotePedido) query.getSingleResult();
			} finally {
				entityManager.close();
			}
		return lotePedido;
	}
}
