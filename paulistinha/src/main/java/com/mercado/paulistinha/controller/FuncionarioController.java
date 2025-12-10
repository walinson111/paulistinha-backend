package com.mercado.paulistinha.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.paulistinha.model.Funcionario;
import com.mercado.paulistinha.service.FuncionarioService;


@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*")
public class FuncionarioController {
    
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService){
        this.funcionarioService = funcionarioService;
    }

    @GetMapping()
    public List<Funcionario> listarFuncionarios() {
        return funcionarioService.listarFuncionarios();
    }

    @GetMapping("/{cpf}")
    public Funcionario buscarPeloCpf(@PathVariable String cpf) {
        return funcionarioService.buscarPeloCpf(cpf);
    }

    @PostMapping()
    public void criarFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioService.criarFuncionario(funcionario);
    }

    @PutMapping("/{id}")
    public Funcionario atualizarFuncionario(
            @PathVariable String id,
            @RequestBody Funcionario funcionario) {
        return funcionarioService.atualizarFuncionario(id, funcionario);
    }

    @DeleteMapping("/{id}")
    public void deletarFuncionario(@PathVariable String id){
        funcionarioService.deletarFuncionario(id);
    }
}

