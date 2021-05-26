package br.com.andremarinhodev.lanchonete.controller.dto;

import java.time.LocalDate;

import br.com.andremarinhodev.lanchonete.controller.form.ClienteForm;

public class ClienteDto {

	public String nome;
	public String email;
	private LocalDate dataNascimento;
	private String telefone;
	private String mensagem;

	public ClienteDto(ClienteForm form, String mensagem) {
		this.nome = form.getNome();
		this.email = form.getEmail();
		this.dataNascimento = form.getDataNascimento();
		this.telefone = form.getTelefone();
		this.mensagem = mensagem;
	}

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

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
