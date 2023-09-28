package com.tingeso.proyecto1_garces.controllers;

import com.tingeso.proyecto1_garces.services.AlumnoService;
import com.tingeso.proyecto1_garces.services.CuotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class CuotaController {

    @Autowired
    AlumnoService alumnoService;

    @Autowired
    CuotasService cuotasService;

    @GetMapping("/paginaCuotas")
    public String posibilidadDeCuotas(Model model){
        String cantidadCuotas = cuotasService.cantidadCuotas();
        cantidadCuotas = "La cantidad maxima de cuotas a las que puede optar son " + cantidadCuotas;
        model.addAttribute("resultadoCantidad", cantidadCuotas);
        return "paginaCuotas";
    }

    @PostMapping("/paginaCuotas")
    public String calcularCuota(@RequestParam("cantidad") Integer cantidad, Model model) {
        Double resultadoMT = cuotasService.calcularMontoTotal();
        Double resultado = cuotasService.calcularCuota(resultadoMT, cantidad);
        model.addAttribute("resultado", resultado);
        return "paginaCuotas";
    }
}
