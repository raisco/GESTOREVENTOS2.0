package model;

import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String id;
    private String nombre;
    private String fecha;
    private String ubicacion;
    private String descripcion;
    private List<Asistente> asistentes;
    private List<Recurso> recursos;

    public Evento(String id, String nombre, String fecha, String ubicacion, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.asistentes = new ArrayList<>();
        this.recursos = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getFecha() { return fecha; }
    public String getUbicacion() { return ubicacion; }
    public String getDescripcion() { return descripcion; }
    public List<Asistente> getAsistentes() { return asistentes; }
    public List<Recurso> getRecursos() { return recursos; }

    public void agregarAsistente(Asistente a) {
        asistentes.add(a);
    }

    public void agregarRecurso(Recurso r) {
        recursos.add(r);
    }

    // Devuelve una línea para guardar el evento en eventos.txt
    public String toFileString() {
        return id + ";" + nombre + ";" + fecha + ";" + ubicacion + ";" + descripcion;
    }

    @Override
    public String toString() {
        return nombre + " - " + fecha;
    }
}