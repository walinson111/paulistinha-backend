package com.mercado.paulistinha.dto;

import com.mercado.paulistinha.model.Categoria;

public record ProdutoDeleteDTO(
    String nome,
    String descricao,
    double preco,
    Categoria categorias
) {}
