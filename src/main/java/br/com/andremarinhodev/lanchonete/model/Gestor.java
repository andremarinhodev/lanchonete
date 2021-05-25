package br.com.andremarinhodev.lanchonete.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Gestor extends Usuario{
	
	private String estabelecimento;
	
	public Gestor() {
	}

	public Gestor(String nome, String email, String senha, String estabelecimento) {
		super(nome, email, senha);
		this.estabelecimento = estabelecimento;
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
}
