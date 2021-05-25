package br.com.andremarinhodev.lanchonete.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.andremarinhodev.lanchonete.controller.dto.UsuarioDto;
import br.com.andremarinhodev.lanchonete.controller.form.UsuarioForm;
import br.com.andremarinhodev.lanchonete.service.UsuarioService;

@RestController
@RequestMapping("/join")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping(value = "/signup")
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm usuario, UriComponentsBuilder uriBuilder) {
		if (service.isEmpty()) {
			service.salvarGestor(usuario);
		}else {
			service.salvarCliente(usuario);
		}
		
		URI uri = uriBuilder.path("/join/signup").build().toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}
	
	
}
