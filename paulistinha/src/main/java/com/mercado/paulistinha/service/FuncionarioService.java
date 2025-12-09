package com.mercado.paulistinha.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mercado.paulistinha.model.Funcionario;
import com.mercado.paulistinha.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;

    public FuncionarioService(FuncionarioRepository funcionarioRepository,
                              PasswordEncoder passwordEncoder) {
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarPeloCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public void criarFuncionario(Funcionario funcionario) {

        if (funcionario.getSenha() == null || funcionario.getSenha().isBlank()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }

        if (funcionarioRepository.existsByCpf(funcionario.getCpf())) {
            throw new IllegalArgumentException("Já existe um funcionário com esse CPF.");
        }

        funcionario.setSenha(passwordEncoder.encode(funcionario.getSenha()));

        funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizarFuncionario(String id, Funcionario novosDados) {
        Funcionario existente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        existente.setNome(novosDados.getNome());
        existente.setTelefone(novosDados.getTelefone());
        existente.setEndereco(novosDados.getEndereco());
        existente.setSalario(novosDados.getSalario());
        existente.setCargo(novosDados.getCargo());

        if (!existente.getCpf().equals(novosDados.getCpf())) {
            if (funcionarioRepository.existsByCpf(novosDados.getCpf())) {
                throw new IllegalArgumentException("Já existe um funcionário com esse CPF.");
            }
            existente.setCpf(novosDados.getCpf());
        }

        if (novosDados.getSenha() != null && !novosDados.getSenha().isBlank()) {
            existente.setSenha(passwordEncoder.encode(novosDados.getSenha()));
        }

        return funcionarioRepository.save(existente);
    }

    public void deletarFuncionario(String id){
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }

        funcionarioRepository.deleteById(id);
    }
}


