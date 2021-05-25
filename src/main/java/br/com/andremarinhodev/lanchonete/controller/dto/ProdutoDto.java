package br.com.andremarinhodev.lanchonete.controller.dto;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import br.com.andremarinhodev.lanchonete.model.Produto;
import br.com.andremarinhodev.lanchonete.model.enums.Classificacao;

public class ProdutoDto {
	
	private String nome;
	private BigDecimal preco;
	private Classificacao classificacao;
	
	public ProdutoDto(Produto produto) {
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.classificacao = produto.getClassificacao();
	}

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

	public static Page<ProdutoDto> converter(Page<Produto> produtos) {
		return produtos.map(ProdutoDto::new);
	}
	
}
