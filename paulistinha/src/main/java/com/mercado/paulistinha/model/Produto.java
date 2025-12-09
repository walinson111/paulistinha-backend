package com.mercado.paulistinha.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "Produtos") 
@Getter
@Setter
@NoArgsConstructor
@CompoundIndex(name = "unique_nome", def = "{'nome' : 1}", unique = true)
public class Produto {

    @Id
    private String id;  
    
    private String nome;
    private String descricao;
    private double preco;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private int quantidade;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
}
