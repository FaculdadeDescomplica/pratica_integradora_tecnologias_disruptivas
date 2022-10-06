package com.projeto.integrado.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tarefa")
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tarefa_id")
	private Integer tarefaId;

	@Column(name = "tarefa_titulo")
	private String tarefaTitulo;

	@Column(name = "tarefa_descricao")
	private String tarefaDescricao;

	@Column(name = "tarefa_inicio")
	private Instant tarefaInicio;

	@Column(name = "tarefa_fim")
	private Instant tarefaFim;

	@OneToOne
	@JoinColumn(name = "status_tarefa_id", referencedColumnName = "status_tarefa_id")
	private StatusTarefa statusTarefa;

	@ManyToOne
	@JoinColumn(name = "projeto_id", referencedColumnName = "projeto_id")
	Projeto projeto;

	@OneToOne
	@JoinColumn(name = "recurso_id", referencedColumnName = "recurso_id")
	Recurso recurso;

	public Integer getTarefaId() {
		return tarefaId;
	}

	public void setTarefaId(Integer tarefaId) {
		this.tarefaId = tarefaId;
	}

	public String getTarefaTitulo() {
		return tarefaTitulo;
	}

	public void setTarefaTitulo(String tarefaTitulo) {
		this.tarefaTitulo = tarefaTitulo;
	}

	public String getTarefaDescricao() {
		return tarefaDescricao;
	}

	public void setTarefaDescricao(String tarefaDescricao) {
		this.tarefaDescricao = tarefaDescricao;
	}

	public Instant getTarefaInicio() {
		return tarefaInicio;
	}

	public void setTarefaInicio(Instant tarefaInicio) {
		this.tarefaInicio = tarefaInicio;
	}

	public Instant getTarefaFim() {
		return tarefaFim;
	}

	public void setTarefaFim(Instant tarefaFim) {
		this.tarefaFim = tarefaFim;
	}

	public StatusTarefa getStatusTarefa() {
		return statusTarefa;
	}

	public void setStatusTarefa(StatusTarefa statusTarefa) {
		this.statusTarefa = statusTarefa;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

}
