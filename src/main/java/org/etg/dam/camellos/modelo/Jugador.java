package main.java.org.etg.dam.camellos.modelo;
import java.net.Socket;

public class Jugador {
    private String nombre;
    private int posicion;
    private Socket socket;

    public Jugador(String nombre, Socket socket) {
        this.nombre = nombre;
        this.socket = socket;
        this.posicion = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void avanzar(int pasos) {
        posicion += pasos;
    }

    public void reiniciarPosicion() {
        posicion = 0;
    }

    public Socket getSocket() {
        return socket;
    }
}