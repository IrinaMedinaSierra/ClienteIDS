package com.ceatformacion.clienteids.services;

import com.ceatformacion.clienteids.model.Clientes;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    public ByteArrayInputStream exportarEmpleados(List<Clientes> cliente) {
        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph("Lista de empleados", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(Chunk.NEWLINE);


            PdfPTable table = new PdfPTable(7); // 4 columnas
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 3, 3, 1,3,2,3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            table.addCell(new PdfPCell(new Phrase("ID", headFont)));
            table.addCell(new PdfPCell(new Phrase("Nombre", headFont)));
            table.addCell(new PdfPCell(new Phrase("Apellidos", headFont)));
            table.addCell(new PdfPCell(new Phrase("Edad", headFont)));
            table.addCell(new PdfPCell(new Phrase("Direccion", headFont)));
            table.addCell(new PdfPCell(new Phrase("Telefono", headFont)));
            table.addCell(new PdfPCell(new Phrase("Email", headFont)));

            for (Clientes cli : cliente) {
                table.addCell(String.valueOf(cli.getIdCliente()));
                table.addCell(cli.getNombreC());
                table.addCell(cli.getApellidoC());
                table.addCell(String.valueOf(cli.getEdad()));
                table.addCell(cli.getDireccion());
                table.addCell(cli.getTelefono());
                table.addCell(cli.getEmail());

            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}