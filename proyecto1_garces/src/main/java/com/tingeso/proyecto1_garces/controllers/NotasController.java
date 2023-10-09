package com.tingeso.proyecto1_garces.controllers;

import com.tingeso.proyecto1_garces.services.NotasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping
public class NotasController {

    @Autowired
    NotasServices notasServices;

        @PostMapping("/cargarArchivo")
        public String cargarArchivo(@RequestParam("archivo") MultipartFile archivo) throws IOException {
        notasServices.procesarArchivo(archivo);
        return "paginaEntregarNotas";
    }
}
