package br.com.andremarinhodev.lanchonete.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.andremarinhodev.lanchonete.controller.dto.PedidoDto;
import br.com.andremarinhodev.lanchonete.controller.form.AtualizacaoPedidoForm;
import br.com.andremarinhodev.lanchonete.controller.form.DevPedidoForm;
import br.com.andremarinhodev.lanchonete.model.Pedido;
import br.com.andremarinhodev.lanchonete.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
@Profile("dev")
public class DevPedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public Page<PedidoDto> listar(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
		Page<Pedido> pedidos = pedidoService.findAll(paginacao);
		return PedidoDto.converter(pedidos);
	}

	@PostMapping("/novo-pedido")
	@Transactional
	public ResponseEntity<PedidoDto> novoPedido(@RequestBody @Valid DevPedidoForm form, UriComponentsBuilder uriBuilder) {
		Pedido pedido = pedidoService.save(form);
		URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PedidoDto(pedido));
	}
	
	@GetMapping("/{idUsuario}")
	public Page<PedidoDto> listarPorCliente(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao
			, @PathVariable Long idUsuario){
			Page<Pedido> pedidos = pedidoService.findAllById(idUsuario, paginacao);
			return PedidoDto.converter(pedidos);
	}

	@GetMapping("/detalhar/{idPedido}")
	public ResponseEntity<PedidoDto> detalhar(@PathVariable Long idPedido) {		
		if (pedidoService.verificarId(idPedido)) {
			return ResponseEntity.ok(new PedidoDto(pedidoService.getById(idPedido)));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/atualizar/{idPedido}")
	@Transactional
	public ResponseEntity<PedidoDto> atualizar(@PathVariable Long idPedido, @RequestBody @Valid AtualizacaoPedidoForm form) {
		if (pedidoService.verificarId(idPedido)) {
			pedidoService.atualizarPedido(idPedido, form);
			return ResponseEntity.ok(new PedidoDto(pedidoService.getById(idPedido)));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/cancelar/{idPedido}")
	@Transactional
	public ResponseEntity<PedidoDto> cancelarPedidoCliente(@PathVariable Long idPedido) {
		if (pedidoService.verificarId(idPedido)) {
			pedidoService.cancelarPedido(idPedido);
			return ResponseEntity.ok(new PedidoDto(pedidoService.getById(idPedido)));
		}
		return ResponseEntity.notFound().build();
	}

}
