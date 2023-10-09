package com.tingeso.proyecto1_garces.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PaginasController {

    @GetMapping("/index")
    public String mostrarPaginaIndex() {
        return "index";
    }

    @GetMapping("/paginaAgregarAlumnos")
    public String mostrarPaginaAgregarAlumnos() {
        return "paginaAgregarAlumnos";
    }

    @GetMapping("/paginaEntregarNotas")
    public String mostrarPaginaEntregarNotas() {
        return "paginaEntregarNotas";
    }

    @GetMapping("/paginaPagos")
    public String mostrarPaginaPagos() {
        return "paginaPagos";
    }
}
