package br.com.unisul.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.unisul.bean.Produto;

public class ProdutoDAO {
	EntityManagerFactory emf;

	public ProdutoDAO() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
	}
	
	public List<Produto> listAll(){
		EntityManager em = emf.createEntityManager();
		try{
			return em.createQuery("from Produto", Produto.class).getResultList();
		}finally{
			em.close();
		}
	}
	
	public void salvar(Produto produto){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{
				et.begin();
				em.persist(produto);
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
				Produto produto = em.find(Produto.class, id);
				if(produto != null){
					et.begin();
					em.remove(produto);
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
	
	public void editar(Produto produto){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{				
				Produto produtoEncontrado = em.find(Produto.class, produto.getIdProduto());
				
				if(produtoEncontrado != null){
					et.begin();					
					produtoEncontrado.setIdProduto(produto.getIdProduto());
					produtoEncontrado.setDescricao(produto.getDescricao());
					produtoEncontrado.setPreco(produto.getPreco());
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
	
	public Produto buscarProdutoPeloId(Long id) {
		EntityManager entityManager = emf.createEntityManager();
		Produto produto = null;
			try {				
				Query query = entityManager.createQuery("select p from Produto p where p.id = :id");
				query.setParameter("id", id);
				
				produto = (Produto) query.getSingleResult();
			} finally {
				entityManager.close();
			}
		return produto;
	}
}
