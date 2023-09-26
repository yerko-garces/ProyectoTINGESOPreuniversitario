package com.tingeso.proyecto1_garces.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cuota")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_cuota;

    private Double marzo;

    private Double abril;

    private Double mayo;

    private Double junio;

    private Double julio;

    private Double agosto;

    private Double septiembre;

    private Double octubre;

    private Double noviembre;

    private Double diciembre;

    @OneToOne
    @JoinColumn(name = "id_alumno")
    AlumnoEntity alumno;

}
