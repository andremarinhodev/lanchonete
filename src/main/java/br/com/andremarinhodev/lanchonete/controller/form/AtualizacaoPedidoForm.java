package br.com.andremarinhodev.lanchonete.controller.form;

import br.com.andremarinhodev.lanchonete.model.Pedido;
import br.com.andremarinhodev.lanchonete.model.enums.StatusPedido;

public class AtualizacaoPedidoForm {
	
	private StatusPedido status;

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public void atualizar(Pedido pedido) {
		pedido.setStatus(status);
	}
	
}
