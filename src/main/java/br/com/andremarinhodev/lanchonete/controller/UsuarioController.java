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
import br.com.andremarinhodev.lanchonete.controller.exception.EmptyFieldException;
import br.com.andremarinhodev.lanchonete.controller.form.UsuarioForm;
import br.com.andremarinhodev.lanchonete.service.UsuarioService;
import br.com.andremarinhodev.lanchonete.util.Validator;

@RestController
@RequestMapping("/join")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping(value = "/signup")
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
		if (service.isEmpty()) {
			if (Validator.isNullOrEmpty(form.getEstabelecimento())) {
				throw new EmptyFieldException("estabelecimento");
			}
			service.salvarGestor(form);
		}else {
			if (Validator.isNullOrEmpty(form.getDataNascimento())) {
				throw new EmptyFieldException("data de nascimento");
			} else if (Validator.isNullOrEmpty(form.getTelefone())) {
				throw new EmptyFieldException("telefone");
			}
			service.salvarCliente(form);
		}
		
		URI uri = uriBuilder.path("/join/signup").build().toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(form));
	}
	
	
}
