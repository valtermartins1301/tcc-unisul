package br.com.unisul.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.unisul.bean.Endereco;



public class EnderecoDAO {
	EntityManagerFactory emf;

	public EnderecoDAO() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
	}
	
	public List<Endereco> listAll(){
		EntityManager em = emf.createEntityManager();
		try{
			return em.createQuery("from Endereco", Endereco.class).getResultList();
		}finally{
			em.close();
		}
	}
	
	public void salvar(Endereco endereco){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{
				et.begin();
				em.persist(endereco);
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
				Endereco endereco = em.find(Endereco.class, id);
				if(endereco != null){
					et.begin();
					em.remove(endereco);
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
	
	public void editar(Endereco endereco){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{				
				Endereco enderecoEncontrado = em.find(Endereco.class, endereco.getIdEndereco());
				
				if(enderecoEncontrado != null){
					et.begin();
					enderecoEncontrado.setIdEndereco(endereco.getIdEndereco());
					enderecoEncontrado.setRua(endereco.getRua());
					enderecoEncontrado.setComplemento(endereco.getComplemento());
					enderecoEncontrado.setNumero(endereco.getNumero());
					enderecoEncontrado.setCidade(endereco.getCidade());
					enderecoEncontrado.setCep(endereco.getCep());
					enderecoEncontrado.setLatitude(endereco.getLatitude());
					enderecoEncontrado.setLongitude(endereco.getLongitude());
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
	
	public Endereco buscarEnderecoPeloId(Long id) {
		EntityManager entityManager = emf.createEntityManager();
		Endereco endereco = null;
			try {				
				Query query = entityManager.createQuery("select e from Endereco e where e.id = :id");
				query.setParameter("id", id);
				
				endereco = (Endereco) query.getSingleResult();
			} finally {
				entityManager.close();
			}
		return endereco;
	}
}
