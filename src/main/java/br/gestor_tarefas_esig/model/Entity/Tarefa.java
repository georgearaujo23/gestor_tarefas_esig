package br.gestor_tarefas_esig.model.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name = "esig_tarefas.tarefa")

@FilterDef(name = "TITULO_FILTER", 
defaultCondition = "(TITULO = :TITULODESCRICAO or DESCRICAO = :TITULODESCRICAO)", 
parameters = { @ParamDef(name = "TITULODESCRICAO", type = "string") })

@FilterDef(name = "ID_TAREFA_FILTER", 
defaultCondition = "ID = :ID_TAREFA", 
parameters = { @ParamDef(name = "ID_TAREFA", type = "int") })
    
@FilterDef(name = "RESPONSAVEL_FILTER", 
defaultCondition = "ID_RESPONSAVEL = :ID_RESPONSAVEL", 
parameters = { @ParamDef(name = "ID_RESPONSAVEL", type = "int") })

@FilterDef(name = "ST_CONCLUIDA_FILTER", 
defaultCondition = "ST_CONCLUIDA = :ST_CONCLUIDA", 
parameters = { @ParamDef(name = "ST_CONCLUIDA", type = "boolean") })


@Filter(name = "TITULO_FILTER")
@Filter(name = "ID_TAREFA_FILTER")
@Filter(name = "RESPONSAVEL_FILTER")
@Filter(name = "ST_CONCLUIDA_FILTER")
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="id_prioridade")
	private Prioridade prioridade;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private String descricao;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date deadline;
	private boolean st_concluida;
	@ManyToOne
	@JoinColumn(name="id_responsavel")
	private Responsavel responsavel;
	
	public Responsavel getResponsavel() {
	    return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
	    this.responsavel = responsavel;
	}
	
	public Prioridade getPrioridade() {
	    return prioridade;
	}
	public void setPrioridade(Prioridade prioridade) {
	    this.prioridade = prioridade;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public boolean isSt_concluida() {
		return st_concluida;
	}
	public void setSt_concluida(boolean st_concluida) {
		this.st_concluida = st_concluida;
	}
	
}
