package com.tingeso.proyecto1_garces.controllers;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import com.tingeso.proyecto1_garces.entities.CuotaEntity;
import com.tingeso.proyecto1_garces.repositories.AlumnoRepository;
import com.tingeso.proyecto1_garces.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @GetMapping("/paginaAgregarAlumnos")
    public String mostrarPaginaAgregarAlumnos() {
        return "paginaAgregarAlumnos";
    }

    @GetMapping("/paginaCuotas")
    public String mostrarPaginaCantidadCuotas() {
        return "paginaCuotas";
    }

    @PostMapping("/agregarAlumno")
    public String agregarAlumno(@RequestParam String rut, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno, @RequestParam String primerNombre, @RequestParam String segundoNombre,
                                 @RequestParam String nacimiento, @RequestParam String tipoColegioProcedencia, @RequestParam String nombreColegio, @RequestParam String anioEgresoColegio){
        AlumnoEntity nuevoAlumno = new AlumnoEntity();
        nuevoAlumno.setRut(rut);
        nuevoAlumno.setApelleido_paterno(apellidoPaterno);
        nuevoAlumno.setApelleido_materno(apellidoMaterno);
        nuevoAlumno.setPrimer_nombre(primerNombre);
        nuevoAlumno.setSegundo_nombre(segundoNombre);
        nuevoAlumno.setNacimiento(nacimiento);
        nuevoAlumno.setTipo_colegio_procedencia(tipoColegioProcedencia);
        nuevoAlumno.setNombre_colegio(nombreColegio);
        nuevoAlumno.setAño_egreso_colegio(anioEgresoColegio);

        alumnoService.agregarAlumno(nuevoAlumno);

        return "index";
    }

    @GetMapping("/posibilidadDeCuotas")
    @ResponseBody
    public String posibilidadDeCuotas(){
        String cantidadCuotas = alumnoService.cantidadCuotas();
        return "La cantidad maxima de cuotas a las que puede optar son " + cantidadCuotas;
    }

    @PostMapping("/calcularMontoCuotas")
    public String calcularCuota(@RequestParam("cantidad") Integer cantidad, Model model) {
        Double resultadoMT = alumnoService.calcularMontoTotal();
        Double resultado = alumnoService.calcularCuota(resultadoMT, cantidad);
        model.addAttribute("resultado", resultado);
        return "paginaCuotas"; // Devuelve el nombre de la página HTML que mostrará el resultado
    }
}
