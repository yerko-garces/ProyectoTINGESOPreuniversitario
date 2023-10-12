package com.tingeso.proyecto1_garces.services;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import com.tingeso.proyecto1_garces.entities.NotaAlmunoEntity;
import com.tingeso.proyecto1_garces.repositories.AlumnoRepository;
import com.tingeso.proyecto1_garces.repositories.NotasRepository;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NotasServicesTest {

    @InjectMocks
    private NotasServices notasServices;

    @Mock
    private NotasRepository notasRepository;

    @Mock
    private AlumnoRepository alumnoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testProcesarArchivo() throws IOException {
        // Crear un archivo Excel de ejemplo con datos ficticios
        ByteArrayInputStream excelData = createSampleExcelData();

        // Crear un MultipartFile a partir del archivo Excel
        MultipartFile mockFile = new MockMultipartFile("archivo.xlsx", excelData);

        // Configurar un AlumnoEntity simulado para findByRut
        AlumnoEntity alumnoEntity = new AlumnoEntity();
        when(alumnoRepository.findByRut(Mockito.anyString())).thenReturn(alumnoEntity);

        when(notasRepository.save(Mockito.any(NotaAlmunoEntity.class))).thenReturn(new NotaAlmunoEntity());
        notasServices.procesarArchivo(mockFile);

        // Verificar que se llamó a save para cada NotaAlmunoEntity
        verify(notasRepository, times(1)).save(any(NotaAlmunoEntity.class));
    }

    private ByteArrayInputStream createSampleExcelData() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        Row row = sheet.createRow(0);

        // Datos de muestra
        String rut = "12.345.678-9";
        LocalDate fechaExamen = LocalDate.of(2023, 10, 12);
        int puntaje = 95;

        // Crear fila con datos de muestra
        row.createCell(0, CellType.STRING).setCellValue(rut);
        row.createCell(1, CellType.NUMERIC).setCellValue(Date.from(fechaExamen.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        row.createCell(2, CellType.NUMERIC).setCellValue(puntaje);

        // Escribir el libro de trabajo en un ByteArrayOutputStream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);

        return new ByteArrayInputStream(bos.toByteArray());
    }
}