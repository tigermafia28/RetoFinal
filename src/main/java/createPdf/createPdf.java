/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package createPdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import leerXML.clases.Plus;
import static leerXML.enums.Contrato.INDEFINIDO;
import nomina.Nomina;
import utilidades.Datos;

/**
 *
 * @author auron
 */
public class createPdf {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {

        Datos datos = new Datos();
        datos.diaInicio = 1;
        datos.diaFinal = 30;
        datos.nHorasDiarias = 8;
        datos.nHorasExtra = 0;
        datos.salarioAnual = 26400.00f;
        datos.nPagasExtra = 3;
        datos.jornadaAnual = 1790;
        datos.irpf = 12f;
        datos.porcentajeHorasExtra = 50f;
        datos.porcentajeAtep = 1.6f;
        datos.prorrateado = true;
        datos.contrato = INDEFINIDO;
        datos.pluses = new Plus[1024];

        Nomina nomina = new Nomina(datos);

        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("NominaPrueba1.pdf"));

        document.open();
        
        Font fontTituloNom = FontFactory.getFont(FontFactory.COURIER, 24, Font.BOLD, BaseColor.BLACK);
        Font fontTitulo = FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD, BaseColor.BLACK);
        Font fontTotales = FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD, BaseColor.BLACK);
        Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
        Paragraph parrafo = new Paragraph();
        Chunk titulo = new Chunk("NÓMINA", fontTituloNom);
        Chunk fecha = new Chunk("\nFecha: ", font);
        Chunk devengos = new Chunk("\nDEVENGOS", fontTitulo);
        Chunk percepciones1 = new Chunk("\nPercepciones salariales: ", fontTitulo);
        Chunk percepciones2 = new Chunk("\nPercepciones no salariales: ", fontTitulo);
        Chunk deducciones = new Chunk("\nDEDUCCIONES", fontTitulo);
        Chunk datosemp = new Chunk("\nNombre de la empresa: \nDomicilio: \nCIF: ", font);
        Chunk datostra = new Chunk("\nNombre del trabajador: \nNúmero SS: \nNIF: ", font);
        Chunk salarioBase = new Chunk("\nSalario base: "+String.valueOf(nomina.salarioBase), font);
        Chunk horasExtra = new Chunk("Horas extra: "+String.valueOf(nomina.horasExtra), font);
        Chunk plusesSalariales = new Chunk("Pluses salariales: "+String.valueOf(nomina.plusesSalariales), font);
        Chunk plusesNoSalariales = new Chunk("\nPluses no salariales: "+String.valueOf(nomina.plusesNoSalariales), font);
        Chunk totalDevengado = new Chunk("\nTotal devengado: "+String.valueOf(nomina.totalDevengado), fontTotales);
        Chunk totalAportaciones = new Chunk("\nTotal aportaciones: "+String.valueOf(nomina.totalAportaciones), fontTotales);
        Chunk aportaciones = new Chunk("\nContingencias comunes: "+String.valueOf(nomina.contingenciasComunes)+
                "\nDesempleo: "+String.valueOf(nomina.desempleo)+
                "\nFP: "+String.valueOf(nomina.fp)+
                "\nAportaciones horas extra: "+String.valueOf(nomina.aportacionesHorasExtra), font);
        Chunk IRPF = new Chunk("\nIRPF: "+String.valueOf(nomina.irpf), font);
        Chunk totalADeducir = new Chunk("\nTotal a deducir: "+String.valueOf(nomina.totalADeducir), fontTotales);
        Chunk neto = new Chunk("\nNeto: "+String.valueOf(nomina.neto), fontTotales);

        
        document.add(titulo);
        document.add(parrafo);
        document.add(fecha);
        document.add(parrafo);
        
        document.add(datosemp);
        document.add(parrafo);       
        document.add(datostra);
        document.add(parrafo);
        
        document.add(devengos);
        document.add(parrafo);
        document.add(percepciones1);
        document.add(parrafo);
        document.add(salarioBase);
        document.add(parrafo);
        document.add(horasExtra);
        document.add(parrafo);
        document.add(plusesSalariales);
        document.add(parrafo);
        
        document.add(percepciones2); 
        document.add(parrafo);
        document.add(plusesNoSalariales);
        document.add(parrafo);
        document.add(totalDevengado);
        document.add(parrafo);
        
        document.add(deducciones);
        document.add(parrafo);
        document.add(aportaciones);
        document.add(parrafo);
        document.add(totalAportaciones);
        document.add(parrafo);
        document.add(IRPF);
        document.add(parrafo);
        document.add(totalADeducir);
        document.add(parrafo);
        document.add(neto);
        
        document.close();

    }

}
