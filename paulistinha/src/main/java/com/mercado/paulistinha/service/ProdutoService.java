package com.mercado.paulistinha.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mercado.paulistinha.exceptions.EstoqueInsuficienteException;
import com.mercado.paulistinha.exceptions.ProdutoJaExisteException;
import com.mercado.paulistinha.exceptions.ProdutoNotFoundException;
import com.mercado.paulistinha.model.Produto;
import com.mercado.paulistinha.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvarProduto(Produto produto) {
        if (produtoRepository.findByNome(produto.getNome()).isPresent()) {
            throw new ProdutoJaExisteException("Já existe um produto com o nome: " + produto.getNome());
        }
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public void deletarProduto(String id) {
        if (!produtoRepository.existsById(id)) {
            throw new ProdutoNotFoundException("Produto não encontrado para exclusão: " + id);
        }
        produtoRepository.deleteById(id);
    }

    public Produto buscarPeloId(String id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado com id: " + id));
    }

    public Produto buscarPeloNome(String nome) {
        return produtoRepository.findByNome(nome)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado: " + nome));
    }

    public List<Produto> salvarLote(List<Produto> produtos) {
        return produtoRepository.saveAll(produtos);
    }

    public void deletarLote() {
        produtoRepository.deleteAll();
    }

    public Produto retirarEstoque(String nome, int quantidade) {
        Produto produto = produtoRepository.findByNome(nome)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado: " + nome));

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade retirada deve ser maior que zero.");
        }

        if (produto.getQuantidade() < quantidade) {
            throw new EstoqueInsuficienteException(
                "Estoque insuficiente. Atual: " + produto.getQuantidade() + ", solicitado: " + quantidade
            );
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);
        return produtoRepository.save(produto);
    }

    public Produto adicionarEstoque(String nome, int quantidade) {
        Produto produto = produtoRepository.findByNome(nome)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado: " + nome));

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade adicionada deve ser maior que zero.");
        }

        produto.setQuantidade(produto.getQuantidade() + quantidade);
        return produtoRepository.save(produto);
    }
}

