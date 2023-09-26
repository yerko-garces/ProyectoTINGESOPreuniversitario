package com.tingeso.proyecto1_garces.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PaginasController {

    @GetMapping("/paginaAgregarAlumnos")
    public String mostrarPaginaAgregarAlumnos() {
        return "paginaAgregarAlumnos";
    }

    @GetMapping("/paginaCuotas")
    public String mostrarPaginaCantidadCuotas() {
        return "paginaCuotas";
    }
}
