package mx.unam.ciencias.edd.proyecto2;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

/**
 * Test del proyecto 2.
 */
public class TestProyecto2 {

    private LecturaSalidaDeInstruccion l = new LecturaSalidaDeInstruccion();

    private void lecturaDeArchivo(String nombreArchivo) {
        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            BufferedReader in = new BufferedReader(isIn);
            l.lectura(in);
            in.close();
        } catch (FileNotFoundException e) {
            assertTrue(false);
        } catch (IOException e) {
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(false);
        }
    }

    /**
     * Test para validar datos de entrada.
     */
    @Test
    public void TestEntradaDeDatos() {
        String out;
        lecturaDeArchivo(TestRutas.RUTA_TEST_ENTRADA_ARBOL_ROJINEGRO);
        out = l.salidaInstruccionToString();
        assertTrue(
                out.equals("ArbolRojinegro 1 2 3 4 5 6 7 8 9 10 11 12 13 14 5"));
        lecturaDeArchivo(TestRutas.RUTA_TEST_ENTRADA_LISTA);
        out = l.salidaInstruccionToString();
        assertTrue(
                out.equals("Lista 1 2 3 4 4 1"));
    }

    /**
     * Test de salida para Árboles AVL.
     */
    @Test
    public void TestSalidaArbolAVL() {
        l = new LecturaSalidaDeInstruccion();
        lecturaDeArchivo(TestRutas.RUTA_ENTRADA_ARBOL_AVL);
        String out = l.salida();
        try {
            FileOutputStream fileOut = new FileOutputStream(TestRutas.RUTA_SALIDA_ARBOL_AVL);
            OutputStreamWriter isOut = new OutputStreamWriter(fileOut);
            BufferedWriter w = new BufferedWriter(isOut);
            w.write(out);
            w.close();
            assertTrue(true);
        } catch (FileNotFoundException e) {
            assertTrue(false);
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    /**
     * Test de salida para Árboles binarios completos.
     */
    @Test
    public void TestSalidaArbolBinarioCompleto() {
        l = new LecturaSalidaDeInstruccion();
        lecturaDeArchivo(TestRutas.RUTA_ENTRADA_ARBOL_BINARIO_COMPLETO);
        String out = l.salida();
        try {
            FileOutputStream fileOut = new FileOutputStream(TestRutas.RUTA_SALIDA_ARBOL_BINARIO_COMPLETO);
            OutputStreamWriter isOut = new OutputStreamWriter(fileOut);
            BufferedWriter w = new BufferedWriter(isOut);
            w.write(out);
            w.close();
            assertTrue(true);
        } catch (FileNotFoundException e) {
            assertTrue(false);
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    /**
     * Test de salida para Árboles binarios
     * ordenados.
     */
    @Test
    public void TestSalidaArbolBinarioOrdenado() {
        l = new LecturaSalidaDeInstruccion();
        lecturaDeArchivo(TestRutas.RUTA_ENTRADA_ARBOL_BINARIO_ORDENADO);
        String out = l.salida();
        try {
            FileOutputStream fileOut = new FileOutputStream(TestRutas.RUTA_SALIDA_ARBOL_BINARIO_ORDENADO);
            OutputStreamWriter isOut = new OutputStreamWriter(fileOut);
            BufferedWriter w = new BufferedWriter(isOut);
            w.write(out);
            w.close();
            assertTrue(true);
        } catch (FileNotFoundException e) {
            assertTrue(false);
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    /**
     * Test de salida para Árboles rojinegros.
     */
    @Test
    public void TestSalidaArbolRojinegro() {
        l = new LecturaSalidaDeInstruccion();
        lecturaDeArchivo(TestRutas.RUTA_ENTRADA_ARBOL_ROJINEGRO);
        String out = l.salida();
        try {
            FileOutputStream fileOut = new FileOutputStream(TestRutas.RUTA_SALIDA_ARBOL_ROJINEGRO);
            OutputStreamWriter isOut = new OutputStreamWriter(fileOut);
            BufferedWriter w = new BufferedWriter(isOut);
            w.write(out);
            w.close();
            assertTrue(true);
        } catch (FileNotFoundException e) {
            assertTrue(false);
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    /**
     * Test de salida para Arreglos.
     */
    @Test
    public void TestSalidaArreglos() {
        l = new LecturaSalidaDeInstruccion();
        lecturaDeArchivo(TestRutas.RUTA_ENTRADA_ARREGLO);
        String out = l.salida();
        try {
            FileOutputStream fileOut = new FileOutputStream(TestRutas.RUTA_SALIDA_ARREGLO);
            OutputStreamWriter isOut = new OutputStreamWriter(fileOut);
            BufferedWriter w = new BufferedWriter(isOut);
            w.write(out);
            w.close();
            assertTrue(true);
        } catch (FileNotFoundException e) {
            assertTrue(false);
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    /**
     * Test de salida para Colas.
     */
    @Test
    public void TestSalidaCola() {
        l = new LecturaSalidaDeInstruccion();
        lecturaDeArchivo(TestRutas.RUTA_ENTRADA_COLA);
        String out = l.salida();
        try {
            FileOutputStream fileOut = new FileOutputStream(TestRutas.RUTA_SALIDA_COLA);
            OutputStreamWriter isOut = new OutputStreamWriter(fileOut);
            BufferedWriter w = new BufferedWriter(isOut);
            w.write(out);
            w.close();
            assertTrue(true);
        } catch (FileNotFoundException e) {
            assertTrue(false);
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    /**
     * Test de salida para Gráficas.
     */
    @Test
    public void TestSalidaGrafica() {
        l = new LecturaSalidaDeInstruccion();
        lecturaDeArchivo(TestRutas.RUTA_ENTRADA_GRAFICA);
        String out = l.salida();
        try {
            FileOutputStream fileOut = new FileOutputStream(TestRutas.RUTA_SALIDA_GRAFICA);
            OutputStreamWriter isOut = new OutputStreamWriter(fileOut);
            BufferedWriter w = new BufferedWriter(isOut);
            w.write(out);
            w.close();
            assertTrue(true);
        } catch (FileNotFoundException e) {
            assertTrue(false);
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    /**
     * Test de salida para Listas.
     */
    @Test
    public void TestSalidaLista() {
        l = new LecturaSalidaDeInstruccion();
        lecturaDeArchivo(TestRutas.RUTA_ENTRADA_LISTA);
        String out = l.salida();
        try {
            FileOutputStream fileOut = new FileOutputStream(TestRutas.RUTA_SALIDA_LISTA);
            OutputStreamWriter isOut = new OutputStreamWriter(fileOut);
            BufferedWriter w = new BufferedWriter(isOut);
            w.write(out);
            w.close();
            assertTrue(true);
        } catch (FileNotFoundException e) {
            assertTrue(false);
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    /**
     * Test de salida para Montículo mínimo.
     */
    @Test
    public void TestSalidaMonticuloMinimo() {
        l = new LecturaSalidaDeInstruccion();
        lecturaDeArchivo(TestRutas.RUTA_ENTRADA_MONTICULO);
        String out = l.salida();
        try {
            FileOutputStream fileOut = new FileOutputStream(TestRutas.RUTA_SALIDA_MONTICULO);
            OutputStreamWriter isOut = new OutputStreamWriter(fileOut);
            BufferedWriter w = new BufferedWriter(isOut);
            w.write(out);
            w.close();
            assertTrue(true);
        } catch (FileNotFoundException e) {
            assertTrue(false);
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    /**
     * Test de salida para Pilas.
     */
    @Test
    public void TestSalidaPila() {
        l = new LecturaSalidaDeInstruccion();
        lecturaDeArchivo(TestRutas.RUTA_ENTRADA_PILA);
        String out = l.salida();
        try {
            FileOutputStream fileOut = new FileOutputStream(TestRutas.RUTA_SALIDA_PILA);
            OutputStreamWriter isOut = new OutputStreamWriter(fileOut);
            BufferedWriter w = new BufferedWriter(isOut);
            w.write(out);
            w.close();
            assertTrue(true);
        } catch (FileNotFoundException e) {
            assertTrue(false);
        } catch (IOException e) {
            assertTrue(false);
        }
    }
}
