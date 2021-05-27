package br.com.andremarinhodev.lanchonete.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.andremarinhodev.lanchonete.model.Gestor;
import br.com.andremarinhodev.lanchonete.model.Perfil;
import br.com.andremarinhodev.lanchonete.repository.PerfilRepository;

public class GestorForm {

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	private String email;

	@NotNull
	@NotEmpty
	private String senha;

	@NotNull
	@NotEmpty
	private String estabelecimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Gestor converter(PerfilRepository perfilRepository) {
		Gestor gestor = new Gestor(nome, email.toLowerCase(), new BCryptPasswordEncoder().encode(senha), estabelecimento);
		Perfil perfil = new Perfil("ROLE_GESTOR");
		Optional<Perfil> optional = perfilRepository.findByNome(perfil.getNome());
		if (!optional.isPresent()) {
			perfilRepository.save(perfil);
			gestor.getPerfis().add(perfil);
		} else {
			gestor.getPerfis().add(optional.get());
		}
		return gestor;
	}
	
}
