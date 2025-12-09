package com.mercado.paulistinha.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.paulistinha.model.AuditLog;
import com.mercado.paulistinha.repository.AuditLogRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogRepository repo;

    @GetMapping("/produto/nome/{nome}")
    public List<AuditLog> listarPorNomeProduto(@PathVariable String nome) {
        return repo.findByProdutoNomeIgnoreCase(nome);
    }

    @GetMapping("/funcionario/cpf/{cpf}")
    public List<AuditLog> listarPorCpf(@PathVariable String cpf) {
        return repo.findByFuncionarioCpf(cpf);
    }

    @GetMapping
    public List<AuditLog> listarTudo() {
        return repo.findAll();
    }

}

