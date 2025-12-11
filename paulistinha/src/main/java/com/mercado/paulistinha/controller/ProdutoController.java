package com.mercado.paulistinha.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.paulistinha.dto.produto.ProdutoCreateDTO;
import com.mercado.paulistinha.model.Produto;
import com.mercado.paulistinha.service.AuditLogService;
import com.mercado.paulistinha.service.ProdutoService;


@CrossOrigin(origins="*")
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
        return produtoService.listarProdutos();
    }

    @PostMapping
    public Produto criarProduto(@RequestBody ProdutoCreateDTO dto) {
        Produto novoProduto = produtoService.criarProduto(dto);
        auditLogService.registrar("CRIAR", novoProduto, 0); 
        return novoProduto;
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
        return p;
    }

    @GetMapping("/nome")
    public Produto buscarPeloNome(@RequestParam String nome) {
        Produto p = produtoService.buscarPeloNome(nome);
        return p;
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

    @PutMapping("/{id}")
    public Produto atualizarProduto(
        @PathVariable String id,
        @RequestBody ProdutoCreateDTO dto) {
        Produto produtoAntesDaAtualizacao = produtoService.buscarPeloId(id);
        auditLogService.registrar("EDITAR", produtoAntesDaAtualizacao, 0);

        Produto atualizado = produtoService.atualizarProduto(id, dto);
        return atualizado;
    }
}