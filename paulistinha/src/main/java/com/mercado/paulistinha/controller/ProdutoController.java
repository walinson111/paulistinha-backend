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
import com.mercado.paulistinha.service.AuditLogService;
import com.mercado.paulistinha.service.ProdutoService;



@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final AuditLogService auditLogService;

    public ProdutoController(ProdutoService produtoService, AuditLogService auditLogService) {
        this.produtoService = produtoService;
        this.auditLogService = auditLogService;
    }

    @GetMapping
    public List<Produto> listar() {
        auditLogService.registrar("LISTAR", "PRODUTO", "ALL");
        return produtoService.listarProdutos();
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        Produto criado = produtoService.salvarProduto(produto);
        auditLogService.registrar("CRIAR", criado, 0);
        return criado;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        Produto existente = produtoService.buscarPeloId(id); 
        auditLogService.registrar("DELETAR", existente, 0);
        produtoService.deletarProduto(id);
    }

    @GetMapping("/{id}")
    public Produto buscarPeloId(@PathVariable String id) {
        Produto p = produtoService.buscarPeloId(id);
        auditLogService.registrar("BUSCAR_POR_ID", p, 0);
        return p;
    }

    @GetMapping("/nome")
    public Produto buscarPeloNome(@RequestParam String nome) {
        Produto p = produtoService.buscarPeloNome(nome);
        auditLogService.registrar("BUSCAR_POR_NOME", p, 0);
        return p;
    }

    @PostMapping("/lote")
    public List<Produto> salvarLote(@RequestBody List<Produto> produtos) {
        List<Produto> salvos = produtoService.salvarLote(produtos);
        auditLogService.registrar("CRIAR_LOTE", "PRODUTO", "LOTE");
        return salvos;
    }

    @DeleteMapping("/lote")
    public void deletarLote(){
        produtoService.deletarLote();
        auditLogService.registrar("DELETAR_LOTE", "PRODUTO", "LOTE");
    }

    @PutMapping("/retirar/{nome}")
    public Produto retirarEstoque(@PathVariable String nome, @RequestParam int quantidade) {
        Produto atualizado = produtoService.retirarEstoque(nome, quantidade);
        auditLogService.registrar("RETIRAR_ESTOQUE", atualizado, quantidade);
        return atualizado;
    }

    @PutMapping("/adicionar/{nome}")
    public Produto adicionarEstoque(@PathVariable String nome, @RequestParam int quantidade) {
        Produto atualizado = produtoService.adicionarEstoque(nome, quantidade);
        auditLogService.registrar("ADICIONAR_ESTOQUE", atualizado, quantidade);
        return atualizado;
    }
}


