package com.projeto.integrado.dto;

import java.time.Instant;
import java.util.Set;

public class ProjetoDTO {
	private Integer projetoId;
	private String projetoNome;
	private String projetoDescricao;
	private Instant projetoInicio;
	private Instant projetoFim;
	private Boolean projetoStatus;
	private Set<TarefaDTO> tarefasDTO;
	private Set<RecursoDTO> gerentesDTO;

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

	public Set<TarefaDTO> getTarefasDTO() {
		return tarefasDTO;
	}

	public void setTarefasDTO(Set<TarefaDTO> tarefasDTO) {
		this.tarefasDTO = tarefasDTO;
	}

	public Set<RecursoDTO> getGerentesDTO() {
		return gerentesDTO;
	}

	public void setGerentesDTO(Set<RecursoDTO> gerentesDTO) {
		this.gerentesDTO = gerentesDTO;
	}
}
