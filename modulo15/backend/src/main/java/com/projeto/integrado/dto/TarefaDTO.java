package com.projeto.integrado.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TarefaDTO {
	private Integer tarefaId;
	private String tarefaTitulo;
	private String tarefaDescricao;
	private Long tarefaInicio;
	private Long tarefaFim;
	private Integer statusTarefaId;
	private Integer projetoId;
	private Integer recursoId;
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

	public Long getTarefaInicio() {
		return tarefaInicio;
	}

	public void setTarefaInicio(Long tarefaInicio) {
		this.tarefaInicio = tarefaInicio;
	}

	public Long getTarefaFim() {
		return tarefaFim;
	}

	public void setTarefaFim(Long tarefaFim) {
		this.tarefaFim = tarefaFim;
	}

	public Integer getStatusTarefaId() {
		return statusTarefaId;
	}

	public void setStatusTarefaId(Integer statusTarefaId) {
		this.statusTarefaId = statusTarefaId;
	}

	public Integer getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Integer projetoId) {
		this.projetoId = projetoId;
	}

	public Integer getRecursoId() {
		return recursoId;
	}

	public void setRecursoId(Integer recursoId) {
		this.recursoId = recursoId;
	}

	public RecursoDTO getRecursoDTO() {
		return recursoDTO;
	}

	public void setRecursoDTO(RecursoDTO recursoDTO) {
		this.recursoDTO = recursoDTO;
	}
}
