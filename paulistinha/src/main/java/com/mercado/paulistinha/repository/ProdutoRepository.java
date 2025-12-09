package com.mercado.paulistinha.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mercado.paulistinha.model.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String>{
    Optional<Produto> findByNome(String nome);
}
