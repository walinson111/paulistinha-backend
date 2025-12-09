package com.mercado.paulistinha.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "funcionarios") 
@Getter
@Setter
@NoArgsConstructor
public class Funcionario {
    
    @Id
    private String id;

    private String nome;
    private String cpf;
    private String senha;
    private String telefone;
    private String endereco;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;
}
