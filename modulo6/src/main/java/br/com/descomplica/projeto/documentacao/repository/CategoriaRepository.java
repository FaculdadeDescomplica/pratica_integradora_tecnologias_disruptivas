package br.com.descomplica.projeto.documentacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.descomplica.projeto.documentacao.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

}
