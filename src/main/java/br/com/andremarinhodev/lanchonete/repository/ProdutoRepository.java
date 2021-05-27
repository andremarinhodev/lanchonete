package br.com.andremarinhodev.lanchonete.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andremarinhodev.lanchonete.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Page<Produto> findByIsActive(Pageable paginacao, boolean isActive);
}
