package br.com.andremarinhodev.lanchonete.controller.dto;

import br.com.andremarinhodev.lanchonete.controller.form.UsuarioForm;

public class UsuarioDto {
	
	public String nome;
	public String email;
	
	
	public UsuarioDto(UsuarioForm form) {
		this.nome = form.getNome();
		this.email = form.getEmail();
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

}
