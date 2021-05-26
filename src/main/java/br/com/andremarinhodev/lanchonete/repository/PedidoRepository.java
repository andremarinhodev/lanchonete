package br.com.andremarinhodev.lanchonete.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andremarinhodev.lanchonete.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	Page<Pedido> findByClienteId(Long id, Pageable paginacao);

}
