package main.java.org.etg.dam.camellos.util;

import main.java.org.etg.dam.camellos.modelo.Jugador;

import java.io.*;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class RegistroPartidas {
    private static final String RUTA = "C:\\Users\\USUARIO\\Documents\\CamellosJoelF-1\\data\\historial.txt";
    private static final AtomicInteger contador = new AtomicInteger(contadorInicial());

    private static int contadorInicial() {
        File archivo = new File(RUTA);
        if (!archivo.exists()) return 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int ultimo = 0;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Partida")) {
                    String[] partes = linea.split(" ");
                    ultimo = Integer.parseInt(partes[1]);
                }
            }
            return ultimo + 1;
        } catch (IOException | NumberFormatException e) {
            return 1;
        }
    }

    public static void registrar(Jugador[] jugadores, Jugador ganador) {
        File archivo = new File(RUTA);
        archivo.getParentFile().mkdirs();

        String fecha = LocalDate.now().toString();
        String linea = "Partida " + contador.getAndIncrement() +
                " | " + jugadores[0].getNombre() + " vs " + jugadores[1].getNombre() +
                " | " + fecha +
                " | Ganador: " + ganador.getNombre();

        try (FileWriter fw = new FileWriter(archivo, true)) {
            fw.write(linea + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error al guardar historial: " + e.getMessage());
        }
    }
}
