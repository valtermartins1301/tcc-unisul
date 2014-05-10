package br.com.unisul.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import br.com.unisul.bean.Pedido;
import br.com.unisul.bean.ProdutoPedido;
import br.com.unisul.bean.ProdutoPedidoId;

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
	
	public void excluir(ProdutoPedidoId id){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{
				ProdutoPedido produtoPedido = em.find(ProdutoPedido.class, id);
				if(produtoPedido != null){
					et.begin();
					em.remove(produtoPedido);
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
}
