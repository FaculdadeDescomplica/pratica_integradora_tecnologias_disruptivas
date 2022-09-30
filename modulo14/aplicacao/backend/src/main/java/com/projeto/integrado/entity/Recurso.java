package com.projeto.integrado.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recurso")
public class Recurso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recurso_id")
	private Integer recursoId;

	@Column(name = "recurso_nome")
	private String recursoNome;

	@Column(name = "recurso_funcao")
	private String recursoFuncao;

	@ManyToMany(mappedBy = "gerentes")
	private Set<Projeto> projetos;

	@OneToOne(mappedBy = "recurso")
	Tarefa tarefa;

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

	public Set<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(Set<Projeto> projetos) {
		this.projetos = projetos;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

}
