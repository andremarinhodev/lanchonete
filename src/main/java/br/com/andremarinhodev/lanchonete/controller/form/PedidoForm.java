package br.com.andremarinhodev.lanchonete.controller.form;

import java.util.ArrayList;
import java.util.List;

import br.com.andremarinhodev.lanchonete.model.Pedido;
import br.com.andremarinhodev.lanchonete.model.Usuario;
import br.com.andremarinhodev.lanchonete.repository.ItemPedidoRepository;
import br.com.andremarinhodev.lanchonete.repository.ProdutoRepository;

public class PedidoForm {

	private Long idCliente;
	private List<ItemPedidoForm> itens = new ArrayList<>();

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<ItemPedidoForm> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoForm> itens) {
		this.itens = itens;
	}

	public Pedido converter(Usuario cliente, ProdutoRepository produtoRepository, ItemPedidoRepository itemPedidoRepository) {
		Pedido pedido = new Pedido(cliente);
		for(ItemPedidoForm i : itens) {
			pedido.adicionarItem(i.converter(pedido, produtoRepository, itemPedidoRepository));
		}
		return pedido;
	}

}
