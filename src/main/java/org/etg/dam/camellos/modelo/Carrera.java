package main.java.org.etg.dam.camellos.modelo;

public class Carrera {
private final int META = 100;
    private final Jugador[] jugadores = new Jugador[2];
    private int contadorJugadores = 0;

    public synchronized void agregarJugador(Jugador jugador) {
        if (contadorJugadores < 2) {
            jugadores[contadorJugadores++] = jugador;
        }
    }

    public synchronized boolean estaLlena() {
        return contadorJugadores == 2;
    }

    public synchronized Jugador[] getJugadores() {
        return jugadores;
    }

    public synchronized Jugador avanzarJugador(String nombre) {
        for (Jugador jugador : jugadores) {
            if (jugador != null && jugador.getNombre().equals(nombre)) {
                int avance = (int)(Math.random() * 10 + 1); // entre 1 y 10
                jugador.avanzar(avance);
                if (jugador.getPosicion() >= META) {
                    return jugador;
                }
            }
        }
        return null;
    }

    public synchronized boolean hayGanador() {
        for (Jugador j : jugadores) {
            if (j != null && j.getPosicion() >= META) return true;
        }
        return false;
    }

    public synchronized Jugador getGanador() {
        for (Jugador j : jugadores) {
            if (j != null && j.getPosicion() >= META) return j;
        }
        return null;
    }
}
