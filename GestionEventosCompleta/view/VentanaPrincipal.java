package view;

import javax.swing.*;
import java.awt.*;
import controller.GestorEventos;
import model.Evento;
import model.Asistente;
import model.Recurso;

public class VentanaPrincipal extends JFrame {
    private JList<String> listaEventos;
    private DefaultListModel<String> modeloLista;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnVerDetalles;
    private JButton btnEliminarEvento;
    private JButton btnEliminarAsistente;
    private JButton btnAgregarRecurso;
    private JButton btnEliminarRecurso;
    private GestorEventos gestor;

    public VentanaPrincipal() {
        setTitle("Gestor de Eventos");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        gestor = new GestorEventos();
        gestor.cargarEventosDesdeArchivo("data/eventos.txt");
        gestor.cargarRecursosDesdeArchivo("data/recursos.txt");

        modeloLista = new DefaultListModel<>();
        listaEventos = new JList<>(modeloLista);
        listaEventos.setBackground(Color.DARK_GRAY);
        listaEventos.setForeground(Color.WHITE);
        listaEventos.setSelectionBackground(Color.LIGHT_GRAY);
        listaEventos.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(listaEventos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(1, 0, 10, 0));
        panelBotones.setBackground(Color.BLACK);

        btnAgregar = new JButton("Agregar Evento");
        btnEditar = new JButton("Editar Evento");
        btnVerDetalles = new JButton("Ver Detalles");
        btnEliminarEvento = new JButton("Eliminar Evento");
        btnEliminarAsistente = new JButton("Eliminar Asistente");
        btnAgregarRecurso = new JButton("Agregar Recurso");
        btnEliminarRecurso = new JButton("Eliminar Recurso");
        btnEliminarRecurso.setBackground(Color.GRAY);
        btnEliminarRecurso.setForeground(Color.WHITE);

        JButton[] botones = {btnAgregar, btnEditar, btnVerDetalles, btnEliminarEvento, btnEliminarAsistente, btnAgregarRecurso, btnEliminarRecurso};
        for (JButton btn : botones) {
            btn.setBackground(Color.GRAY);
            btn.setForeground(Color.WHITE);
            panelBotones.add(btn);
        }

        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> {
            FormularioEvento formulario = new FormularioEvento(gestor, this, null);
            formulario.setVisible(true);
        });

        btnEditar.addActionListener(e -> {
            int index = listaEventos.getSelectedIndex();
            if (index != -1) {
                Evento evento = gestor.listarEventos().get(index);
                FormularioEvento formulario = new FormularioEvento(gestor, this, evento);
                formulario.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(VentanaPrincipal.this, "Seleccione un evento para editar.");
            }
        });

        btnVerDetalles.addActionListener(e -> {
            int index = listaEventos.getSelectedIndex();
            if (index != -1) {
                Evento evento = gestor.listarEventos().get(index);
                DetalleEvento detalle = new DetalleEvento(evento);
                detalle.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(VentanaPrincipal.this, "Seleccione un evento para ver detalles.");
            }
        });

        btnEliminarEvento.addActionListener(e -> {
            int index = listaEventos.getSelectedIndex();
            if (index != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar este evento?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Evento evento = gestor.listarEventos().get(index);
                    gestor.eliminarEvento(evento.getId());
                    gestor.guardarEventosEnArchivo("data/eventos.txt");
                    gestor.guardarRecursosEnArchivo("data/recursos.txt");
                    actualizarListaEventos();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un evento para eliminar.");
            }
        });

        btnEliminarAsistente.addActionListener(e -> {
            int index = listaEventos.getSelectedIndex();
            if (index != -1) {
                Evento evento = gestor.listarEventos().get(index);
                java.util.List<Asistente> asistentes = evento.getAsistentes();
                if (asistentes.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Este evento no tiene asistentes.");
                    return;
                }
                String[] nombres = asistentes.stream().map(a -> a.getNombre() + " (" + a.getDni() + ")").toArray(String[]::new);
                String seleccionado = (String) JOptionPane.showInputDialog(this, "Seleccione asistente a eliminar:", "Eliminar Asistente", JOptionPane.PLAIN_MESSAGE, null, nombres, nombres[0]);
                if (seleccionado != null) {
                    int idx = java.util.Arrays.asList(nombres).indexOf(seleccionado);
                    if (idx != -1) {
                        asistentes.remove(idx);
                        gestor.guardarAsistentesEnArchivo("data/asistentes.txt");
                        JOptionPane.showMessageDialog(this, "Asistente eliminado.");
                        actualizarListaEventos();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un evento primero.");
            }
        });

        btnAgregarRecurso.addActionListener(e -> {
            int index = listaEventos.getSelectedIndex();
            if (index != -1) {
                Evento evento = gestor.listarEventos().get(index);
                String tipo = JOptionPane.showInputDialog(this, "Tipo de recurso:");
                if (tipo == null || tipo.trim().isEmpty()) return;
                String descripcion = JOptionPane.showInputDialog(this, "Descripción del recurso:");
                if (descripcion == null || descripcion.trim().isEmpty()) return;
                Recurso recurso = new Recurso(tipo, descripcion);
                evento.agregarRecurso(recurso);
                gestor.guardarRecursosEnArchivo("data/recursos.txt");
                JOptionPane.showMessageDialog(this, "Recurso agregado.");
                actualizarListaEventos();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un evento primero.");
            }
        });

        btnEliminarRecurso.addActionListener(e -> {
            int index = listaEventos.getSelectedIndex();
            if (index != -1) {
                Evento evento = gestor.listarEventos().get(index);
                java.util.List<Recurso> recursos = evento.getRecursos();
                if (recursos.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Este evento no tiene recursos.");
                    return;
                }
                String[] nombres = recursos.stream().map(Recurso::toString).toArray(String[]::new);
                String seleccionado = (String) JOptionPane.showInputDialog(this, "Seleccione recurso a eliminar:", "Eliminar Recurso", JOptionPane.PLAIN_MESSAGE, null, nombres, nombres[0]);
                if (seleccionado != null) {
                    int idx = java.util.Arrays.asList(nombres).indexOf(seleccionado);
                    if (idx != -1) {
                        recursos.remove(idx);
                        gestor.guardarRecursosEnArchivo("data/recursos.txt");
                        JOptionPane.showMessageDialog(this, "Recurso eliminado.");
                        actualizarListaEventos();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un evento primero.");
            }
        });

        actualizarListaEventos();
    }

    public void actualizarListaEventos() {
        modeloLista.clear();
        for (Evento evento : gestor.listarEventos()) {
            modeloLista.addElement(evento.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}