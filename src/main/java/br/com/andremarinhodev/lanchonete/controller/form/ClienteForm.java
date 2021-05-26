package br.com.andremarinhodev.lanchonete.controller.form;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.andremarinhodev.lanchonete.model.Cliente;
import br.com.andremarinhodev.lanchonete.model.Perfil;
import br.com.andremarinhodev.lanchonete.repository.PerfilRepository;

public class ClienteForm {

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
	private LocalDate dataNascimento;

	@NotNull
	@NotEmpty
	private String telefone;

	private Perfil perfil = new Perfil("ROLE_CLIENTE");

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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Cliente converter(PerfilRepository perfilRepository) {
		Cliente cliente = new Cliente(nome, email, new BCryptPasswordEncoder().encode(senha), dataNascimento, telefone);
		Optional<Perfil> optional = perfilRepository.findByNome(perfil.getNome());
		if (!optional.isPresent()) {
			perfilRepository.save(perfil);
			cliente.getPerfis().add(perfil);
		} else {
			cliente.getPerfis().add(optional.get());
		}
		return cliente;
	}

}
