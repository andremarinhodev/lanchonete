package br.com.andremarinhodev.lanchonete.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.andremarinhodev.lanchonete.model.Cliente;
import br.com.andremarinhodev.lanchonete.model.Gestor;

public class UsuarioForm {

	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty 
	private String email;
	
	@NotNull @NotEmpty 
	private String senha;
	private String estabelecimento;
	private LocalDate dataNascimento;
	private String telefone;
	
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
	
	public Cliente converterParaCliente() {
		return new Cliente(nome, email, senha, dataNascimento, telefone);
	}
	
	public Gestor converterParaGestor() {
		return new Gestor(nome, email, senha, estabelecimento);
	}
}