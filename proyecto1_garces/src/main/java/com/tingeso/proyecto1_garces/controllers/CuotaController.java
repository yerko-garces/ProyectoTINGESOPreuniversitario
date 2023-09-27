package com.tingeso.proyecto1_garces.controllers;

import com.tingeso.proyecto1_garces.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class CuotaController {

    @Autowired
    AlumnoService alumnoService;

    @GetMapping("/posibilidadDeCuotas")
    public String posibilidadDeCuotas(Model model){
        String cantidadCuotas = alumnoService.cantidadCuotas();
        cantidadCuotas = "La cantidad maxima de cuotas a las que puede optar son " + cantidadCuotas;
        model.addAttribute("resultadoCantidad", cantidadCuotas);
        return "paginaCuotas";
    }

    @PostMapping("/calcularMontoCuotas")
    public String calcularCuota(@RequestParam("cantidad") Integer cantidad, Model model) {
        Double resultadoMT = alumnoService.calcularMontoTotal();
        Double resultado = alumnoService.calcularCuota(resultadoMT, cantidad);
        model.addAttribute("resultado", resultado);
        return "paginaCuotas";
    }
}
