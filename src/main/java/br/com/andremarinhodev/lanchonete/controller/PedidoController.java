package br.com.andremarinhodev.lanchonete.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.andremarinhodev.lanchonete.controller.form.PedidoForm;
import br.com.andremarinhodev.lanchonete.model.Pedido;
import br.com.andremarinhodev.lanchonete.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping
	public Page<PedidoDto> lista(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
		Page<Pedido> pedidos = service.findAll(paginacao);
		return PedidoDto.converter(pedidos);
	}

	@PostMapping("/novoPedido")
	@Transactional
	public ResponseEntity<PedidoDto> novoPedido(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriBuilder) {
		Pedido pedido = service.save(form);
		URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PedidoDto(pedido));
	}
	
	@GetMapping("/{idCliente}")
	public Page<PedidoDto> listarPorCliente(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao, @PathVariable Long idCliente){
		Page<Pedido> pedidos = service.findAllById(idCliente, paginacao);
		return PedidoDto.converter(pedidos);
	}
	
	@GetMapping("/{idCliente}/{idPedido}")
	public ResponseEntity<PedidoDto> detalhar(@PathVariable Long idCliente, @PathVariable Long idPedido) {		
		if (service.verificarId(idCliente, idPedido)) {
			return ResponseEntity.ok(new PedidoDto(service.getById(idPedido)));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/atualizar/{idPedido}")
	@Transactional
	public ResponseEntity<PedidoDto> atualizar(@PathVariable Long idPedido, @RequestBody AtualizacaoPedidoForm form) {
		if (service.verificarId(idPedido)) {
			service.atualizarPedido(idPedido, form);
			return ResponseEntity.ok(new PedidoDto(service.getById(idPedido)));
		}
		return ResponseEntity.notFound().build();
	}
	
}





















