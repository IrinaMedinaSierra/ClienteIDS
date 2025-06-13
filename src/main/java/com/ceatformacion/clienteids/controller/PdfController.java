package com.ceatformacion.clienteids.controller;
import com.ceatformacion.clienteids.model.Clientes;
import com.ceatformacion.clienteids.repository.ClienteRepository;
import com.ceatformacion.clienteids.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
public class PdfController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportarPDF() {
        List<Clientes> clientes = clienteRepository.findAll();
        ByteArrayInputStream pdfStream = pdfService.exportarEmpleados(clientes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=clientes.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfStream.readAllBytes());
    }
}


