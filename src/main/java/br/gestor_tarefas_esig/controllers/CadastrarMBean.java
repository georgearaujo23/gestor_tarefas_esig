package br.gestor_tarefas_esig.controllers;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.gestor_tarefas_esig.model.DAO.TarefaDAO;
import br.gestor_tarefas_esig.model.Entity.Tarefa;
import br.gestor_tarefas_esig.model.Entity.Responsavel;
import br.gestor_tarefas_esig.model.DAO.ResponsavelDAO;
import br.gestor_tarefas_esig.model.Entity.Prioridade;
import br.gestor_tarefas_esig.model.DAO.PrioridadeDAO;

@ManagedBean
@SessionScoped
public class CadastrarMBean {
	Tarefa tarefa;
	TarefaDAO tarefaDAO;
	List<Responsavel> responsaveis;
	List<Prioridade> prioridades;
	public CadastrarMBean() throws Exception {
		if(tarefa == null) tarefa = new Tarefa();;
		tarefaDAO = new TarefaDAO();
		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		PrioridadeDAO prioridadeDAO = new PrioridadeDAO();
		responsaveis = responsavelDAO.listar();
		prioridades = prioridadeDAO.listar();
	}

	public Tarefa getTarefa() { 
		return tarefa; 
	}
	
	public void setTarefa(Tarefa tarefa) { 
		this.tarefa = tarefa;
	}
	
	public List<Responsavel> getResponsaveis() { 
		return responsaveis; 
	} 
	
	public List<Prioridade> getPrioridades() { 
		return prioridades; 
	} 
	  
	public void setPessoa(Tarefa tarefa) { 
		this.tarefa = tarefa;
		
	} 
	  
	public String cadastrar() {
		 try {
			this.tarefaDAO.salvar(tarefa);
			FacesMessage msg = new FacesMessage("Tarefa cadastrado com sucesso!");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext.getCurrentInstance().addMessage("", msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/index.jsf"; 
	} 
	
	public String cancelar() {
		 
		return "/index.jsf"; 
	}
	
	public String editar(Tarefa tarefa) {
		this.tarefa = tarefa;
		
		return "cadastroTarefa";
	}
	
	public String entrarCadastro(){
		this.tarefa = new Tarefa();
		return "/cadastroTarefa.jsf";
	}

}
