package br.com.descomplica.projeto.documentacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.descomplica.projeto.documentacao.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

}
