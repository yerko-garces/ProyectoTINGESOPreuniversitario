package com.tingeso.proyecto1_garces.services;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import com.tingeso.proyecto1_garces.repositories.AlumnoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AlumnoServiceTest {

    @Mock
    AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoService alumnoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void agregarAlumno1() {
        //*************************************************TEST 1*****************************************************
        AlumnoEntity alumno1 = new AlumnoEntity();
        alumno1.setRut("11.111.111-1");
        alumno1.setApelleido_paterno("Albarado");
        alumno1.setApelleido_materno("Jimenez");
        alumno1.setSegundo_nombre("Jose");
        alumno1.setSegundo_nombre("Alan");
        alumno1.setNacimiento(LocalDate.ofEpochDay(05-11-1991));
        alumno1.setTipo_colegio_procedencia("Municipal");
        alumno1.setAño_egreso_colegio(2020);

        when(alumnoRepository.save(alumno1)).thenReturn(alumno1);
        AlumnoEntity resultado = alumnoService.agregarAlumno(alumno1);
        Mockito.verify(alumnoRepository, Mockito.times(1)).save(alumno1);
        assertEquals(alumno1, resultado);

    }

    @Test
    void agregarAlumno2(){

        //*************************************************TEST 2*****************************************************
        AlumnoEntity alumno2 = new AlumnoEntity();
        alumno2.setRut("22.222.222-2");
        alumno2.setApelleido_paterno("Gomez");
        alumno2.setApelleido_materno("Rodriguez");
        alumno2.setPrimer_nombre("Maria");
        alumno2.setSegundo_nombre("Luis");
        alumno2.setNacimiento(LocalDate.of(2000, 6, 15));
        alumno2.setTipo_colegio_procedencia("Privado");
        alumno2.setAño_egreso_colegio(2018);

        when(alumnoRepository.save(alumno2)).thenReturn(alumno2);
        AlumnoEntity resultado2 = alumnoService.agregarAlumno(alumno2);
        Mockito.verify(alumnoRepository, Mockito.times(1)).save(alumno2);
        assertEquals(alumno2, resultado2);

    }

    @Test
    void agregarAlumno3(){

        //*************************************************TEST 3*****************************************************
        AlumnoEntity alumno3 = new AlumnoEntity();
        alumno3.setRut("33.333.333-3");
        alumno3.setApelleido_paterno("Perez");
        alumno3.setApelleido_materno("Gutierrez");
        alumno3.setPrimer_nombre("Pedro");
        alumno3.setSegundo_nombre("Sofia");
        alumno3.setNacimiento(LocalDate.of(1999, 2, 20));
        alumno3.setTipo_colegio_procedencia("Particular");
        alumno3.setAño_egreso_colegio(2017);

        when(alumnoRepository.save(alumno3)).thenReturn(alumno3);
        AlumnoEntity resultado3 = alumnoService.agregarAlumno(alumno3);
        Mockito.verify(alumnoRepository, Mockito.times(1)).save(alumno3);
        assertEquals(alumno3, resultado3);
    }

}