package br.com.andremarinhodev.lanchonete.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
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

import br.com.andremarinhodev.lanchonete.config.security.TokenService;
import br.com.andremarinhodev.lanchonete.controller.dto.PedidoDto;
import br.com.andremarinhodev.lanchonete.controller.form.AtualizacaoPedidoForm;
import br.com.andremarinhodev.lanchonete.controller.form.PedidoForm;
import br.com.andremarinhodev.lanchonete.model.Pedido;
import br.com.andremarinhodev.lanchonete.service.PedidoService;
import br.com.andremarinhodev.lanchonete.service.UsuarioService;

@RestController
@RequestMapping(value = "/pedidos")
@Profile("prod")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TokenService tokenService;
	
	@GetMapping("/todos")
	public Page<PedidoDto> listarTodos(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
		Page<Pedido> pedidos = pedidoService.findAll(paginacao);
		return PedidoDto.converter(pedidos);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PedidoDto> novoPedido(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
		String token = recuperarToken(request);
		Pedido pedido = pedidoService.save(form, tokenService.getIdUsuario(token));
		URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PedidoDto(pedido));
	}
	
	@GetMapping
	public Page<PedidoDto> listarPorCliente(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao
			, HttpServletRequest request){
		String token = recuperarToken(request);
		Long idUsuario = tokenService.getIdUsuario(token);
		Page<Pedido> pedidos = pedidoService.findAllById(idUsuario, paginacao);
		return PedidoDto.converter(pedidos);

	}

	@GetMapping("/{idPedido}")
	public ResponseEntity<PedidoDto> detalhar(@PathVariable Long idPedido, HttpServletRequest request) {		
		String token = recuperarToken(request);
		Long idUsuario = tokenService.getIdUsuario(token);
		if (usuarioService.isGestor(idUsuario)) {
			return ResponseEntity.ok(new PedidoDto(pedidoService.getById(idPedido)));
		} else if (pedidoService.verificarId(idUsuario, idPedido)) {
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
	public ResponseEntity<PedidoDto> cancelarPedidoCliente(@PathVariable Long idPedido, HttpServletRequest request) {
		String token = recuperarToken(request);
		Long idUsuario = tokenService.getIdUsuario(token);
		if (usuarioService.isGestor(idUsuario)) {
			if (pedidoService.verificarId(idPedido)) {
				pedidoService.cancelarPedido(idPedido);
				return ResponseEntity.ok(new PedidoDto(pedidoService.getById(idPedido)));
			}
			return ResponseEntity.notFound().build();
		} else if (pedidoService.verificarId(idUsuario, idPedido)) {
			if (pedidoService.verificarStatus(idUsuario, idPedido)) {
				pedidoService.cancelarPedido(idPedido);
				return ResponseEntity.ok(new PedidoDto(pedidoService.getById(idPedido)));
			}
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		return token.substring(7, token.length());
	}
}
