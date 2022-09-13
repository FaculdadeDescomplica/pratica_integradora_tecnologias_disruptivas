package br.com.descomplica.projeto.seguranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.descomplica.projeto.seguranca.entity.Pedido;
import br.com.descomplica.projeto.seguranca.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;
	
	public List<Pedido> getAll(){
		return pedidoRepository.findAll();
	}
	
	public Pedido getById(Integer id) {
		return pedidoRepository.findById(id).orElse(null) ;
	}
	
	public Pedido savePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Pedido updatePedido(Integer id, Pedido pedido) {
		Pedido pedidoAtualizado = pedidoRepository.findById(id).orElse(null);
		if(pedidoAtualizado != null) {
			pedidoAtualizado.setPedidoData(pedido.getPedidoData());
			return pedidoRepository.save(pedidoAtualizado);
		}else {
			return null;
		}
	}

	public Boolean deletePedido(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElse(null);
		if(pedido != null) {
			pedidoRepository.delete(pedido);
			return true;
		}else {
			return false;
		}
	}
}
