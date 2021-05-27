package br.com.andremarinhodev.lanchonete.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.andremarinhodev.lanchonete.controller.form.ProdutoForm;
import br.com.andremarinhodev.lanchonete.model.Produto;
import br.com.andremarinhodev.lanchonete.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;

	public Page<Produto> findAll(Pageable paginacao) {
		return repository.findByIsActive(paginacao, true);
	}

	public Produto save(ProdutoForm form) {
		Produto produto = form.converter();
		repository.save(produto);
		return produto;
	}

	public void atualizarProduto(Long id, ProdutoForm form) {
		form.atualizar(repository.findById(id).get());
	}
	
	public void ativarProduto(Long id) {
		Produto produto = repository.findById(id).get();
		produto.setActive(true);;
	}
	
	public void desativarProduto(Long id) {
		Produto produto = repository.findById(id).get();
		produto.setActive(false);;
	}

	public Produto getById(Long id) {
		return repository.findById(id).get();
	}
	
	public boolean findById(Long id) {
		Optional<Produto> optional = repository.findById(id);
		if (optional.isPresent()) {
			return true;
		}
		return false;
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
}
