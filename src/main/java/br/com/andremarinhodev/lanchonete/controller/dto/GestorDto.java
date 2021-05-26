package br.com.andremarinhodev.lanchonete.controller.dto;

import br.com.andremarinhodev.lanchonete.controller.form.GestorForm;

public class GestorDto {
	
	private String nome;
	private String email;
	private String estabelecimento;
	private String mensagem;
	
	
	public GestorDto(GestorForm form, String mensagem) {
		this.nome = form.getNome();
		this.email = form.getEmail();
		this.estabelecimento = form.getEstabelecimento();
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


	public String getEstabelecimento() {
		return estabelecimento;
	}


	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
