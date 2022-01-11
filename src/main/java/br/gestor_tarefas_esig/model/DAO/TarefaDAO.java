package br.gestor_tarefas_esig.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import br.gestor_tarefas_esig.model.Entity.Responsavel;
import br.gestor_tarefas_esig.model.Entity.Tarefa;

public class TarefaDAO {
	
	private EntityManager getEntityManager() {
		EntityManagerFactory factory = null;
	    EntityManager entityManager = null;
	    //Obtém o factory a partir da unidade de persistência.
	    factory = Persistence.createEntityManagerFactory("GestorTarefasDB");
	    if (entityManager == null) {
	    	entityManager = factory.createEntityManager();
        }
	    return entityManager;
	}

	public void salvar(Tarefa tarefa) throws Exception {
		EntityManager entityManager = getEntityManager();
		try {
			// Inicia uma transação com o banco de dados.
			entityManager.getTransaction().begin();
			
			// Verifica se a pessoa ainda não está salva no banco de dados.
			if(tarefa.getId() == 0) {
				//Salva os dados da Tarefa.
				System.out.println("incluindo a tarefa.");
				entityManager.persist(tarefa);
			} else {
				//Atualiza os dados da pessoa.
				System.out.println("atualizando a tarefa.");
				tarefa = entityManager.merge(tarefa);
			}
			// 	Finaliza a transação.
			entityManager.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
            entityManager.getTransaction().rollback();
		}
  }

	public void excluir(int id) {
		EntityManager entityManager = getEntityManager();
		try {
			// Inicia uma transação com o banco de dados.
		    entityManager.getTransaction().begin();
		    // Consulta a pessoa na base de dados através do seu ID.
		    Tarefa tarefa = entityManager.find(Tarefa.class, id);
		    // Remove a pessoa da base de dados.
		    entityManager.remove(tarefa);
		    // Finaliza a transação.
		    entityManager.getTransaction().commit();
		    }catch (Exception e) {
		    	e.printStackTrace();
	            entityManager.getTransaction().rollback();
			}
	}

	@SuppressWarnings("unchecked")
	public List<Tarefa> listar() {
		EntityManager entityManager = getEntityManager();
		List<Tarefa>  tarefas = null;
		try {
			// Inicia uma transação com o banco de dados.
		    entityManager.getTransaction().begin();
		    tarefas = entityManager.createQuery("SELECT e FROM br.gestor_tarefas_esig.model.Entity.Tarefa e inner join e.responsavel WHERE e.st_concluida = false")
		    		  .getResultList();  
	    }catch (Exception e) {
				e.printStackTrace();
		}
	    return tarefas;
	}

	@SuppressWarnings("unchecked")
	public List<Tarefa> listar(int idFiltro, String tituloDescricaoFiltro, Responsavel responsavelFiltro,
			Boolean st_concluidaFiltro) {
		EntityManager entityManager = getEntityManager();
		List<Tarefa>  tarefas = null;
	    try {
	    	entityManager.getTransaction().begin(); 
	    	Session session = entityManager.unwrap(Session.class);
	    	if (tituloDescricaoFiltro != null && !tituloDescricaoFiltro.isEmpty())
	        {
	    		session.enableFilter("TITULO_FILTER").
	    		setParameter("TITULODESCRICAO", tituloDescricaoFiltro);
	        }
	    	 
	    	if (idFiltro != 0)
	        {
	    		session.enableFilter("ID_TAREFA_FILTER").
	    		setParameter("ID_TAREFA", idFiltro);
	        }
	    	 
	    	if (responsavelFiltro != null)
	        {
	    		session.enableFilter("RESPONSAVEL_FILTER").
	    		setParameter("ID_RESPONSAVEL", responsavelFiltro.getId());
         	}
	    	 
	    	if (st_concluidaFiltro != null)
	    	{
	    		session.enableFilter("ST_CONCLUIDA_FILTER").
	    		setParameter("ST_CONCLUIDA", st_concluidaFiltro);
         	} 
	      
	    	tarefas = entityManager.createQuery("SELECT e FROM br.gestor_tarefas_esig.model.Entity.Tarefa e inner join e.responsavel")
	    		  .getResultList();
	      
	    }catch (Exception e) {
			e.printStackTrace();
	    }
	    return tarefas;
	}

}
