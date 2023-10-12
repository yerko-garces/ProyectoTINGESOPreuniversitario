package com.tingeso.proyecto1_garces.repositories;

import com.tingeso.proyecto1_garces.entities.NotaAlmunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotasRepository extends JpaRepository<NotaAlmunoEntity, Long> {
}

