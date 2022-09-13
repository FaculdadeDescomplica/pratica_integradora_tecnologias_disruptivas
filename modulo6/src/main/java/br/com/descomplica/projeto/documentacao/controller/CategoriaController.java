package br.com.descomplica.projeto.documentacao.controller;

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

import br.com.descomplica.projeto.documentacao.entity.Categoria;
import br.com.descomplica.projeto.documentacao.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping
	@Operation(summary="Listar todas as categorias", description  = "Listagem de Categorias")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso."),
			  @ApiResponse(responseCode = "400", description = "Requisição Inválida"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso."), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado.")})
	public ResponseEntity<List<Categoria>> getAll(){
		List<Categoria> categorias = categoriaService.getAll();
		if(!categorias.isEmpty())
			return new ResponseEntity<>(categorias, HttpStatus.OK);
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Integer id) {
		Categoria categoria = categoriaService.getById(id);
		if(categoria != null)
			return new ResponseEntity<>(categoria, HttpStatus.OK); 
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);		
	}
	
	@PostMapping
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
		return new ResponseEntity<>(categoriaService.saveCategoria(categoria), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
		Categoria categoriaAtualizada = categoriaService.updateCategoria(id, categoria);
		if(categoriaAtualizada != null)
			return new ResponseEntity<>(categoriaAtualizada, HttpStatus.OK); 
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteCategoria(@PathVariable Integer id) {
		if(categoriaService.deleteCategoria(id))
			return new ResponseEntity<>(true, HttpStatus.OK);
		else 
			return new ResponseEntity<>(false, HttpStatus.OK);
	}
}
