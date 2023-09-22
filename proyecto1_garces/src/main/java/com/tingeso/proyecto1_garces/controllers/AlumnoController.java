package com.tingeso.proyecto1_garces.controllers;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import com.tingeso.proyecto1_garces.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @GetMapping("/paginaAgregarAlumnos")
    public String mostrarPaginaAgregarAlumnos() {
        return "paginaAgregarAlumnos";
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

        return "paginaAgregarAlumnos";
    }
}
