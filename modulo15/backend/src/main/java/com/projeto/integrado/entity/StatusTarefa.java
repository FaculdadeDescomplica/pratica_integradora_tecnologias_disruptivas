package com.projeto.integrado.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "status_tarefa")
public class StatusTarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_tarefa_id")
	private Integer statusTarefaId;

	@Column(name = "status_descricao")
	private String statusDescricao;

	@OneToMany(mappedBy = "statusTarefa")
	private List<Tarefa> tarefas;

	public Integer getStatusTarefaId() {
		return statusTarefaId;
	}

	public void setStatusTarefaId(Integer statusTarefaId) {
		this.statusTarefaId = statusTarefaId;
	}

	public String getStatusDescricao() {
		return statusDescricao;
	}

	public void setStatusDescricao(String statusDescricao) {
		this.statusDescricao = statusDescricao;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

}