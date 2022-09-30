package com.projeto.integrado.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.integrado.entity.StatusTarefa;
import com.projeto.integrado.repository.StatusTarefaRepository;

@Service
public class StatusTarefaService {
	@Autowired
	StatusTarefaRepository statusTarefaRepository;
	
	public List<StatusTarefa> getAll(){
		return statusTarefaRepository.findAll();
	}
	
	public StatusTarefa getById(Integer id) {
		return statusTarefaRepository.findById(id).orElse(null) ;
	}
	
	public StatusTarefa saveStatusTarefa(StatusTarefa statusTarefa) {
		return statusTarefaRepository.save(statusTarefa);
	}
	
	public StatusTarefa updateStatusTarefa(Integer id, StatusTarefa statusTarefa) {
		StatusTarefa statusTarefaAtualizado = statusTarefaRepository.findById(id).orElse(null);
		if(statusTarefaAtualizado != null) {
			statusTarefaAtualizado.setStatusDescricao(statusTarefa.getStatusDescricao());
			return statusTarefaRepository.save(statusTarefaAtualizado);
		}else {
			return null;
		}
	}

	public Boolean deleteStatusTarefa(Integer id) {
		StatusTarefa statusTarefa = statusTarefaRepository.findById(id).orElse(null);
		if(statusTarefa != null) {
			statusTarefaRepository.delete(statusTarefa);
			return true;
		}else {
			return false;
		}
	}
}