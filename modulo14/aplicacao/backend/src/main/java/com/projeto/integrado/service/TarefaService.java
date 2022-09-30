package com.projeto.integrado.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.integrado.entity.Tarefa;
import com.projeto.integrado.repository.TarefaRepository;

@Service
public class TarefaService {
	@Autowired
	TarefaRepository tarefaRepository;
	
	public List<Tarefa> getAll(){
		return tarefaRepository.findAll();
	}
	
	public Tarefa getById(Integer id) {
		return tarefaRepository.findById(id).orElse(null) ;
	}
	
	public Tarefa saveTarefa(Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	public Tarefa updateTarefa(Integer id, Tarefa tarefa) {
		Tarefa tarefaAtualizado = tarefaRepository.findById(id).orElse(null);
		if(tarefaAtualizado != null) {
			tarefaAtualizado.setProjeto(tarefa.getProjeto());
			tarefaAtualizado.setRecurso(tarefa.getRecurso());
			tarefaAtualizado.setStatusTarefa(tarefa.getStatusTarefa());
			tarefaAtualizado.setTarefaDescricao(tarefa.getTarefaDescricao());
			tarefaAtualizado.setTarefaFim(tarefa.getTarefaFim());
			tarefaAtualizado.setTarefaInicio(tarefa.getTarefaInicio());
			tarefaAtualizado.setTarefaTitulo(tarefa.getTarefaTitulo());
			return tarefaRepository.save(tarefaAtualizado);
		}else {
			return null;
		}
	}

	public Boolean deleteTarefa(Integer id) {
		Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
		if(tarefa != null) {
			tarefaRepository.delete(tarefa);
			return true;
		}else {
			return false;
		}
	}
}