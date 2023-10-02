package com.tingeso.proyecto1_garces.services;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import com.tingeso.proyecto1_garces.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CuotasService {

    @Autowired
    AlumnoRepository alumnoRepository;

    public Double calcularMontoTotal(String rut){

        AlumnoEntity alumno = new AlumnoEntity();
        alumno = alumnoRepository.findByRut(rut);

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

        Integer aniosDeEgreso = LocalDateTime.now().getYear() - alumno.getAño_egreso_colegio();

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

    public String cantidadCuotas(String rut){
        AlumnoEntity alumno = alumnoRepository.findByRut(rut);
        switch (alumno.getTipo_colegio_procedencia()){
            case "Municipal":
                return "diez";

            case "Subvencionado":
                return "siete";

            case "Privado":
                return "cuatro";
        }
        return null;
    }

    public Double calcularCuota(Double total, Integer cantidadCuotas){
        Double cuotas  = total / cantidadCuotas;
        return cuotas;
    }

}
