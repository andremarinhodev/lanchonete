package br.com.andremarinhodev.lanchonete.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andremarinhodev.lanchonete.controller.form.UsuarioForm;
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

	public void salvarGestor(@Valid UsuarioForm usuario) {
		repository.save(usuario.converterParaGestor());
	}

	public void salvarCliente(@Valid UsuarioForm usuario) {
		repository.save(usuario.converterParaCliente());
	}

}
