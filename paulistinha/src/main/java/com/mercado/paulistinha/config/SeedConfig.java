package com.mercado.paulistinha.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mercado.paulistinha.model.Cargo;
import com.mercado.paulistinha.model.Funcionario;
import com.mercado.paulistinha.repository.FuncionarioRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SeedConfig {

    private final PasswordEncoder encoder;

    @Bean
    public CommandLineRunner seed(FuncionarioRepository repo) {
        return args -> {
            if (repo.findByCpf("11111111111").isEmpty()) {

                Funcionario gerente = new Funcionario();
                gerente.setNome("Adilson Moreira");
                gerente.setCpf("11111111111");
                gerente.setSenha(encoder.encode("123"));
                gerente.setCargo(Cargo.GERENTE);

                repo.save(gerente);
                System.out.println("Gerente inicial criado!");
            }
        };
    }
}

