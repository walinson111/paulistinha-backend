package com.mercado.paulistinha.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mercado.paulistinha.model.AuditLog;

public interface AuditLogRepository extends MongoRepository<AuditLog, String> {
    List<AuditLog> findByProdutoNomeIgnoreCase(String nome);

    List<AuditLog> findByFuncionarioId(String funcionarioId); 

    List<AuditLog> findByFuncionarioCpf(String cpf); 
}
