package com.tingeso.proyecto1_garces.repositories;


import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import com.tingeso.proyecto1_garces.entities.CuotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CuotaRepository extends JpaRepository<CuotaEntity, Long> {
    List<CuotaEntity> findByAlumnoRut(String rut);


}
