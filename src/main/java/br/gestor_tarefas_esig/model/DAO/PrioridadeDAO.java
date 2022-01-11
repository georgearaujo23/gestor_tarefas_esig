package br.gestor_tarefas_esig.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.gestor_tarefas_esig.model.Entity.Prioridade;

public class PrioridadeDAO {
	    
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
	  public List<Prioridade> listar() throws Exception {
		  List<Prioridade>  prioridades = null;
		  EntityManager entityManager = getEntityManager();
		  try {
			  entityManager.getTransaction().begin();
			  prioridades = entityManager.createQuery("Select u from br.gestor_tarefas_esig.model.Entity.Prioridade u")
	    		  .getResultList();
	      
	    }catch (Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
	    return prioridades;
	  }

	public Prioridade getById(int id) {
		Prioridade prioridade = null;
		EntityManager entityManager = getEntityManager();
	    try {
	    	entityManager.getTransaction().begin();
	    	prioridade = entityManager.find(Prioridade.class, id);
	    }catch (Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
	    return prioridade;
	}

}
