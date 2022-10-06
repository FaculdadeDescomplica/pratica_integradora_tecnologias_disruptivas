package com.projeto.integrado.dto;

import java.util.List;

public class StatusTarefaDTO {
	private Integer statusTarefaId;
	private String statusDescricao;
	private List<TarefaDTO> tarefasDTO;

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

	public List<TarefaDTO> getTarefasDTO() {
		return tarefasDTO;
	}

	public void setTarefasDTO(List<TarefaDTO> tarefasDTO) {
		this.tarefasDTO = tarefasDTO;
	}
}
