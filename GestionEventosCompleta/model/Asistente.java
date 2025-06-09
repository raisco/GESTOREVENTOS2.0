package model;

public class Asistente {
    private String dni;
    private String nombre;
    private String email;

    public Asistente(String dni, String nombre, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }

    public String toFileString() {
        return dni + ";" + nombre + ";" + email;
    }

    @Override
    public String toString() {
        return nombre + " (" + email + ")";
    }
}