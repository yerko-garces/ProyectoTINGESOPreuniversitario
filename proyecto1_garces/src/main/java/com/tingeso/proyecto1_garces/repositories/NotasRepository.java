package com.tingeso.proyecto1_garces.repositories;

import com.tingeso.proyecto1_garces.entities.NotaAlmunoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotasRepository extends CrudRepository<NotaAlmunoEntity, Long> {
}

