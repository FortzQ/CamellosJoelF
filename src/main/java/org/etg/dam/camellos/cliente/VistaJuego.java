package main.java.org.etg.dam.camellos.cliente;

import javax.swing.*;
import java.awt.*;

public class VistaJuego extends JFrame {
    private final JLabel linea1 = new JLabel();
    private final JLabel linea2 = new JLabel();
    private final JLabel resultado = new JLabel();
    private final JTextArea log = new JTextArea();

    public VistaJuego(String nombre) {
        setTitle("Carrera de Camellos - " + nombre);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new GridLayout(3, 1));
        top.add(linea1);
        top.add(linea2);
        top.add(resultado);
        resultado.setForeground(Color.BLUE);

        log.setEditable(false);
        JScrollPane scroll = new JScrollPane(log);

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actualizarEstado(String j1, String j2) {
        linea1.setText(j1);
        linea2.setText(j2);
    }

    public void mostrarResultado(String msg) {
        resultado.setText(msg);
    }

    public void añadirLog(String msg) {
        log.append(msg + "\n");
    }
}
