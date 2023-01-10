package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase principal Proyecto 2.
 * Inicializa todo el programa, y se encarga del 
 * manejo de errores. 
 *
 */
public class Proyecto2 {

    // Mensajes de errores.
    static final String ERROR_LECTURA_ARCHIVO = "Error al leer el archivo. :(";
    static final String ERROR_LECTURA_ESTANDAR = "Error de lectura estandar. :(";
    static final String ERROR_ARCHIVO_NO_ENCONTRADO = "No se econtró el archivo con nombre: ";
    static final String ERROR_FORMATO_INVALIDO = "Formato inválido. :(";

    // Nombre del archivo para leer.
    static String nombreArchivo;

    // Lectura de datos y salida del programa.
    static LecturaSalidaDeInstruccion l = new LecturaSalidaDeInstruccion();

    /**
     * Hace lectura de lo archivos por lienea de comandos
     */
    private static void lecturaDeArchivo() {
        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            BufferedReader in = new BufferedReader(isIn);
            l.lectura(in);
            in.close();
        } catch (FileNotFoundException e) {
            error(ERROR_ARCHIVO_NO_ENCONTRADO + nombreArchivo + " :(");
        } catch (IOException e) {
            error(ERROR_LECTURA_ARCHIVO);
        } catch (IllegalArgumentException e) {
            error(ERROR_FORMATO_INVALIDO);
        }
    }

    /**
     * Lee de la estrada estadar.
     */
    private static void lecturaEstandar() {
        try {
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            l.lectura(br);
            br.close();
        } catch (IOException e) {
            error(ERROR_LECTURA_ESTANDAR);
        } catch (IllegalArgumentException e) {
            error(ERROR_FORMATO_INVALIDO);
        }
    }

    // Métodos auxilaires para manejar errores.
    
    private static void salida(){
        System.out.print(l.salida());
    }

    private static void error(String mensaje) {
        System.err.println(mensaje);
        System.exit(1);
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            lecturaEstandar();
            salida();
            return;
        }
        nombreArchivo = args[0].trim();
        lecturaDeArchivo();
        salida();
    }
}
