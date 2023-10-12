package com.tingeso.proyecto1_garces.services;

import com.tingeso.proyecto1_garces.entities.NotaAlmunoEntity;
import com.tingeso.proyecto1_garces.repositories.AlumnoRepository;
import com.tingeso.proyecto1_garces.repositories.NotasRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class NotasServices {

    @Autowired
    private NotasRepository notasRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    public void procesarArchivo(MultipartFile archivo) throws IOException {
        InputStream inputStream = archivo.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);

            if (row != null) {
                NotaAlmunoEntity notaAlumno = new NotaAlmunoEntity();

                Cell rutCell = row.getCell(0);
                if (rutCell == null || rutCell.getCellType() != CellType.STRING) {
                    break;
                }

                String rutEstudiante = rutCell.getStringCellValue();
                notaAlumno.setAlumno(alumnoRepository.findByRut(rutEstudiante));

                Cell fechaCell = row.getCell(1);
                LocalDate fechaExamen = null;

                if (fechaCell != null && fechaCell.getCellType() == CellType.NUMERIC) {
                    fechaExamen = fechaCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }

                notaAlumno.setFecha_examen(fechaExamen);

                Cell puntajeCell = row.getCell(2);
                int puntaje = 0;

                if (puntajeCell != null && puntajeCell.getCellType() == CellType.NUMERIC) {
                    puntaje = (int) puntajeCell.getNumericCellValue();
                }

                notaAlumno.setPuntaje(puntaje);

                // Agrega un registro de depuración para verificar que se llama al método save
                System.out.println("Llamando a save en notasRepository");
                notasRepository.save(notaAlumno);
            }
        }
        workbook.close();
    }
}