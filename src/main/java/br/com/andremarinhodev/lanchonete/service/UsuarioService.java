package br.com.andremarinhodev.lanchonete.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andremarinhodev.lanchonete.controller.exception.EmailAlreadyExistsException;
import br.com.andremarinhodev.lanchonete.controller.form.ClienteForm;
import br.com.andremarinhodev.lanchonete.controller.form.GestorForm;
import br.com.andremarinhodev.lanchonete.model.Perfil;
import br.com.andremarinhodev.lanchonete.model.Usuario;
import br.com.andremarinhodev.lanchonete.repository.PerfilRepository;
import br.com.andremarinhodev.lanchonete.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public void salvarGestor(GestorForm gestor) {
		verificaEmail(gestor);
		usuarioRepository.save(gestor.converter(perfilRepository));
	}

	public void salvarCliente(ClienteForm cliente) {
		verificaEmail(cliente);
		usuarioRepository.save(cliente.converter(perfilRepository));
	}
	
	public boolean contemGestor() {
		Optional<Usuario> optional = usuarioRepository.findByPerfisNome("ROLE_GESTOR");
		if (optional.isPresent()) {
			return true;
		}
		return false;
	}

	private void verificaEmail(GestorForm form) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(form.getEmail());
		if(usuario.isPresent()) {
			throw new EmailAlreadyExistsException("Email: " + form.getEmail());
		}
	}
	
	private void verificaEmail(ClienteForm form) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(form.getEmail());
		if(usuario.isPresent()) {
			throw new EmailAlreadyExistsException("Email: " + form.getEmail());
		}
	}

	public boolean isGestor(Long id) {
		for(Perfil p: usuarioRepository.findById(id).get().getPerfis()) {
			if (p.getNome().equals("ROLE_GESTOR")) {
				return true;
			}
		}
		return false;
	}
	
}
