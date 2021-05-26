package br.com.andremarinhodev.lanchonete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andremarinhodev.lanchonete.controller.form.PedidoForm;
import br.com.andremarinhodev.lanchonete.model.Pedido;
import br.com.andremarinhodev.lanchonete.repository.ItemPedidoRepository;
import br.com.andremarinhodev.lanchonete.repository.PedidoRepository;
import br.com.andremarinhodev.lanchonete.repository.ProdutoRepository;
import br.com.andremarinhodev.lanchonete.repository.UsuarioRepository;

@Service
public class PedidoService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido save(PedidoForm form) {
		Pedido pedido = form.converter(usuarioRepository.findById(form.getIdCliente()).get(), produtoRepository, itemPedidoRepository);
		pedido.calculaValorTotal();
		pedidoRepository.save(pedido);
		return pedido;
	}

}
