package com.tingeso.proyecto1_garces.controllers;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import com.tingeso.proyecto1_garces.entities.CuotaEntity;
import com.tingeso.proyecto1_garces.repositories.AlumnoRepository;
import com.tingeso.proyecto1_garces.repositories.CuotaRepository;
import com.tingeso.proyecto1_garces.services.AlumnoService;
import com.tingeso.proyecto1_garces.services.CuotasService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping
public class CuotaController {

    @Autowired
    CuotasService cuotasService;

    @Autowired
    CuotaRepository cuotaRepository;

    @GetMapping("/paginaCuotas")
    public String posibilidadDeCuotas(Model model, HttpSession session){
        AlumnoEntity nuevoAlumno = (AlumnoEntity) session.getAttribute("nuevoAlumno");
        String cantidadCuotas = cuotasService.cantidadCuotas(nuevoAlumno.getRut());
        cantidadCuotas = "La cantidad maxima de cuotas a las que puede optar son " + cantidadCuotas;
        model.addAttribute("resultadoCantidad", cantidadCuotas);
        session.setAttribute("nuevoAlumno", nuevoAlumno);
        return "paginaCuotas";
    }

    @PostMapping("/paginaCuotas")
    public String calcularCuota(@RequestParam("cantidad") Integer cantidad, Model model, HttpSession session) {
        AlumnoEntity nuevoAlumno = (AlumnoEntity) session.getAttribute("nuevoAlumno");
        Double resultadoMT = cuotasService.calcularMontoTotal(nuevoAlumno.getRut());
        Double resultado = cuotasService.calcularCuota(resultadoMT, cantidad);

        cuotasService.generarCuotas(nuevoAlumno, cantidad, resultado);

        model.addAttribute("resultado", resultado);
        return "paginaCuotas";
    }

    @GetMapping("/paginaMostrarPagos")
    public String mostrarPagosPorUsuario(Model model) {
        List<CuotaEntity> cuotas = cuotasService.mostrarCuotas();
        model.addAttribute("cuotas", cuotas);
        return "paginaMostrarPagos";
    }

    @GetMapping("/mostrarCuotasPorRut")
    public String mostrarCuotasPorRut(@RequestParam String rut, Model model) {
        List<CuotaEntity> cuotas = cuotasService.buscarPorRut(rut);
        model.addAttribute("cuotas", cuotas);
        return "paginaMostrarPagos";
    }
    @PostMapping("/pagar/{id_cuota}")
    public String pagarCuota(@PathVariable Long id_cuota) {
        cuotasService.pagarCuota(id_cuota);
        return "redirect:/paginaMostrarPagos"; // Cambia la URL a la que redirigir según tu configuración.
    }
}

