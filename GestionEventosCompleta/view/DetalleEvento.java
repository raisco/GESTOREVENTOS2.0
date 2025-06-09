package view;

import javax.swing.*;
import model.Evento;
import model.Asistente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DetalleEvento extends JFrame {
    private Evento evento;
    private JTextArea detallesArea;
    private JButton inscribirButton;

    public DetalleEvento(Evento evento) {
        this.evento = evento;
        setTitle("Detalles del Evento");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        detallesArea = new JTextArea();
        detallesArea.setEditable(false);
        detallesArea.setBackground(Color.BLACK);
        detallesArea.setForeground(Color.WHITE);
        detallesArea.setText(obtenerDetallesEvento());
        add(new JScrollPane(detallesArea), BorderLayout.CENTER);

        inscribirButton = new JButton("Inscribir Asistente");
        inscribirButton.setBackground(Color.GRAY);
        inscribirButton.setForeground(Color.WHITE);
        inscribirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inscribirAsistente();
            }
        });
        add(inscribirButton, BorderLayout.SOUTH);
    }

    private String obtenerDetallesEvento() {
        StringBuilder detalles = new StringBuilder();
        detalles.append("ID: ").append(evento.getId()).append("\n");
        detalles.append("Nombre: ").append(evento.getNombre()).append("\n");
        detalles.append("Fecha: ").append(evento.getFecha()).append("\n");
        detalles.append("Ubicación: ").append(evento.getUbicacion()).append("\n");
        detalles.append("Descripción: ").append(evento.getDescripcion()).append("\n");
        detalles.append("Asistentes: ").append(evento.getAsistentes().size()).append("\n");
        detalles.append("Recursos: ").append(evento.getRecursos().size()).append("\n");
        return detalles.toString();
    }

    private void inscribirAsistente() {
        String dni = JOptionPane.showInputDialog(this, "Ingrese DNI del asistente:");
        String nombre = JOptionPane.showInputDialog(this, "Ingrese nombre del asistente:");
        String email = JOptionPane.showInputDialog(this, "Ingrese email del asistente:");

        if (dni != null && nombre != null && email != null) {
            Asistente asistente = new Asistente(dni, nombre, email);
            evento.agregarAsistente(asistente);
            guardarAsistenteEnArchivo(asistente);
            JOptionPane.showMessageDialog(this, "Asistente inscrito exitosamente.");
        }
    }

    private void guardarAsistenteEnArchivo(Asistente asistente) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/asistentes.txt", true))) {
            bw.write(evento.getId() + ";" + asistente.toFileString());
            bw.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}