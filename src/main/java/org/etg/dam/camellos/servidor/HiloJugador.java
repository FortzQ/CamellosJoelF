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

            String nombre = entrada.readLine();
            jugador = new Jugador(nombre, socket);
            carrera.agregarJugador(jugador);
            salida.println("Esperando a otro jugador...");
            while (!carrera.estaLlena()) Thread.sleep(100);
            salida.println("¡Comienza la carrera!");

        } catch (Exception e) {
            System.err.println("Error en hilo jugador: " + e.getMessage());
        }
    }

    public void enviarEstado() {
        Jugador[] jugadores = carrera.getJugadores();
        salida.println("POSICIONES:");
        for (Jugador j : jugadores) {
            salida.println(j.getNombre() + ": " + j.getPosicion());
        }
        salida.println("---");
    }

    public void enviarResultado(Jugador ganador) {
        if (jugador.getNombre().equals(ganador.getNombre())) {
            salida.println("¡HAS GANADO!");
        } else {
            salida.println("Has perdido.");
        }
    }
}
