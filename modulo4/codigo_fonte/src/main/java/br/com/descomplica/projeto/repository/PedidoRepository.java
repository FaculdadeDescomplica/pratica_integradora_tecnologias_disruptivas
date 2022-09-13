package br.com.descomplica.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.descomplica.projeto.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

}
