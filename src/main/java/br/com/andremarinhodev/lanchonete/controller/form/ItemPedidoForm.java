package br.com.andremarinhodev.lanchonete.controller.form;

import br.com.andremarinhodev.lanchonete.model.ItemPedido;
import br.com.andremarinhodev.lanchonete.model.Pedido;
import br.com.andremarinhodev.lanchonete.repository.ItemPedidoRepository;
import br.com.andremarinhodev.lanchonete.repository.ProdutoRepository;

public class ItemPedidoForm {

	private Long idProduto;
	private int quantidade;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public ItemPedido converter(Pedido pedido, ProdutoRepository produtoRepository, ItemPedidoRepository itemPedidoRepository) {
		ItemPedido itemPedido = new ItemPedido(quantidade, pedido, produtoRepository.findById(idProduto).get());
		itemPedidoRepository.save(itemPedido);
		return itemPedido;
	}

}
