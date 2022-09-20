package br.com.descomplica.projeto.seguranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.descomplica.projeto.seguranca.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

}
