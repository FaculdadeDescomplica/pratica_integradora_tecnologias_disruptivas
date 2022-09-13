package br.com.descomplica.projeto.seguranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.descomplica.projeto.seguranca.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

}
