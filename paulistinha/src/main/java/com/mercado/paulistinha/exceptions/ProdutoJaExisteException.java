package com.mercado.paulistinha.exceptions;

public class ProdutoJaExisteException extends RuntimeException {
    public ProdutoJaExisteException(String message) {
        super(message);
    }
}

