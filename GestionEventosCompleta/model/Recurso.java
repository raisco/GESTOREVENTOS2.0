package model;

public class Recurso {
    private String tipo;
    private String descripcion;

    public Recurso(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }

    public String toFileString() {
        return tipo + ";" + descripcion;
    }

    @Override
    public String toString() {
        return tipo + ": " + descripcion;
    }
}