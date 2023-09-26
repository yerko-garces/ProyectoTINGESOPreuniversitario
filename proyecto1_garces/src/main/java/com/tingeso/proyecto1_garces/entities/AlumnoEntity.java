package com.tingeso.proyecto1_garces.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alumno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_alumno;

    private String rut;

    private String apelleido_paterno;

    private String apelleido_materno;

    private String primer_nombre;

    private String segundo_nombre;

    private String nacimiento;

    private String tipo_colegio_procedencia;

    private String nombre_colegio;

    private String año_egreso_colegio;

}
