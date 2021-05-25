package br.com.andremarinhodev.lanchonete.controller.form;

import java.time.LocalDate;

import br.com.andremarinhodev.lanchonete.model.Cliente;

public class UsuarioForm {

	private String nome;
	private String email;
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
}
