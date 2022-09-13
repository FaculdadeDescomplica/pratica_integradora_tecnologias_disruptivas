package br.com.descomplica.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.descomplica.projeto.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

}
