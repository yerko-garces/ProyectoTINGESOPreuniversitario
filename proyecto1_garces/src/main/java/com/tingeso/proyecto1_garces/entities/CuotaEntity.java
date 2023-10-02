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

    private String tipo_pago;

    private Integer cantidad_cuotas;

    @OneToOne
    @JoinColumn(name = "rut")
    AlumnoEntity alumno;

}
