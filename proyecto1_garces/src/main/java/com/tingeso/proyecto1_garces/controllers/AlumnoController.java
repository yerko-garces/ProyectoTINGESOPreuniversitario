package com.tingeso.proyecto1_garces.controllers;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import com.tingeso.proyecto1_garces.entities.CuotaEntity;
import com.tingeso.proyecto1_garces.repositories.AlumnoRepository;
import com.tingeso.proyecto1_garces.services.AlumnoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @PostMapping("/agregarAlumno")
    public String agregarAlumno(@RequestParam String rut, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno, @RequestParam String primerNombre, @RequestParam String segundoNombre,
                                @RequestParam LocalDate nacimiento, @RequestParam String tipoColegioProcedencia, @RequestParam String nombreColegio, @RequestParam Integer anioEgresoColegio, RedirectAttributes redirectAttributes, HttpSession session){
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

        session.setAttribute("rutEstudiante", rut);

        redirectAttributes.addFlashAttribute("mostrarPosibilidadCuotas", true);
        return "redirect:/paginaCuotas";
    }

}
