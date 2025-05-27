package main.java.org.etg.dam.camellos.servidor;

import java.net.ServerSocket;
import java.net.Socket;

import main.java.org.etg.dam.camellos.modelo.Carrera;
import main.java.org.etg.dam.camellos.modelo.Jugador;

import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
     private static final int PUERTO = 8888;
    private static final Carrera carrera = new Carrera();

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor esperando jugadores en el puerto " + PUERTO);

            while (!carrera.estaLlena()) {
                Socket socket = servidor.accept();
                System.out.println("Jugador conectado desde " + socket.getInetAddress());

                // Crea y lanza el hilo para manejar al jugador
                HiloJugador hilo = new HiloJugador(socket, carrera);
                new Thread(hilo).start();
            }

            // Espera activa hasta que haya un ganador
            while (!carrera.hayGanador()) {
                Thread.sleep(100);
            }

            Jugador ganador = carrera.getGanador();
            System.out.println("¡Ganador: " + ganador.getNombre() + " con " + ganador.getPosicion() + " unidades!");

        } catch (Exception e) {
            // Manejo básico de errores sin printStackTrace
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
