package com.projeto.integrado.dto;

import java.util.Set;

public class RecursoDTO {
	private Integer recursoId;
	private String recursoNome;
	private String recursoFuncao;
	private Set<ProjetoDTO> projetosDTO;
	private TarefaDTO tarefaDTO;

	public Integer getRecursoId() {
		return recursoId;
	}

	public void setRecursoId(Integer recursoId) {
		this.recursoId = recursoId;
	}

	public String getRecursoNome() {
		return recursoNome;
	}

	public void setRecursoNome(String recursoNome) {
		this.recursoNome = recursoNome;
	}

	public String getRecursoFuncao() {
		return recursoFuncao;
	}

	public void setRecursoFuncao(String recursoFuncao) {
		this.recursoFuncao = recursoFuncao;
	}

	public Set<ProjetoDTO> getProjetosDTO() {
		return projetosDTO;
	}

	public void setProjetosDTO(Set<ProjetoDTO> projetosDTO) {
		this.projetosDTO = projetosDTO;
	}

	public TarefaDTO getTarefaDTO() {
		return tarefaDTO;
	}

	public void setTarefaDTO(TarefaDTO tarefaDTO) {
		this.tarefaDTO = tarefaDTO;
	}
}