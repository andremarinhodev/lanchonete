package br.com.andremarinhodev.lanchonete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andremarinhodev.lanchonete.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{

}
