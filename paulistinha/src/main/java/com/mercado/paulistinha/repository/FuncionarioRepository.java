package com.mercado.paulistinha.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mercado.paulistinha.model.Funcionario;



public interface FuncionarioRepository extends MongoRepository<Funcionario, String> {
    Optional<Funcionario> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
