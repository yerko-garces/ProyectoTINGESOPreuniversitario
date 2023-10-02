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
    private NotasRepository notasRepository; // Reemplaza con tu repositorio real

    @Autowired
    private AlumnoRepository alumnoRepository;

    public void procesarArchivo(MultipartFile archivo) throws IOException {
        // Procesa el archivo Excel y guarda los datos en la base de datos
        // Usa una biblioteca como Apache POI para trabajar con archivos Excel
        InputStream inputStream = archivo.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Inicia desde la segunda fila (fila 1 en índices de base 0)
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);

            if (row != null) {
                // Lee las celdas y guarda los datos en tu entidad y luego guárdala en la base de datos
                // Ejemplo:
                NotaAlmunoEntity notaAlumno = new NotaAlmunoEntity();

                // Asigna el valor de la primera columna (RUT del estudiante) al atributo alumno
                Cell rutCell = row.getCell(0); // Celda del RUT
                if (rutCell == null || rutCell.getCellType() != CellType.STRING) {
                    // Si la celda está vacía o no es de tipo STRING, se asume que no hay más datos
                    break; // Sale del bucle for
                }

                String rutEstudiante = rutCell.getStringCellValue();
                notaAlumno.setAlumno(alumnoRepository.findByRut(rutEstudiante));

                // Asigna el valor de la segunda columna (Fecha del examen) al atributo fecha_examen
                Cell fechaCell = row.getCell(1); // Celda de la fecha
                LocalDate fechaExamen = null; // Inicializamos con null

                if (fechaCell != null && fechaCell.getCellType() == CellType.NUMERIC) {
                    fechaExamen = fechaCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }

                notaAlumno.setFecha_examen(fechaExamen);

                // Asigna el valor de la tercera columna (Puntaje obtenido) al atributo puntaje
                Cell puntajeCell = row.getCell(2); // Celda del puntaje
                int puntaje = 0; // Valor por defecto en caso de que no sea numérico

                if (puntajeCell != null && puntajeCell.getCellType() == CellType.NUMERIC) {
                    puntaje = (int) puntajeCell.getNumericCellValue();
                }

                notaAlumno.setPuntaje(puntaje);

                notasRepository.save(notaAlumno);
            }
        }
        workbook.close();
    }
}

