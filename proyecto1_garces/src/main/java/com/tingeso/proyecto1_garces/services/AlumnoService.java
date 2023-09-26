package com.tingeso.proyecto1_garces.services;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import com.tingeso.proyecto1_garces.repositories.AlumnoRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    public AlumnoEntity agregarAlumno(AlumnoEntity alumno){
        return alumnoRepository.save(alumno);
    }

    public Double calcularMontoTotal(AlumnoEntity alumno){

        Double precioInicial = Double.valueOf(1500000);
        Integer auxiliar = 0;

       switch (alumno.getTipo_colegio_procedencia()){
           case "Municipal":
               precioInicial = precioInicial - (1500000 * 0.2);
               auxiliar = 1;
               break;

           case "Subvencionado":
               precioInicial = precioInicial - (1500000 * 0.1);
               auxiliar = 2;
               break;

           case "Privado":
               auxiliar = 3;
               break;
       }

       Integer aniosDeEgreso = 2023 - Integer.parseInt(alumno.getAño_egreso_colegio());

       if(aniosDeEgreso == 0){
           precioInicial = precioInicial - (precioInicial * 0.15);
       }

       if(aniosDeEgreso == 1 || aniosDeEgreso == 2){
           precioInicial = precioInicial - (precioInicial * 0.08);
       }

       if(aniosDeEgreso == 3 || aniosDeEgreso == 4){
           precioInicial = precioInicial - (precioInicial * 0.04);
       }

       return precioInicial;
    }

    public Double calcularCuota(Double total, Integer cantidadCuotas){
        Double cuotas  = total / cantidadCuotas;
        return cuotas;
    }

}
