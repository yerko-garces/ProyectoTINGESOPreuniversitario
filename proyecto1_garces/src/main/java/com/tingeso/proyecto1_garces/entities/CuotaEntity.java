package com.tingeso.proyecto1_garces.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    private Boolean pagado;

    private Double pago_mensual;

    private Integer c_cuotas;

    private LocalDate f_pago;

    @ManyToOne
    @JoinColumn(name = "rut")
    AlumnoEntity alumno;

}
