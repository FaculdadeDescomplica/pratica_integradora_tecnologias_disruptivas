package br.com.descomplica.projeto.seguranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.descomplica.projeto.seguranca.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

}
