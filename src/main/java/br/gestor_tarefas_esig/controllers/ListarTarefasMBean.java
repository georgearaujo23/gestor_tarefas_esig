package br.gestor_tarefas_esig.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.gestor_tarefas_esig.model.DAO.TarefaDAO;
import br.gestor_tarefas_esig.model.Entity.Responsavel;
import br.gestor_tarefas_esig.model.Entity.Tarefa;

@ManagedBean
public class ListarTarefasMBean {
	Tarefa tarefa;
	TarefaDAO tarefaDAO;
	List<Tarefa> tarefas;
	int idFiltro;
	String tituloDescricaoFiltro;
	Responsavel responsavelFiltro;
	Boolean st_concluidaFiltro;
	
	public ListarTarefasMBean() {
		this.tarefaDAO = new TarefaDAO();
		listar();
	}

	public void setIdFiltro(int idFiltro) {
		this.idFiltro = idFiltro;
	}

	public void setTituloDescricaoFiltro(String tituloDescricaoFiltro) {
		this.tituloDescricaoFiltro = tituloDescricaoFiltro;
	}

	public void setResponsavelFiltro(Responsavel responsavelFiltro) {
		this.responsavelFiltro = responsavelFiltro;
	}

	public Boolean getSt_concluidaFiltro() {
		return st_concluidaFiltro;
	}

	public void setSt_concluidaFiltro(Boolean st_concluidaFiltro) {
		this.st_concluidaFiltro = st_concluidaFiltro;
	}

	public int getIdFiltro() {
		return idFiltro;
	}

	public String getTituloDescricaoFiltro() {
		return tituloDescricaoFiltro;
	}

	public Responsavel getResponsavelFiltro() {
		return responsavelFiltro;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}
	
	public void setTarefa( Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	public void filtrar() {
		tarefaDAO = new TarefaDAO();
		this.tarefas =  this.tarefaDAO.listar(idFiltro, tituloDescricaoFiltro, responsavelFiltro, st_concluidaFiltro);
	}
	
	public void listar() {
		
		this.tarefas = this.tarefaDAO.listar();
	}
	
	public void concluir(Tarefa tarefa) {
		tarefa.setSt_concluida(true);
		tarefaDAO = new TarefaDAO();
		try {
			tarefaDAO.salvar(tarefa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			listar();
		} 
	}
	
	public void excluir(Tarefa tarefa) {
		try {
			this.tarefaDAO.excluir(tarefa.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			listar();
		}
		 
	}
	
	
	public String entrarCadastro() { 
		
		return "/cadastroTarefa.jsf"; 
	}

}
