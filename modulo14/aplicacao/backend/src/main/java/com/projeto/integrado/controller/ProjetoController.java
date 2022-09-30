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

import com.projeto.integrado.entity.Projeto;
import com.projeto.integrado.service.ProjetoService;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {
	@Autowired
	ProjetoService projetoService;
	
	@GetMapping
	public ResponseEntity<List<Projeto>> getAll(){
		List<Projeto> projetos = projetoService.getAll();
		if(!projetos.isEmpty())
			return new ResponseEntity<>(projetos, HttpStatus.OK);
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Projeto> getById(@PathVariable Integer id) {
		Projeto projeto = projetoService.getById(id);
		if(projeto != null)
			return new ResponseEntity<>(projeto, HttpStatus.OK); 
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);		
	}
	
	@PostMapping
	public ResponseEntity<Projeto> saveProjeto(@RequestBody Projeto projeto) {
		return new ResponseEntity<>(projetoService.saveProjeto(projeto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Projeto> updateProjeto(@PathVariable Integer id, @RequestBody Projeto projeto) {
		Projeto projetoAtualizada = projetoService.updateProjeto(id, projeto);
		if(projetoAtualizada != null)
			return new ResponseEntity<>(projetoAtualizada, HttpStatus.OK); 
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteProjeto(@PathVariable Integer id) {
		if(projetoService.deleteProjeto(id))
			return new ResponseEntity<>(true, HttpStatus.OK);
		else 
			return new ResponseEntity<>(false, HttpStatus.OK);
	}
}