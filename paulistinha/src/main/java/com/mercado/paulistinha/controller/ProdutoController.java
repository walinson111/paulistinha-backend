package com.mercado.paulistinha.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.paulistinha.model.Produto;
import com.mercado.paulistinha.service.ProdutoService;



@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoService.listarProdutos();
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        produtoService.deletarProduto(id);
    }

    @GetMapping("/{id}")
    public Produto buscarPeloId(@PathVariable String id) {
        return produtoService.buscarPeloId(id);
    }

    @GetMapping("/nome")
    public Produto buscarPeloNome(@RequestParam String nome) {
        return produtoService.buscarPeloNome(nome);
    }
    
    @PostMapping("/lote")
    public List<Produto> salvarLote(@RequestBody List<Produto> produtos) {
        return produtoService.salvarLote(produtos);
    }
    
    @DeleteMapping("/lote")
    public void deletarLote(){
        produtoService.deletarLote();
    }

    @PutMapping("/retirar/{nome}")
    public Produto retirarEstoque(@PathVariable String nome, @RequestParam int quantidade) {
        return produtoService.retirarEstoque(nome, quantidade);
    }
    
    @PutMapping("/adicionar/{nome}")
    public Produto adicionarEstoque(@PathVariable String nome, @RequestParam int quantidade) {
        return produtoService.adicionarEstoque(nome, quantidade);
    }
}
