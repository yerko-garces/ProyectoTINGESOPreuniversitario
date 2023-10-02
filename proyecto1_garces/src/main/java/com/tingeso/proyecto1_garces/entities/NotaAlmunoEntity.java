package com.tingeso.proyecto1_garces.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "nota_alumno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaAlmunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_nota_alumno;

    private LocalDate fecha_examen;

    private Integer puntaje;

    @ManyToOne
    @JoinColumn(name = "rut")
    private AlumnoEntity alumno;
}
