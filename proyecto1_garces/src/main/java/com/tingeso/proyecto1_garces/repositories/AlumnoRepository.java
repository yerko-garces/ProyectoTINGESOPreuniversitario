package com.tingeso.proyecto1_garces.repositories;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends CrudRepository<AlumnoEntity, String> {
    @Query(value = "SELECT * FROM alumno WHERE id_alumno = (SELECT MAX(id_alumno) FROM alumno)", nativeQuery = true)
    AlumnoEntity idMayor();

    @Query("SELECT a FROM AlumnoEntity a WHERE a.rut = :rut")
    AlumnoEntity findByRut(@Param("rut") String rut);
}
