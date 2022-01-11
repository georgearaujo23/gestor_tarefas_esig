package br.gestor_tarefas_esig.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.gestor_tarefas_esig.model.Entity.Responsavel;

public class ResponsavelDAO {
	
    private EntityManager getEntityManager() {
	    EntityManagerFactory factory = null;
	    EntityManager entityManager = null;
	    factory = Persistence.createEntityManagerFactory("GestorTarefasDB");
	    if (entityManager == null) {
	    	entityManager = factory.createEntityManager();
	    }
	    
	    return entityManager;
	  }

    @SuppressWarnings("unchecked")
	public List<Responsavel> listar() throws Exception {
		List<Responsavel>  responsaveis = null;
		EntityManager entityManager = getEntityManager();
	    try {
	      entityManager.getTransaction().begin();
	      responsaveis = entityManager.createQuery("Select u from br.gestor_tarefas_esig.model.Entity.Responsavel u")
	    		  .getResultList();
	      
	    }catch (Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
	    return responsaveis;
	  }
	  
	  public Responsavel getById(int id) throws Exception {
		  Responsavel responsavel = null;
		  EntityManager entityManager = getEntityManager();
		  try {
			  entityManager.getTransaction().begin();
			  responsavel = entityManager.find(Responsavel.class, id);
	      
		  }catch (Exception e) {
			  e.printStackTrace();
		  }finally {
				entityManager.close();
			}
	    return responsavel;
	  }

}
