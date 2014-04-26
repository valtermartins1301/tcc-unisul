package br.com.unisul.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.unisul.bean.Pedido;


public class PedidoDAO {
	EntityManagerFactory emf;

	public PedidoDAO() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
	}
	
	public List<Pedido> listAll(){
		EntityManager em = emf.createEntityManager();
		try{
			return em.createQuery("from Pedido", Pedido.class).getResultList();
		}finally{
			em.close();
		}
	}
	
	public void salvar(Pedido pedido){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{
				et.begin();
				em.persist(pedido);
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
				Pedido pedido = em.find(Pedido.class, id);
				if(pedido != null){
					et.begin();
					em.remove(pedido);
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
	
	public void editar(Pedido pedido){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{				
				Pedido pedidoEncontrado = em.find(Pedido.class, pedido.getIdPedido());
				
				if(pedidoEncontrado != null){
					et.begin();
					pedidoEncontrado.setIdPedido(pedido.getIdPedido());
					pedidoEncontrado.setCliente(pedido.getCliente());
					pedidoEncontrado.setData(pedido.getData());
					pedidoEncontrado.setValorTotalPedido(pedido.getValorTotalPedido());
					pedidoEncontrado.setObservacao(pedido.getObservacao());
					pedidoEncontrado.setStatusPedido(pedido.getStatusPedido());
					pedidoEncontrado.setRetiradoLocal(pedido.getRetiradoLocal());
					pedidoEncontrado.setProdutoPedidoList(pedido.getProdutoPedidoList());
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
	
	public Pedido buscarPedidoPeloId(Long id) {
		EntityManager entityManager = emf.createEntityManager();
		Pedido pedido = null;
			try {				
				Query query = entityManager.createQuery("select p from Pedido p where p.id = :id");
				query.setParameter("id", id);
				
				pedido = (Pedido) query.getSingleResult();
			} finally {
				entityManager.close();
			}
		return pedido;
	}
}
