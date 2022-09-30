package com.projeto.integrado.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.integrado.entity.Recurso;
import com.projeto.integrado.repository.RecursoRepository;

@Service
public class RecursoService {
	@Autowired
	RecursoRepository recursoRepository;
	
	public List<Recurso> getAll(){
		return recursoRepository.findAll();
	}
	
	public Recurso getById(Integer id) {
		return recursoRepository.findById(id).orElse(null) ;
	}
	
	public Recurso saveRecurso(Recurso recurso) {
		return recursoRepository.save(recurso);
	}
	
	public Recurso updateRecurso(Integer id, Recurso recurso) {
		Recurso recursoAtualizado = recursoRepository.findById(id).orElse(null);
		if(recursoAtualizado != null) {
			recursoAtualizado.setRecursoFuncao(recurso.getRecursoFuncao());
			recursoAtualizado.setRecursoNome(recurso.getRecursoNome());
			return recursoRepository.save(recursoAtualizado);
		}else {
			return null;
		}
	}

	public Boolean deleteRecurso(Integer id) {
		Recurso recurso = recursoRepository.findById(id).orElse(null);
		if(recurso != null) {
			recursoRepository.delete(recurso);
			return true;
		}else {
			return false;
		}
	}
}