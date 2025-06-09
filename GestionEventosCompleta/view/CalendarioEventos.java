package view;

import javax.swing.*;
import java.awt.*;
// Make sure the JCalendar library is in your classpath.
// If using Maven or Gradle, add the dependency for 'toedter-calendar'.
// If using a jar, add it to your project libraries.
import com.toedter.calendar.JCalendar;

public class CalendarioEventos extends JFrame {
    private JCalendar calendario;

    public CalendarioEventos() {
        setTitle("Calendario de Eventos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        calendario = new JCalendar();
        // Personalizar colores
        calendario.setBackground(Color.BLACK);
        calendario.setForeground(Color.WHITE);
        calendario.getDayChooser().setBackground(Color.BLACK);
        calendario.getDayChooser().setForeground(Color.WHITE);
        calendario.getMonthChooser().setBackground(Color.BLACK);
        calendario.getMonthChooser().setForeground(Color.WHITE);
        calendario.getYearChooser().setBackground(Color.BLACK);
        calendario.getYearChooser().setForeground(Color.WHITE);
        add(calendario, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalendarioEventos());
    }
}