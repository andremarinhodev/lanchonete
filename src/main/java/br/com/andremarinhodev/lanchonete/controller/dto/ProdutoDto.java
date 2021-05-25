package br.com.andremarinhodev.lanchonete.controller.dto;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import br.com.andremarinhodev.lanchonete.controller.form.ProdutoForm;
import br.com.andremarinhodev.lanchonete.model.Produto;
import br.com.andremarinhodev.lanchonete.model.enums.Classificacao;

public class ProdutoDto {
	
	private Long id;
	private String nome;
	private BigDecimal preco;
	private Classificacao classificacao;
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.classificacao = produto.getClassificacao();
	}

	public ProdutoDto(Long id, ProdutoForm form) {
		this.id = id;
		this.nome = form.getNome();
		this.preco = form.getPreco();
		this.classificacao = form.getClassificacao();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
