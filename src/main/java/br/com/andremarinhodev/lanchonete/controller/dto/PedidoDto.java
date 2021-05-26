package br.com.andremarinhodev.lanchonete.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.andremarinhodev.lanchonete.model.ItemPedido;
import br.com.andremarinhodev.lanchonete.model.Pedido;
import br.com.andremarinhodev.lanchonete.model.Produto;
import br.com.andremarinhodev.lanchonete.model.Usuario;
import br.com.andremarinhodev.lanchonete.model.enums.StatusPedido;

public class PedidoDto {

	private Long idPedido;
	private BigDecimal valorTotal;
	private LocalDate data;
	private StatusPedido status;
	private Usuario cliente;
	private List<ItemPedido> itens;
	
	public PedidoDto(Pedido pedido) {
		this.idPedido = pedido.getId();
		this.valorTotal = pedido.getValorTotal();
		this.data = pedido.getData();
		this.status = pedido.getStatus();
		this.cliente = pedido.getCliente();
		this.itens = pedido.getItens();
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public static Page<PedidoDto> converter(Page<Pedido> pedidos) {
		return pedidos.map(PedidoDto::new);
	}
	
}
