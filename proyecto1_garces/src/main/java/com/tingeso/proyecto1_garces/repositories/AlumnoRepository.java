package com.tingeso.proyecto1_garces.repositories;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends CrudRepository<AlumnoEntity, String> {
    @Query("SELECT a FROM AlumnoEntity a WHERE a.rut = :rut")
    AlumnoEntity findByRut(@Param("rut") String rut);
    @Query("SELECT a FROM AlumnoEntity a WHERE a.rut NOT IN (SELECT DISTINCT c.alumno.rut FROM CuotaEntity c)")
    AlumnoEntity findAlumnoSinCuota();
}
