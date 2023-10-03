package com.tingeso.proyecto1_garces.services;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import com.tingeso.proyecto1_garces.entities.CuotaEntity;
import com.tingeso.proyecto1_garces.repositories.AlumnoRepository;
import com.tingeso.proyecto1_garces.repositories.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuotasService {

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    CuotaRepository cuotaRepository;


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

    public void generarCuotas(AlumnoEntity alumno, Integer cantidad, Double monto) {

        LocalDate fechaInicio = LocalDate.now().withMonth(3).withDayOfMonth(10);

        for (int i = 0; i < cantidad; i++) {
            CuotaEntity cuota = new CuotaEntity();
            cuota.setPagado(false);
            cuota.setC_cuotas(cantidad);
            cuota.setPago_mensual(monto);
            cuota.setAlumno(alumno);
            cuota.setF_pago(fechaInicio.plusMonths(i));
            cuotaRepository.save(cuota);
        }
    }

    public List<CuotaEntity> mostrarCuotas() {
        Iterable<CuotaEntity> cuotasIterable = cuotaRepository.findAll();
        List<CuotaEntity> cuotasList = new ArrayList<>();
        cuotasIterable.forEach(cuotasList::add);
        return cuotasList;
    }

    public List<CuotaEntity> buscarPorRut(String rut) {
        Iterable<CuotaEntity> cuotasIterable = cuotaRepository.findByAlumnoRut(rut);
        List<CuotaEntity> cuotasList = new ArrayList<>();
        cuotasIterable.forEach(cuotasList::add);
        return cuotasList;
    }

    public void pagarCuota(Long idCuota) {
        Optional<CuotaEntity> optionalCuota = cuotaRepository.findById(idCuota);
        if (optionalCuota.isPresent()) {
            CuotaEntity cuota = optionalCuota.get();
            cuota.setPagado(true);
            cuotaRepository.save(cuota);
        }
    }

}
