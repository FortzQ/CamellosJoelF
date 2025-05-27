package main.java.org.etg.dam.camellos.servidor;

import main.java.org.etg.dam.camellos.modelo.Carrera;
import main.java.org.etg.dam.camellos.modelo.Jugador;

import java.io.*;
import java.net.Socket;


public class HiloJugador implements Runnable {
    private final Socket socket;
    private final Carrera carrera;
    private Jugador jugador;
    private BufferedReader entrada;
    private PrintWriter salida;

    public HiloJugador(Socket socket, Carrera carrera) {
        this.socket = socket;
        this.carrera = carrera;
    }

    @Override
    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);

            // Recibe el nombre del jugador
            String nombre = entrada.readLine();
            jugador = new Jugador(nombre, socket);
            carrera.agregarJugador(jugador);

            salida.println("Esperando a otro jugador...");

            // Espera activa a que estén los dos jugadores conectados
            while (!carrera.estaLlena()) {
                Thread.sleep(100);
            }

            salida.println("¡Comienza la carrera!");

            while (!carrera.hayGanador()) {
                // Avanza jugador y comprueba si ha ganado
                Jugador posibleGanador = carrera.avanzarJugador(jugador.getNombre());

                // Envia estado actual al jugador
                Jugador[] todos = carrera.getJugadores();
                salida.println("POSICIONES:");
                for (Jugador j : todos) {
                    salida.println(j.getNombre() + ": " + j.getPosicion());
                }
                salida.println("---");

                if (posibleGanador != null && posibleGanador == jugador) {
                    salida.println("¡HAS GANADO!");
                }

                Thread.sleep(1000); // simula tiempo entre turnos
            }

            if (carrera.getGanador() != jugador) {
                salida.println("Has perdido.");
            }

        } catch (Exception e) {
            System.err.println("Error con jugador: " + e.getMessage());
        }
    }
}
