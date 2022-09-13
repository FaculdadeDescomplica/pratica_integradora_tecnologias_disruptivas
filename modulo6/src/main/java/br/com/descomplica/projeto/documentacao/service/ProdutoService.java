package br.com.descomplica.projeto.documentacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.descomplica.projeto.documentacao.entity.Produto;
import br.com.descomplica.projeto.documentacao.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<Produto> getAll(){
		return produtoRepository.findAll();
	}
	
	public Produto getById(Integer id) {
		return produtoRepository.findById(id).orElse(null) ;
	}
	
	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto updateProduto(Integer id, Produto produto) {
		Produto produtoAtualizado = produtoRepository.findById(id).orElse(null);
		if(produtoAtualizado != null) {
			produtoAtualizado.setProdutoNome(produto.getProdutoNome());
			produtoAtualizado.setCategoria(produto.getCategoria());
			return produtoRepository.save(produtoAtualizado);
		}else {
			return null;
		}
	}

	public Boolean deleteProduto(Integer id) {
		Produto produto = produtoRepository.findById(id).orElse(null);
		if(produto != null) {
			produtoRepository.delete(produto);
			return true;
		}else {
			return false;
		}
	}
}
