package br.com.andremarinhodev.lanchonete.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.andremarinhodev.lanchonete.controller.dto.ProdutoDto;
import br.com.andremarinhodev.lanchonete.controller.form.ProdutoForm;
import br.com.andremarinhodev.lanchonete.model.Produto;
import br.com.andremarinhodev.lanchonete.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;

	@GetMapping
	public Page<ProdutoDto> lista(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
		Page<Produto> produtos = service.findAll(paginacao);
		return ProdutoDto.converter(produtos);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody ProdutoForm form, UriComponentsBuilder uriBuilder) {
		Produto produto = form.converter();
		service.save(produto);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}
}
