package controller;

import model.Recurso;
import model.Evento;
import model.Asistente;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class GestorEventos {
    private List<Evento> eventos;

    public GestorEventos() {
        this.eventos = new ArrayList<>();
    }

    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }

    public void editarEvento(String id, Evento eventoActualizado) {
        for (int i = 0; i < eventos.size(); i++) {
            if (eventos.get(i).getId().equals(id)) {
                eventos.set(i, eventoActualizado);
                return;
            }
        }
    }

    public List<Evento> listarEventos() {
        return eventos;
    }

    public Evento buscarEvento(String id) {
        for (Evento evento : eventos) {
            if (evento.getId().equals(id)) {
                return evento;
            }
        }
        return null;
    }

    public void eliminarEvento(String id) {
        eventos.removeIf(evento -> evento.getId().equals(id));
    }

    public void cargarEventosDesdeArchivo(String rutaArchivo) {
        eventos.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty() || linea.trim().startsWith("//")) continue;
                String[] partes = linea.split(";");
                if (partes.length >= 5) {
                    String id = partes[0];
                    String nombre = partes[1];
                    String fecha = partes[2];
                    String ubicacion = partes[3];
                    String descripcion = partes[4];
                    Evento evento = new Evento(id, nombre, fecha, ubicacion, descripcion);
                    eventos.add(evento);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarEventosEnArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Evento evento : eventos) {
                bw.write(evento.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarRecursosEnArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Evento evento : eventos) {
                for (Recurso recurso : evento.getRecursos()) {
                    bw.write(evento.getId() + ";" + recurso.toFileString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarRecursosDesdeArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 3) {
                    String idEvento = partes[0];
                    String tipo = partes[1];
                    String descripcion = partes[2];
                    Evento evento = buscarEvento(idEvento);
                    if (evento != null) {
                        evento.agregarRecurso(new Recurso(tipo, descripcion));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarAsistentesEnArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Evento evento : eventos) {
                for (Asistente asistente : evento.getAsistentes()) {
                    bw.write(evento.getId() + ";" + asistente.toFileString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}