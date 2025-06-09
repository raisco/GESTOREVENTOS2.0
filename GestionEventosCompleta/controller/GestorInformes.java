package controller;

import model.Evento;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GestorInformes {

    public void generarInformeParticipacion(List<Evento> eventos, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write("Informe de Participación\n");
            writer.write("========================\n");
            for (Evento evento : eventos) {
                writer.write("Evento: " + evento.getNombre() + "\n");
                writer.write("Fecha: " + evento.getFecha() + "\n");
                writer.write("Ubicación: " + evento.getUbicacion() + "\n");
                writer.write("Descripción: " + evento.getDescripcion() + "\n");
                writer.write("Asistentes:\n");
                for (var asistente : evento.getAsistentes()) {
                    writer.write(" - " + asistente.toString() + "\n");
                }
                writer.write("\n");
            }
            writer.write("Fin del Informe\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}