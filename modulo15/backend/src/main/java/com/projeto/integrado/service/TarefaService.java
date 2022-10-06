package com.projeto.integrado.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.integrado.dto.ListagemTarefaDTO;
import com.projeto.integrado.dto.ProjetoDTO;
import com.projeto.integrado.dto.RecursoDTO;
import com.projeto.integrado.dto.StatusTarefaDTO;
import com.projeto.integrado.dto.TarefaDTO;
import com.projeto.integrado.entity.Projeto;
import com.projeto.integrado.entity.Recurso;
import com.projeto.integrado.entity.StatusTarefa;
import com.projeto.integrado.entity.Tarefa;
import com.projeto.integrado.repository.ProjetoRepository;
import com.projeto.integrado.repository.RecursoRepository;
import com.projeto.integrado.repository.StatusTarefaRepository;
import com.projeto.integrado.repository.TarefaRepository;

@Service
public class TarefaService {
	@Autowired
	TarefaRepository tarefaRepository;
	
	@Autowired
	ProjetoRepository projetoRepository;

	@Autowired
	RecursoRepository recursoRepository;
	
	@Autowired
	StatusTarefaRepository statusTarefaRepository;
	
	public List<ListagemTarefaDTO> getAll(){
		List<Tarefa> tarefasList = tarefaRepository.findAll();
		List<ListagemTarefaDTO> listagemTarefasDtos = new ArrayList<>();
		for(Tarefa tarefa : tarefasList) {
			ListagemTarefaDTO listagemTarefaDTO = new ListagemTarefaDTO();

			ProjetoDTO projetoDTO = new ProjetoDTO();
			if(null != tarefa.getProjeto()) {
				projetoDTO.setProjetoDescricao(tarefa.getProjeto().getProjetoDescricao());
				projetoDTO.setProjetoFim(tarefa.getProjeto().getProjetoFim());
				projetoDTO.setProjetoId(tarefa.getProjeto().getProjetoId());
				projetoDTO.setProjetoInicio(tarefa.getProjeto().getProjetoInicio());
				projetoDTO.setProjetoNome(tarefa.getProjeto().getProjetoNome());
				projetoDTO.setProjetoStatus(tarefa.getProjeto().getProjetoStatus());
				listagemTarefaDTO.setProjetoDTO(projetoDTO);
			}else{
				listagemTarefaDTO.setProjetoDTO(null);
			}
			
			RecursoDTO recursoDTO = new RecursoDTO();
			if(null != tarefa.getRecurso()) {
				recursoDTO.setRecursoFuncao(tarefa.getRecurso().getRecursoFuncao());
				recursoDTO.setRecursoId(tarefa.getRecurso().getRecursoId());
				recursoDTO.setRecursoNome(tarefa.getRecurso().getRecursoNome());
				listagemTarefaDTO.setRecursoDTO(recursoDTO);
			}else {
				listagemTarefaDTO.setRecursoDTO(null);
			}
			
			StatusTarefaDTO statusTarefaDTO = new StatusTarefaDTO();
			if(null != tarefa.getStatusTarefa()) {
				statusTarefaDTO.setStatusDescricao(tarefa.getStatusTarefa().getStatusDescricao());
				statusTarefaDTO.setStatusTarefaId(tarefa.getStatusTarefa().getStatusTarefaId());
				listagemTarefaDTO.setStatusTarefaDTO(statusTarefaDTO);
			}else {
				listagemTarefaDTO.setStatusTarefaDTO(null);
			}
			
			listagemTarefaDTO.setTarefaDescricao(tarefa.getTarefaDescricao());
			listagemTarefaDTO.setTarefaFim(tarefa.getTarefaFim());
			listagemTarefaDTO.setTarefaId(tarefa.getTarefaId());
			listagemTarefaDTO.setTarefaInicio(tarefa.getTarefaInicio());
			listagemTarefaDTO.setTarefaTitulo(tarefa.getTarefaTitulo());
			
			listagemTarefasDtos.add(listagemTarefaDTO);
		}
		return listagemTarefasDtos;
	}
	
	public Tarefa getById(Integer id) {
		return tarefaRepository.findById(id).orElse(null) ;
	}
	
	public Tarefa saveTarefaEntidade(Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	public TarefaDTO saveTarefa(TarefaDTO tarefaDTO) {
		//Cria uma nova instancia da entidade Tarefa que recebe o resultado da conversao do DTO
		Tarefa novaTarefa = converteDTOParaEntidade(tarefaDTO);
		//Salva a nova Tarefa e a retorna
		return converteEntidadeParaDTO(tarefaRepository.save(novaTarefa));
	}
	
	private Tarefa converteDTOParaEntidade(TarefaDTO tarefaDto) {
		Tarefa tarefa = new Tarefa();
		
		//Recuperando uma instancia de Projeto, via repository, a partir de seu id
		Projeto projeto = projetoRepository.findById(tarefaDto.getProjetoId()).orElse(null);
		
		//Setando em tarefa a instancia recuperada de projeto 
		//  (ou NULL, caso nao existe projeto com o id fornecido)
		tarefa.setProjeto(projeto);
		
		if(null != tarefaDto.getRecursoId()) {
			Recurso recurso = recursoRepository.findById(tarefaDto.getRecursoId()).orElse(null);
			
			//Caso exista recurso com o id recebido, seta seu valor. 
			//  Caso nao, seta como null
			if(null != recurso)
				tarefa.setRecurso(recurso);
			else 
				tarefa.setRecurso(null);
		}else if(null != tarefaDto.getRecursoDTO()) {
			//Para evitar um nullpointer exception, verifica se o RecursoDTO foi recebido
			
			//Recupera, via repository, a instancia de recurso, passando pelo RecursoDTO e chegando
			//  ao atributo RecursoId
			Recurso recurso = recursoRepository.findById(tarefaDto.getRecursoDTO().getRecursoId()).orElse(null);
			//Caso exista recurso com o id recebido, seta seu valor. 
			//  Caso nao, seta como null
			if(null != recurso)
				tarefa.setRecurso(recurso);
			else 
				tarefa.setRecurso(null);
		}else {
			tarefa.setRecurso(null);
		}
		
		//Caso exista StatusTarefa com o id recebido, seta seu valor. 
		//  Caso nao, seta como null
		StatusTarefa statusTarefa = statusTarefaRepository.findById(tarefaDto.getStatusTarefaId()).orElse(null);
		if(null != statusTarefa)
			tarefa.setStatusTarefa(statusTarefa);
		else 
			tarefa.setStatusTarefa(null);
		
		tarefa.setTarefaDescricao(tarefaDto.getTarefaDescricao());
		tarefa.setTarefaFim(Instant.ofEpochMilli(tarefaDto.getTarefaFim()));
		tarefa.setTarefaInicio(Instant.ofEpochMilli(tarefaDto.getTarefaInicio()));
		//tarefa.setTarefaInicio(tarefaDto.getTarefaInicio());
		//tarefa.setTarefaInicio(Instant.parse(tarefaDto.getTarefaInicio().toString().concat("Z")));
		tarefa.setTarefaTitulo(tarefaDto.getTarefaTitulo());
		
		return tarefa;
	}
	
	private TarefaDTO converteEntidadeParaDTO(Tarefa tarefa) {
		TarefaDTO tarefaDto = new TarefaDTO();
		
		if(null != tarefa.getTarefaId())
			tarefaDto.setTarefaId(tarefa.getTarefaId());
		
		//Para evitar uma excecao nullpointer, precisamos verificar se foi
		//  recebida uma instancia da entidade Projeto a partir da entidade Tarefa.
		//  Caso positivo, pegamos o seu id a partir de tarefa.getProjeto.
		//  Caso negativo, setamos o projetoId como null
		if(null != tarefa.getProjeto())
			tarefaDto.setProjetoId(tarefa.getProjeto().getProjetoId());
		else
			tarefaDto.setProjetoId(null);
		
		//Para setar o recursoDTO, precisariamos recuperar seus dados 
		//  a partir do tarefa.getRecursoId, recuperando primeiro a instancia da
		//  entidade e, a seguir, convertendo tambem para DTO.
		//tarefaDto.setRecursoDTO(RecursoDTO);
		tarefaDto.setRecursoId(tarefa.getRecurso().getRecursoId());
		tarefaDto.setStatusTarefaId(tarefa.getStatusTarefa().getStatusTarefaId());
		
		tarefaDto.setTarefaDescricao(tarefa.getTarefaDescricao());
		//tarefaDto.setTarefaFim(tarefa.getTarefaFim());
		//tarefaDto.setTarefaInicio(tarefa.getTarefaInicio());
		tarefaDto.setTarefaTitulo(tarefa.getTarefaTitulo());
		
		return tarefaDto;
	}	
	
	public TarefaDTO updateTarefa(Integer id, TarefaDTO tarefaDto) {
		Tarefa tarefaAtualizado = tarefaRepository.findById(id).orElse(null);
		if(tarefaAtualizado != null) {
			
			//Recuperando uma instancia de Projeto, via repository, a partir de seu id
			Projeto projeto = projetoRepository.findById(tarefaDto.getProjetoId()).orElse(null);
			
			//Setando em tarefa a instancia recuperada de projeto 
			//  (ou NULL, caso nao existe projeto com o id fornecido)
			tarefaAtualizado.setProjeto(projeto);

			if(null != tarefaDto.getRecursoId()) {
				Recurso recurso = recursoRepository.findById(tarefaDto.getRecursoId()).orElse(null);
				
				//Caso exista recurso com o id recebido, seta seu valor. 
				//  Caso nao, seta como null
				if(null != recurso)
					tarefaAtualizado.setRecurso(recurso);
				else 
					tarefaAtualizado.setRecurso(null);
			}else if(null != tarefaDto.getRecursoDTO()) {
				//Para evitar um nullpointer exception, verifica se o RecursoDTO foi recebido
				
				//Recupera, via repository, a instancia de recurso, passando pelo RecursoDTO e chegando
				//  ao atributo RecursoId
				Recurso recurso = recursoRepository.findById(tarefaDto.getRecursoDTO().getRecursoId()).orElse(null);
				//Caso exista recurso com o id recebido, seta seu valor. 
				//  Caso nao, seta como null
				if(null != recurso)
					tarefaAtualizado.setRecurso(recurso);
				else 
					tarefaAtualizado.setRecurso(null);
			}else {
				tarefaAtualizado.setRecurso(null);
			}			

			//Caso exista StatusTarefa com o id recebido, seta seu valor. 
			//  Caso nao, seta como null
			StatusTarefa statusTarefa = statusTarefaRepository.findById(tarefaDto.getStatusTarefaId()).orElse(null);
			if(null != statusTarefa)
				tarefaAtualizado.setStatusTarefa(statusTarefa);
			else 
				tarefaAtualizado.setStatusTarefa(null);


			tarefaAtualizado.setTarefaDescricao(tarefaDto.getTarefaDescricao());
			tarefaAtualizado.setTarefaFim(Instant.ofEpochMilli(tarefaDto.getTarefaFim()));
			tarefaAtualizado.setTarefaInicio(Instant.ofEpochMilli(tarefaDto.getTarefaInicio()));
			tarefaAtualizado.setTarefaTitulo(tarefaDto.getTarefaTitulo());
			return converteEntidadeParaDTO(tarefaRepository.save(tarefaAtualizado));
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