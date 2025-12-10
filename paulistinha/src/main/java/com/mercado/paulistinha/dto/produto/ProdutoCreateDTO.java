package com.mercado.paulistinha.dto.produto;

import com.mercado.paulistinha.model.Categoria;

public record ProdutoCreateDTO(
    String nome,
    double preco,
    Categoria categoria,
    int quantidade,
    String descricao   
) {}
