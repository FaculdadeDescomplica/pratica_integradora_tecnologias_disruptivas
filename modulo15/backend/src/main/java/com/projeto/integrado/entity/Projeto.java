package com.projeto.integrado.entity;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projeto")
public class Projeto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projeto_id")
	private Integer projetoId;

	@Column(name = "projeto_nome")
	private String projetoNome;

	@Column(name = "projeto_descricao")
	private String projetoDescricao;

	@Column(name = "projeto_inicio")
	private Instant projetoInicio;

	@Column(name = "projeto_fim")
	private Instant projetoFim;

	@Column(name = "projeto_status")
	private Boolean projetoStatus;

	@OneToMany(mappedBy = "projeto")
	private Set<Tarefa> tarefas;

	@ManyToMany
	@JoinTable(name = "gerente_projeto", joinColumns = @JoinColumn(name = "projeto_id"), inverseJoinColumns = @JoinColumn(name = "recurso_id"))
	private Set<Recurso> gerentes;

	public Integer getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Integer projetoId) {
		this.projetoId = projetoId;
	}

	public String getProjetoNome() {
		return projetoNome;
	}

	public void setProjetoNome(String projetoNome) {
		this.projetoNome = projetoNome;
	}

	public String getProjetoDescricao() {
		return projetoDescricao;
	}

	public void setProjetoDescricao(String projetoDescricao) {
		this.projetoDescricao = projetoDescricao;
	}

	public Instant getProjetoInicio() {
		return projetoInicio;
	}

	public void setProjetoInicio(Instant projetoInicio) {
		this.projetoInicio = projetoInicio;
	}

	public Instant getProjetoFim() {
		return projetoFim;
	}

	public void setProjetoFim(Instant projetoFim) {
		this.projetoFim = projetoFim;
	}

	public Boolean getProjetoStatus() {
		return projetoStatus;
	}

	public void setProjetoStatus(Boolean projetoStatus) {
		this.projetoStatus = projetoStatus;
	}

	public Set<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(Set<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Set<Recurso> getGerentes() {
		return gerentes;
	}

	public void setGerentes(Set<Recurso> gerentes) {
		this.gerentes = gerentes;
	}

}