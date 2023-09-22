package com.tingeso.proyecto1_garces.repositories;

import com.tingeso.proyecto1_garces.entities.AlumnoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends CrudRepository<AlumnoEntity, Long> {
}
