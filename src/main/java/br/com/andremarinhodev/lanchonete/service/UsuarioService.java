package br.com.andremarinhodev.lanchonete.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andremarinhodev.lanchonete.controller.exception.EmailAlreadyExistsException;
import br.com.andremarinhodev.lanchonete.controller.form.UsuarioForm;
import br.com.andremarinhodev.lanchonete.model.Usuario;
import br.com.andremarinhodev.lanchonete.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public boolean isEmpty() {
		if (repository.count() == 0) {
			return true;
		}
		return false;
	}

	public void salvarGestor(UsuarioForm usuario) {
		verificaEmail(usuario);
		repository.save(usuario.converterParaGestor());
	}

	public void salvarCliente(UsuarioForm usuario) {
		verificaEmail(usuario);
		repository.save(usuario.converterParaCliente());
	}
	
	private void verificaEmail(UsuarioForm form) {
		Optional<Usuario> usuario = repository.findByEmail(form.getEmail());
		if(usuario.isPresent()) {
			throw new EmailAlreadyExistsException("Email: " + form.getEmail());
		}
	}

}
