package br.com.descomplica.projeto.documentacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.descomplica.projeto.documentacao.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

}
