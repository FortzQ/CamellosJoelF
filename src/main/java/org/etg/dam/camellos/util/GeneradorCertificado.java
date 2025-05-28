package main.java.org.etg.dam.camellos.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class GeneradorCertificado {

    public static void generar(String nombreJugador) {
        String fecha = LocalDate.now().toString();
        String contenido = """
                CERTIFICADO DE GANADOR
                ----------------------
                Jugador: %s
                Fecha: %s
                ¡Has ganado la carrera de camellos!
                """.formatted(nombreJugador, fecha);

        String ruta = "C:\\Users\\USUARIO\\Documents\\CamellosJoelF-1\\certificados";
        File carpeta = new File(ruta);
        if (!carpeta.exists()) carpeta.mkdirs();

        File archivo = new File(ruta + File.separator + "certificado_" + nombreJugador + ".txt");
        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write(contenido);
        } catch (IOException e) {
            System.err.println("Error generando el certificado: " + e.getMessage());
        }
    }
}
