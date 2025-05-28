package main.java.org.etg.dam.camellos.cliente;

import javax.swing.*;

import main.java.org.etg.dam.camellos.util.GeneradorCertificado;

import java.io.*;
import java.net.Socket;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 8888;

    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog("Tu nombre:");
        if (nombre == null || nombre.isBlank()) return;

        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            salida.println(nombre);
            VistaJuego gui = new VistaJuego(nombre);

            while (true) {
                String linea = entrada.readLine();
                if (linea == null) break;

                switch (linea) {
                    case "POSICIONES:" -> {
                        String linea1 = entrada.readLine();
                        String linea2 = entrada.readLine();
                        entrada.readLine(); // "---"

                        gui.actualizarEstado(linea1, linea2);
                    }
                    case "¡HAS GANADO!" ->{
                        gui.mostrarResultado("¡Has ganado!");
                        GeneradorCertificado.generar(nombre);
                    }
                    case "Has perdido." -> gui.mostrarResultado("Has perdido.");
                    default -> gui.añadirLog(linea);
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}