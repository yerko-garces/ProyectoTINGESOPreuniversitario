package com.tingeso.proyecto1_garces.repositories;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends CrudRepository<AlumnoEntity, Long> {
    @Query(value = "SELECT * FROM alumno WHERE id_alumno = (SELECT MAX(id_alumno) FROM alumno)", nativeQuery = true)
    AlumnoEntity idMayor();

}
