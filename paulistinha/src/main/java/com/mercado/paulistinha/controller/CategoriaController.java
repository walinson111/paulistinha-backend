package com.mercado.paulistinha.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.paulistinha.model.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    @GetMapping
    public List<String> listar() {
        return Arrays.stream(Categoria.values())
                .map(Enum::name)
                .toList();
    }
}
