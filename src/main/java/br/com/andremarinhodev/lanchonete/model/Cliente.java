package br.com.andremarinhodev.lanchonete.model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Cliente extends Usuario{
	
	private static final long serialVersionUID = 1L;
	
	private LocalDate dataNascimento;
	private String telefone;

	public Cliente() {
	}

	public Cliente(String nome, String email, String senha, LocalDate dataNascimento, String telefone) {
		super(nome, email, senha);
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
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
	
}
