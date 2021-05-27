package br.com.andremarinhodev.lanchonete.controller.form;

import javax.validation.constraints.NotNull;

import br.com.andremarinhodev.lanchonete.model.Pedido;
import br.com.andremarinhodev.lanchonete.model.enums.StatusPedido;

public class AtualizacaoPedidoForm {
	
	@NotNull
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
