package br.com.andremarinhodev.lanchonete.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.andremarinhodev.lanchonete.model.Produto;
import br.com.andremarinhodev.lanchonete.model.enums.Classificacao;

public class ProdutoForm {

	@NotNull @NotEmpty
	private String nome;
	
	@NotNull
	private BigDecimal preco;
	
	@NotNull
	private Classificacao classificacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public Produto converter() {
		return new Produto(nome, preco, classificacao);
	}

}
