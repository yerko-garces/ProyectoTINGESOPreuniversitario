package com.tingeso.proyecto1_garces.services;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import com.tingeso.proyecto1_garces.entities.CuotaEntity;
import com.tingeso.proyecto1_garces.repositories.AlumnoRepository;
import com.tingeso.proyecto1_garces.repositories.CuotaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CuotasServiceTest {

    @Mock
    AlumnoRepository alumnoRepository;

    @Mock
    CuotaRepository cuotaRepository;

    @InjectMocks
    private CuotasService cuotasService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calcularMontoTotal1() {

        //*************************************************TEST 1*****************************************************
        AlumnoEntity alumno1 = new AlumnoEntity();
        alumno1.setTipo_colegio_procedencia("Municipal");
        alumno1.setAño_egreso_colegio(2020);


        when(alumnoRepository.findByRut(anyString())).thenReturn(alumno1);
        Double resultado = cuotasService.calcularMontoTotal("11.111.111-1");
        assertEquals(1152000.0, resultado, 0);

    }

    @Test
    void calcularMontoTotal2() {

        //*************************************************TEST 2*****************************************************
        AlumnoEntity alumno2 = new AlumnoEntity();
        alumno2.setTipo_colegio_procedencia("Privado");
        alumno2.setAño_egreso_colegio(2023);


        when(alumnoRepository.findByRut(anyString())).thenReturn(alumno2);
        Double resultado2 = cuotasService.calcularMontoTotal("22.222.222-2");
        assertEquals(1275000.0, resultado2, 0);

    }

    @Test
    void calcularMontoTotal3(){

        //*************************************************TEST 3*****************************************************
        AlumnoEntity alumno3 = new AlumnoEntity();
        alumno3.setTipo_colegio_procedencia("Subvencionado");
        alumno3.setAño_egreso_colegio(2022);


        when(alumnoRepository.findByRut(anyString())).thenReturn(alumno3);
        Double resultado3 = cuotasService.calcularMontoTotal("33.333.333-3");
        assertEquals(1242000.0, resultado3, 0);
    }

    @Test
    void cantidadCuotas1() {

        //*************************************************TEST 1*****************************************************
        AlumnoEntity alumno1 = new AlumnoEntity();
        alumno1.setTipo_colegio_procedencia("Municipal");

        when(alumnoRepository.findByRut(anyString())).thenReturn(alumno1);
        String resultado = cuotasService.cantidadCuotas("11.111.111-1");
        assertEquals("diez", resultado);


    }

    @Test
    void cantidadCuotas2(){

        //*************************************************TEST 2*****************************************************
        AlumnoEntity alumno2 = new AlumnoEntity();
        alumno2.setTipo_colegio_procedencia("Subvencionado");

        when(alumnoRepository.findByRut(anyString())).thenReturn(alumno2);
        String resultado2 = cuotasService.cantidadCuotas("22.222.222-2");
        assertEquals("siete", resultado2);

    }

    @Test
    void cantidadCuotas3(){

        //*************************************************TEST 3*****************************************************
        AlumnoEntity alumno3 = new AlumnoEntity();
        alumno3.setTipo_colegio_procedencia("Privado");

        when(alumnoRepository.findByRut(anyString())).thenReturn(alumno3);
        String resultado3 = cuotasService.cantidadCuotas("33.333.333-3");
        assertEquals("cuatro", resultado3);
    }

    @Test
    void calcularCuota1() {

        //*************************************************TEST 1*****************************************************
        Double total1 = 1500000.0;
        Integer cantidadCuotas1 = 10;


        Double resultado1 = cuotasService.calcularCuota(total1, cantidadCuotas1);
        assertEquals(150000.0, resultado1, 0);

    }

    @Test
    void calcularCuota2(){

        //*************************************************TEST 2*****************************************************
        Double total2 = 1152000.0;
        Integer cantidadCuotas2 = 7;


        Double resultado2 = cuotasService.calcularCuota(total2, cantidadCuotas2);
        assertEquals(164571.42857142858, resultado2, 0);

    }

    @Test
    void calcularCuota3(){

        //*************************************************TEST 3*****************************************************
        Double total3 = 1300000.0;
        Integer cantidadCuotas3 = 5;


        Double resultado3 = cuotasService.calcularCuota(total3, cantidadCuotas3);
        assertEquals(260000.0, resultado3, 0);
    }

    @Test
    void generarCuotas1() {

        //*************************************************TEST 1*****************************************************
        AlumnoEntity alumno1 = new AlumnoEntity();
        Integer cantidad1 = 4;
        Double monto1 = 1000000.0;

        when(cuotaRepository.save(any(CuotaEntity.class))).then(returnsFirstArg());
        cuotasService.generarCuotas(alumno1, cantidad1, monto1);
        verify(cuotaRepository, times(cantidad1)).save(any(CuotaEntity.class));

    }

    @Test
    void generarCuotas2() {

        //*************************************************TEST 2*****************************************************
        AlumnoEntity alumno2 = new AlumnoEntity();
        Integer cantidad2 = 10;
        Double monto2 = 1200000.0;

        when(cuotaRepository.save(any(CuotaEntity.class))).then(returnsFirstArg());
        cuotasService.generarCuotas(alumno2, cantidad2, monto2);
        verify(cuotaRepository, times(cantidad2)).save(any(CuotaEntity.class));

    }

    @Test
    void generarCuotas3() {

        //*************************************************TEST 3*****************************************************
        AlumnoEntity alumno3 = new AlumnoEntity();
        Integer cantidad3 = 7;
        Double monto3 = 1250000.0;

        when(cuotaRepository.save(any(CuotaEntity.class))).then(returnsFirstArg());
        cuotasService.generarCuotas(alumno3, cantidad3, monto3);
        verify(cuotaRepository, times(cantidad3)).save(any(CuotaEntity.class));

    }

    @Test
    void mostrarCuotas1() {

        //*************************************************TEST 1*****************************************************
        CuotaEntity cuota1 = new CuotaEntity();
        CuotaEntity cuota2 = new CuotaEntity();
        List<CuotaEntity> cuotasSimuladas = Arrays.asList(cuota1, cuota2);

        when(cuotaRepository.findAll()).thenReturn(cuotasSimuladas);
        List<CuotaEntity> resultado = cuotasService.mostrarCuotas();
        assertEquals(cuotasSimuladas, resultado);
    }

    @Test
    void mostrarCuotas2() {

        //*************************************************TEST 2*****************************************************
        CuotaEntity cuota3 = new CuotaEntity();
        CuotaEntity cuota4 = new CuotaEntity();
        CuotaEntity cuota5 = new CuotaEntity();
        List<CuotaEntity> cuotasSimuladas = Arrays.asList(cuota3, cuota4, cuota5);

        when(cuotaRepository.findAll()).thenReturn(cuotasSimuladas);
        List<CuotaEntity> resultado = cuotasService.mostrarCuotas();
        assertEquals(cuotasSimuladas, resultado);
    }

    @Test
    void mostrarCuotas3() {

        //*************************************************TEST 3*****************************************************
        CuotaEntity cuota6 = new CuotaEntity();
        List<CuotaEntity> cuotasSimuladas = Arrays.asList(cuota6);

        when(cuotaRepository.findAll()).thenReturn(cuotasSimuladas);
        List<CuotaEntity> resultado = cuotasService.mostrarCuotas();
        assertEquals(cuotasSimuladas, resultado);
    }

    @Test
    void buscarPorRut1() {

        //*************************************************TEST 1*****************************************************
        String rut = "11.111.111-1";
        CuotaEntity cuota1 = new CuotaEntity();
        CuotaEntity cuota2 = new CuotaEntity();
        List<CuotaEntity> cuotasSimuladas = Arrays.asList(cuota1, cuota2);

        when(cuotaRepository.findByAlumnoRut(rut)).thenReturn(cuotasSimuladas);
        List<CuotaEntity> resultado = cuotasService.buscarPorRut(rut);
        assertEquals(cuotasSimuladas, resultado);
    }

    @Test
    void buscarPorRut2() {

        //*************************************************TEST 2*****************************************************
        String rut2 = "22.222.222-2";
        CuotaEntity cuota1 = new CuotaEntity();
        CuotaEntity cuota3 = new CuotaEntity();
        List<CuotaEntity> cuotasSimuladas2 = Arrays.asList(cuota1, cuota3);

        when(cuotaRepository.findByAlumnoRut(rut2)).thenReturn(cuotasSimuladas2);
        List<CuotaEntity> resultado = cuotasService.buscarPorRut(rut2);
        assertEquals(cuotasSimuladas2, resultado);
    }

    @Test
    void buscarPorRut3() {

        //*************************************************TEST 3*****************************************************
        String rut = "33.333.333-3";
        CuotaEntity cuota4 = new CuotaEntity();
        CuotaEntity cuota7 = new CuotaEntity();
        CuotaEntity cuota10 = new CuotaEntity();
        List<CuotaEntity> cuotasSimuladas3 = Arrays.asList(cuota4, cuota7, cuota10);

        when(cuotaRepository.findByAlumnoRut(rut)).thenReturn(cuotasSimuladas3);
        List<CuotaEntity> resultado3 = cuotasService.buscarPorRut(rut);
        assertEquals(cuotasSimuladas3, resultado3);
    }

    @Test
    void pagarCuota1() {

        //*************************************************TEST 1*****************************************************
        Long idCuota = 1L;
        CuotaEntity cuotaSimulada1 = new CuotaEntity();
        cuotaSimulada1.setId_cuota(idCuota);
        cuotaSimulada1.setPagado(false);

        when(cuotaRepository.findById(idCuota)).thenReturn(Optional.of(cuotaSimulada1));
        cuotasService.pagarCuota(idCuota);
        assertTrue(cuotaSimulada1.getPagado());
    }

    @Test
    void pagarCuota2() {

        //*************************************************TEST 2*****************************************************
        Long idCuota = 10L;
        CuotaEntity cuotaSimulada2 = new CuotaEntity();
        cuotaSimulada2.setId_cuota(idCuota);
        cuotaSimulada2.setPagado(false);

        when(cuotaRepository.findById(idCuota)).thenReturn(Optional.of(cuotaSimulada2));
        cuotasService.pagarCuota(idCuota);
        assertTrue(cuotaSimulada2.getPagado());
    }

    @Test
    void pagarCuota3() {

        //*************************************************TEST 3*****************************************************
        Long idCuota = 7L;
        CuotaEntity cuotaSimulada3 = new CuotaEntity();
        cuotaSimulada3.setId_cuota(idCuota);
        cuotaSimulada3.setPagado(false);

        when(cuotaRepository.findById(idCuota)).thenReturn(Optional.of(cuotaSimulada3));
        cuotasService.pagarCuota(idCuota);
        assertTrue(cuotaSimulada3.getPagado());
    }

    @Test
    void rutPorId1() {

        //*************************************************TEST 1*****************************************************
        Long idCuota = 1L;
        String rutAlumno = "11.111.111-1";
        CuotaEntity cuotaSimulada1 = new CuotaEntity();
        cuotaSimulada1.setId_cuota(idCuota);
        AlumnoEntity alumnoSimulado1 = new AlumnoEntity();
        alumnoSimulado1.setRut(rutAlumno);

        cuotaSimulada1.setAlumno(alumnoSimulado1);
        when(cuotaRepository.findById(idCuota)).thenReturn(Optional.of(cuotaSimulada1));
        String resultadoRut = cuotasService.rutPorId(idCuota);
        assertEquals(rutAlumno, resultadoRut);
    }

    @Test
    void rutPorId2() {

        //*************************************************TEST 2*****************************************************
        Long idCuota = 15L;
        String rutAlumno = "22.222.222-2";
        CuotaEntity cuotaSimulada2 = new CuotaEntity();
        cuotaSimulada2.setId_cuota(idCuota);
        AlumnoEntity alumnoSimulado2 = new AlumnoEntity();
        alumnoSimulado2.setRut(rutAlumno);

        cuotaSimulada2.setAlumno(alumnoSimulado2);
        when(cuotaRepository.findById(idCuota)).thenReturn(Optional.of(cuotaSimulada2));
        String resultadoRut = cuotasService.rutPorId(idCuota);
        assertEquals(rutAlumno, resultadoRut);
    }

    @Test
    void rutPorId3() {

        //*************************************************TEST 3*****************************************************
        Long idCuota = 7L;
        String rutAlumno = "33.333.333-3";
        CuotaEntity cuotaSimulada3 = new CuotaEntity();
        cuotaSimulada3.setId_cuota(idCuota);
        AlumnoEntity alumnoSimulado3 = new AlumnoEntity();
        alumnoSimulado3.setRut(rutAlumno);

        cuotaSimulada3.setAlumno(alumnoSimulado3);
        when(cuotaRepository.findById(idCuota)).thenReturn(Optional.of(cuotaSimulada3));
        String resultadoRut = cuotasService.rutPorId(idCuota);
        assertEquals(rutAlumno, resultadoRut);
    }

    @Test
    void alumnosSC(){
        AlumnoEntity alumnoSimulado = new AlumnoEntity();
        alumnoSimulado.setRut("33.333.333-3");
        when(alumnoRepository.findAlumnoSinCuota()).thenReturn(alumnoSimulado);
        AlumnoEntity resultado = cuotasService.alumnosSC();
        assertEquals(alumnoSimulado, resultado);
    }
}