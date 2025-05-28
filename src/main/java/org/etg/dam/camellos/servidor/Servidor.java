package main.java.org.etg.dam.camellos.servidor;

import java.net.ServerSocket;
import java.net.Socket;

import main.java.org.etg.dam.camellos.modelo.Carrera;
import main.java.org.etg.dam.camellos.modelo.Jugador;
import main.java.org.etg.dam.camellos.util.RegistroPartidas;

public class Servidor {
    private static final int PUERTO = 8888;

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor activo en el puerto " + PUERTO);

            while (true) { // Bucle eterno para múltiples partidas
                Carrera carrera = new Carrera();
                Socket[] sockets = new Socket[2];
                HiloJugador[] hilos = new HiloJugador[2];

                // Esperar 2 conexiones
                for (int i = 0; i < 2; i++) {
                    sockets[i] = servidor.accept();
                    System.out.println("Jugador conectado: " + sockets[i].getInetAddress());
                    hilos[i] = new HiloJugador(sockets[i], carrera);
                    new Thread(hilos[i]).start();
                }

                // Esperar a que ambos jugadores estén listos
                while (!carrera.estaLlena()) Thread.sleep(100);

                // Iniciar la carrera (el servidor la controla)
                while (!carrera.hayGanador()) {
                    for (Jugador j : carrera.getJugadores()) {
                        carrera.avanzarJugador(j.getNombre());
                    }

                    // Enviar estado actualizado a todos los jugadores
                    for (HiloJugador hilo : hilos) {
                        hilo.enviarEstado();
                    }

                    Thread.sleep(1000);
                }

                Jugador ganador = carrera.getGanador();
                RegistroPartidas.registrar(carrera.getJugadores(), ganador);
                System.out.println("Ganador: " + ganador.getNombre());

                // Notificar resultado a los jugadores
                for (HiloJugador hilo : hilos) {
                    hilo.enviarResultado(ganador);
                }

                // Cerrar sockets tras la carrera
                for (Socket s : sockets) {
                    s.close();
                }

                System.out.println("Carrera finalizada. Esperando nuevos jugadores...");
            }

        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
