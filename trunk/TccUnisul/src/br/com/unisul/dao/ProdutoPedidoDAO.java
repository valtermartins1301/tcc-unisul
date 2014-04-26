package br.com.unisul.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.com.unisul.bean.ProdutoPedido;

public class ProdutoPedidoDAO {
	EntityManagerFactory emf;

	public ProdutoPedidoDAO() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
	}
	public void salvar(ProdutoPedido produtoPedido){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{
				et.begin();
				em.persist(produtoPedido);
				et.commit();
			}finally{
				if(et.isActive())
					et.rollback();
			}
		}finally{
			em.close();
		}		
	}
}
