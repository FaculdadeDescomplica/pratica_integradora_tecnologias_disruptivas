package com.projeto.integrado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.integrado.entity.Tarefa;
import com.projeto.integrado.service.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
	@Autowired
	TarefaService tarefaService;
	
	@GetMapping
	public ResponseEntity<List<Tarefa>> getAll(){
		List<Tarefa> tarefas = tarefaService.getAll();
		if(!tarefas.isEmpty())
			return new ResponseEntity<>(tarefas, HttpStatus.OK);
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> getById(@PathVariable Integer id) {
		Tarefa tarefa = tarefaService.getById(id);
		if(tarefa != null)
			return new ResponseEntity<>(tarefa, HttpStatus.OK); 
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);		
	}
	
	@PostMapping
	public ResponseEntity<Tarefa> saveTarefa(@RequestBody Tarefa tarefa) {
		return new ResponseEntity<>(tarefaService.saveTarefa(tarefa), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> updateTarefa(@PathVariable Integer id, @RequestBody Tarefa tarefa) {
		Tarefa tarefaAtualizada = tarefaService.updateTarefa(id, tarefa);
		if(tarefaAtualizada != null)
			return new ResponseEntity<>(tarefaAtualizada, HttpStatus.OK); 
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteTarefa(@PathVariable Integer id) {
		if(tarefaService.deleteTarefa(id))
			return new ResponseEntity<>(true, HttpStatus.OK);
		else 
			return new ResponseEntity<>(false, HttpStatus.OK);
	}
}