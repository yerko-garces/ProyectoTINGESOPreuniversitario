package com.tingeso.proyecto1_garces.services;

import com.tingeso.proyecto1_garces.repositories.AlumnoRepository;
import com.tingeso.proyecto1_garces.repositories.CuotaRepository;
import com.tingeso.proyecto1_garces.repositories.NotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumenService {

    @Autowired
    AlumnoRepository alumnoRepository;

    @Autowired
    CuotaRepository cuotaRepository;

    @Autowired
    NotasRepository notasRepository;


}
