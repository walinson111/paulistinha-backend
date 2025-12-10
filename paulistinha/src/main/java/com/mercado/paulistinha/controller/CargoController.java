package com.mercado.paulistinha.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.paulistinha.model.Cargo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/cargos")
public class CargoController {
    @GetMapping
    public List<String> listar() 
    {
       return Arrays.stream(Cargo.values())
       .map(Enum::name)
       .toList();
    } 
}
