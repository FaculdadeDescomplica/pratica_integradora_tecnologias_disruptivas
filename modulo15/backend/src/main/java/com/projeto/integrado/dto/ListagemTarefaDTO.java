package com.projeto.integrado.dto;

import java.time.Instant;

public class ListagemTarefaDTO {
	private Integer tarefaId;
	private String tarefaTitulo;
	private String tarefaDescricao;
	private Instant tarefaInicio;
	private Instant tarefaFim;
	private StatusTarefaDTO statusTarefaDTO;
	private ProjetoDTO projetoDTO;
	private RecursoDTO recursoDTO;

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

	public StatusTarefaDTO getStatusTarefaDTO() {
		return statusTarefaDTO;
	}

	public void setStatusTarefaDTO(StatusTarefaDTO statusTarefaDTO) {
		this.statusTarefaDTO = statusTarefaDTO;
	}

	public ProjetoDTO getProjetoDTO() {
		return projetoDTO;
	}

	public void setProjetoDTO(ProjetoDTO projetoDTO) {
		this.projetoDTO = projetoDTO;
	}

	public RecursoDTO getRecursoDTO() {
		return recursoDTO;
	}

	public void setRecursoDTO(RecursoDTO recursoDTO) {
		this.recursoDTO = recursoDTO;
	}
}