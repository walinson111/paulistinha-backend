package com.mercado.paulistinha.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "audit_logs")
public class AuditLog {

    @Id
    private String id;

    private String acao; 
    private String produtoId; 
    private String produtoNome;      
    private int quantidadeAlterada; 
    
    private String funcionarioId;
    private String funcionarioNome;  
    
    private String descricao; 
    private LocalDateTime dataHora;
    private String funcionarioCpf;  
}
