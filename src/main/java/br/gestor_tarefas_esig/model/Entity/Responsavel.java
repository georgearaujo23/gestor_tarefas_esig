package br.gestor_tarefas_esig.model.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "esig_tarefas.responsavel")
public class Responsavel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String nome;
	@OneToMany(mappedBy="responsavel",cascade=CascadeType.ALL)
	private List<Tarefa> tarefas = new ArrayList<Tarefa>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	
	public List<Tarefa> getTarefas() {
	    return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
	    this.tarefas = tarefas;
	}
	

	@Override
	public boolean equals(Object obj) {
		if(((Responsavel)obj).getId() == this.id) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.id;
	}
}
