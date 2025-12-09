package com.mercado.paulistinha.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mercado.paulistinha.auth.FuncionarioUserDetails;
import com.mercado.paulistinha.model.Funcionario;
import com.mercado.paulistinha.repository.FuncionarioRepository;

@Service
public class FuncionarioUserDetailsService implements UserDetailsService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioUserDetailsService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepository
                .findByCpf(cpf)
                .orElseThrow(() -> new UsernameNotFoundException("Funcionário não encontrado"));

        return new FuncionarioUserDetails(funcionario);
    }
}

