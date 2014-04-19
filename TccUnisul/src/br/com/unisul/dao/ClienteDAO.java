package br.com.unisul.dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.unisul.bean.Cliente;

public class ClienteDAO {
	EntityManagerFactory emf;

	public ClienteDAO() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
	}
	
	public List<Cliente> listAll(){
		EntityManager em = emf.createEntityManager();
		try{
			return em.createQuery("from Cliente", Cliente.class).getResultList();
		}finally{
			em.close();
		}
	}
	
	public void salvar(Cliente cliente){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{
				et.begin();
				em.persist(cliente);
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
				Cliente cliente = em.find(Cliente.class, id);
				if(cliente != null){
					et.begin();
					em.remove(cliente);
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
	
	public void editar(Cliente cliente){
		EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction et = em.getTransaction();
			try{				
				Cliente clienteEncontrado = em.find(Cliente.class, cliente.getId());
				
				if(clienteEncontrado != null){
					et.begin();
					clienteEncontrado.setNome(cliente.getNome());
					clienteEncontrado.setEndereco(cliente.getEndereco());
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
	
	public Cliente buscarClientePeloCPF(String cpf){
		EntityManager em = emf.createEntityManager();
		Cliente cliente = null;
		try{
			Query query = em.createQuery("select c from Cliente c where c.cpf = :cpf");
			query.setParameter("cpf", cpf);
			cliente = (Cliente) query.getSingleResult();
		}catch(Exception e){
			return cliente;
		}finally{
			em.close();
		}
		
		return cliente;
	}
}
