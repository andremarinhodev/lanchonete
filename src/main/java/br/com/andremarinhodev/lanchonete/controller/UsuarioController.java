package br.com.andremarinhodev.lanchonete.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.andremarinhodev.lanchonete.controller.dto.UsuarioDto;
import br.com.andremarinhodev.lanchonete.controller.form.UsuarioForm;
import br.com.andremarinhodev.lanchonete.repository.UsuarioRepository;

@RestController
@RequestMapping("/join")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping(value = "/signup")
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody UsuarioForm usuario, UriComponentsBuilder uriBuilder) {
		if (usuarioRepository.count() == 0) {
			usuarioRepository.save(usuario.converterParaGestor());
		}else {
			usuarioRepository.save(usuario.converterParaCliente());	
		}
		
		URI uri = uriBuilder.path("/join/signup").build().toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}
	
	
}
