package br.com.unisul.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.unisul.bean.Pedido;
import br.com.unisul.bean.StatusPedido;

public class StatusPedidoDAO {
	EntityManagerFactory emf;

	public StatusPedidoDAO() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
	}
	
	public List<StatusPedido> listAll(){
		EntityManager em = emf.createEntityManager();
		try{
			return em.createQuery("from StatusPedido", StatusPedido.class).getResultList();
		}finally{
			em.close();
		}
	}
}

