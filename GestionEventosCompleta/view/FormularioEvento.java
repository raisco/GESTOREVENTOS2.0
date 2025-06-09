package view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import controller.GestorEventos;
import model.Evento;
import com.toedter.calendar.JCalendar;

public class FormularioEvento extends JFrame {
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtFecha;
    private JButton btnCalendario;
    private JTextField txtUbicacion;
    private JTextArea txtDescripcion;
    private JButton btnGuardar;
    private JButton btnCancelar;

    private GestorEventos gestor;
    private VentanaPrincipal ventanaPrincipal;
    private Evento eventoEditar;

    public FormularioEvento(GestorEventos gestor, VentanaPrincipal ventanaPrincipal, Evento eventoEditar) {
        this.gestor = gestor;
        this.ventanaPrincipal = ventanaPrincipal;
        this.eventoEditar = eventoEditar;

        setTitle(eventoEditar == null ? "Agregar Evento" : "Editar Evento");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 3));

        add(new JLabel("ID:"));
        txtId = new JTextField();
        add(txtId);
        add(new JLabel()); // vacío

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);
        add(new JLabel());

        add(new JLabel("Fecha (yyyy-MM-dd):"));
        txtFecha = new JTextField();
        txtFecha.setEditable(false);
        add(txtFecha);
        btnCalendario = new JButton("Seleccionar");
        add(btnCalendario);

        add(new JLabel("Ubicación:"));
        txtUbicacion = new JTextField();
        add(txtUbicacion);
        add(new JLabel());

        add(new JLabel("Descripción:"));
        txtDescripcion = new JTextArea();
        add(new JScrollPane(txtDescripcion));
        add(new JLabel());

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        add(btnGuardar);
        add(btnCancelar);
        add(new JLabel());

        if (eventoEditar != null) {
            txtId.setText(eventoEditar.getId());
            txtId.setEditable(false);
            txtNombre.setText(eventoEditar.getNombre());
            txtFecha.setText(eventoEditar.getFecha());
            txtUbicacion.setText(eventoEditar.getUbicacion());
            txtDescripcion.setText(eventoEditar.getDescripcion());
        }

        btnCalendario.addActionListener(e -> {
            JDialog dialog = new JDialog(this, "Seleccionar Fecha", true);
            JCalendar calendar = new JCalendar();
            dialog.add(calendar, BorderLayout.CENTER);
            JButton btnOk = new JButton("OK");
            btnOk.addActionListener(ev -> {
                Date fecha = calendar.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                txtFecha.setText(sdf.format(fecha));
                dialog.dispose();
            });
            dialog.add(btnOk, BorderLayout.SOUTH);
            dialog.setSize(400, 300);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        });

        btnGuardar.addActionListener(e -> {
            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            String fecha = txtFecha.getText().trim();
            String ubicacion = txtUbicacion.getText().trim();
            String descripcion = txtDescripcion.getText().trim();

            if (id.isEmpty() || nombre.isEmpty() || fecha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID, Nombre y Fecha son obligatorios.");
                return;
            }

            Evento nuevoEvento = new Evento(id, nombre, fecha, ubicacion, descripcion);

            if (eventoEditar == null) {
                gestor.agregarEvento(nuevoEvento);
            } else {
                gestor.editarEvento(id, nuevoEvento);
            }
            gestor.guardarEventosEnArchivo("data/eventos.txt");
            ventanaPrincipal.actualizarListaEventos();
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}