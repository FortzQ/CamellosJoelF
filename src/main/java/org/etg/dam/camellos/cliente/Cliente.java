package main.java.org.etg.dam.camellos.cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String HOST = "localhost"; // o la IP del servidor
    private static final int PUERTO = 8888;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PUERTO);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Introduce tu nombre: ");
            String nombre = scanner.nextLine();
            salida.println(nombre);

            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println(mensaje);
            }

        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }
}