package com.mercado.paulistinha.service;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mercado.paulistinha.auth.FuncionarioUserDetails;
import com.mercado.paulistinha.model.AuditLog;
import com.mercado.paulistinha.model.Funcionario;
import com.mercado.paulistinha.model.Produto;
import com.mercado.paulistinha.repository.AuditLogRepository;


@Service
public class AuditLogService {

    private final AuditLogRepository repo;

    public AuditLogService(AuditLogRepository repo) {
        this.repo = repo;
    }

    private Funcionario getFuncionarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
            return null;
        }

        FuncionarioUserDetails user = (FuncionarioUserDetails) auth.getPrincipal();
        return user.getFuncionario();
    }

    public void registrar(String acao, Produto produto, int quantidadeAlterada) {

        Funcionario funcionario = getFuncionarioLogado();

        String descricao = switch (acao) {
            case "CRIAR" ->
                "Criou o produto " + produto.getNome();

            case "EDITAR" ->
                "Editou o produto " + produto.getNome();

            case "DELETAR" ->
                "Deletou o produto " + produto.getNome();

            case "ADICIONAR_ESTOQUE" ->
                "Adicionou " + quantidadeAlterada + " unidades ao produto " + produto.getNome();

            case "RETIRAR_ESTOQUE" ->
                "Retirou " + quantidadeAlterada + " unidades do produto " + produto.getNome();

            default ->
                "Ação não identificada no produto " + produto.getNome();
        };

        AuditLog log = new AuditLog();
        log.setAcao(acao);
        log.setProdutoId(produto.getId());
        log.setProdutoNome(produto.getNome());
        log.setQuantidadeAlterada(quantidadeAlterada);
        log.setDescricao(descricao);
        log.setDataHora(LocalDateTime.now());

        if (funcionario != null) {
            log.setFuncionarioId(funcionario.getId());
            log.setFuncionarioNome(funcionario.getNome());
            log.setFuncionarioCpf(funcionario.getCpf());
        }

        repo.save(log);
    }

    public void registrar(String acao, String entidade, String identificador) {

        Funcionario funcionario = getFuncionarioLogado();

        String descricao = switch (acao) {
            case "LISTAR" ->
                "Listou todos os registros de " + entidade;

            case "BUSCAR" ->
                "Buscou o registro de " + entidade + " com valor: " + identificador;

            case "CRIAR_LOTE" ->
                "Criou um lote de " + entidade;

            case "DELETAR_LOTE" ->
                "Deletou um lote inteiro de " + entidade;

            default ->
                "Executou ação '" + acao + "' em " + entidade;
        };

        AuditLog log = new AuditLog();
        log.setAcao(acao);
        log.setDescricao(descricao);
        log.setDataHora(LocalDateTime.now());

        if (entidade.equalsIgnoreCase("PRODUTO")) {
            log.setProdutoId(identificador);
            log.setProdutoNome(identificador);
        }

        if (funcionario != null) {
            log.setFuncionarioId(funcionario.getId());
            log.setFuncionarioNome(funcionario.getNome());
            log.setFuncionarioCpf(funcionario.getCpf());
        }

        repo.save(log);
    }
}


