package br.com.andremarinhodev.lanchonete.controller.form;

import java.math.BigDecimal;

import br.com.andremarinhodev.lanchonete.model.Produto;
import br.com.andremarinhodev.lanchonete.model.enums.Classificacao;

public class ProdutoForm {

	private String nome;
	private BigDecimal preco;
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
