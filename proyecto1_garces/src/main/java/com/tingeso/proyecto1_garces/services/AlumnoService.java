package com.tingeso.proyecto1_garces.services;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import com.tingeso.proyecto1_garces.repositories.AlumnoRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    public AlumnoEntity agregarAlumno(AlumnoEntity alumno){
        return alumnoRepository.save(alumno);
    }

}
