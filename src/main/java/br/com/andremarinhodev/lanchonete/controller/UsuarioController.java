package br.com.andremarinhodev.lanchonete.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.andremarinhodev.lanchonete.controller.dto.ClienteDto;
import br.com.andremarinhodev.lanchonete.controller.dto.GestorDto;
import br.com.andremarinhodev.lanchonete.controller.form.ClienteForm;
import br.com.andremarinhodev.lanchonete.controller.form.GestorForm;
import br.com.andremarinhodev.lanchonete.service.UsuarioService;

@RestController
@RequestMapping("/login")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping(value = "/cadastrar-gestor")
	@Transactional
	public ResponseEntity<GestorDto> cadastrarGestor(@RequestBody @Valid GestorForm form, UriComponentsBuilder uriBuilder) {
		if (service.contemGestor()) {
			return ResponseEntity.badRequest().body(new GestorDto(form, "Já há um gestor cadastrado no sistema"));
		}
		service.salvarGestor(form);
		URI uri = uriBuilder.path("/login/cadastro-gestor").build().toUri();
		return ResponseEntity.created(uri).body(new GestorDto(form, "Gestor cadastrado com sucesso"));
	}
	
	@PostMapping(value = "/cadastrar-cliente")
	@Transactional
	public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		if (!service.contemGestor()) {
			return ResponseEntity.badRequest().body(new ClienteDto(form, "Ainda não há gestor cadastrado no sistema"));
		}
		service.salvarCliente(form);
		URI uri = uriBuilder.path("/join/signup").build().toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(form, "Cliente cadastrado com sucesso"));
	}
	
}
