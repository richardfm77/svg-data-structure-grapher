package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.IOException;

import mx.unam.ciencias.edd.proyecto2.svg.DibujaArbolAVLSvg;
import mx.unam.ciencias.edd.proyecto2.svg.DibujaArbolBinarioCompletoSvg;
import mx.unam.ciencias.edd.proyecto2.svg.DibujaArbolBinarioOrdenadoSvg;
import mx.unam.ciencias.edd.proyecto2.svg.DibujaArbolRojinegroSvg;
import mx.unam.ciencias.edd.proyecto2.svg.DibujaArreglosSvg;
import mx.unam.ciencias.edd.proyecto2.svg.DibujaColaSvg;
import mx.unam.ciencias.edd.proyecto2.svg.DibujaGraficaSvg;
import mx.unam.ciencias.edd.proyecto2.svg.DibujaListaSvg;
import mx.unam.ciencias.edd.proyecto2.svg.DibujaMonticuloMinimoSvg;
import mx.unam.ciencias.edd.proyecto2.svg.DibujaPilaSvg;

/**
 * Clase para la lectura y salida 
 * del programa.
 * Verifica que los datos de entrada 
 * sean correctos y hace su salida 
 * correspondiente.
 */
public class LecturaSalidaDeInstruccion {
    
    // Intrucción a ejecutar.
    private Instruccion instruccion;

    /**
     * Construye la intrucción. 
     */
    public LecturaSalidaDeInstruccion() {
        instruccion = new Instruccion(null, null);
    }

    /**
     * Hace la lectura de los datos 
     * de entrada, y los guarda en una
     * {@link Instruccion}  
     * @param in Lectura de los datos de entrada.
     * @throws IOException Si ocurre un error al 
     * momento de la lectura de los datos. 
     * @throws IllegalArgumentException Si los 
     * datos de entrada no tienen el formato 
     * solicitado por el programa.
     */
    public void lectura(BufferedReader in) throws IOException,
            IllegalArgumentException {
        String aux = "";
        try {
            String linea = "";
            while ((linea = in.readLine()) != null) {
                if (linea.contains("#"))
                    continue;
                if ((linea.trim()).equals(""))
                    continue;
                aux += linea.trim() + " ";
            }
        } catch (IOException e) {
            throw e;
        }

        String arreglo[] = aux.split(" ");

        Estructura estructura = Estructura.getEstructura(arreglo[0]);

        if (Estructura.NINGUNO == estructura)
            throw new IllegalArgumentException();

        int e[] = new int[arreglo.length - 1];

        for (int i = 1; i < arreglo.length; i++) {
            e[i - 1] = Integer.parseInt(arreglo[i]);
        }

        if (estructura == Estructura.GRAFICA && e.length % 2 != 0)
            throw new IllegalArgumentException();

        instruccion.setEstructura(estructura);
        instruccion.setElementos(e);
    }

    /**
     * Realiza la salida correspondiente 
     * de los datos de entrada.
     * @return cadena con la salida correspondiente 
     * de los datos de entrada. 
     */
    public String salida() {
        String salida = "";
        switch (instruccion.getEstructura()) {
            case LISTA: {
                DibujaListaSvg esSvg = new DibujaListaSvg(instruccion);
                salida = esSvg.dibujaSvg();
                break;
            }
            case ARBOL_AVL: {
                DibujaArbolAVLSvg esSvg = new DibujaArbolAVLSvg(instruccion);
                salida = esSvg.dibujaSvg();
                break;
            }
            case ARBOL_BINARIO_COMPLETO: {
                DibujaArbolBinarioCompletoSvg esSvg = new DibujaArbolBinarioCompletoSvg(instruccion);
                salida = esSvg.dibujaSvg();
                break;
            }
            case ARBOL_BINARIO_ORDENADO: {
                DibujaArbolBinarioOrdenadoSvg esSvg = new DibujaArbolBinarioOrdenadoSvg(instruccion);
                salida = esSvg.dibujaSvg();
                break;
            }
            case ARBOL_ROJINEGRO: {
                DibujaArbolRojinegroSvg esSvg = new DibujaArbolRojinegroSvg(instruccion);
                salida = esSvg.dibujaSvg();
                break;
            }
            case ARREGLOS: {
                DibujaArreglosSvg esSvg = new DibujaArreglosSvg(instruccion.getElementos());
                salida = esSvg.dibujaSvg();
                break;
            }
            case COLA: {
                DibujaColaSvg esSvg = new DibujaColaSvg(instruccion);
                salida = esSvg.dibujaSvg();
                break;
            }
            case GRAFICA: {
                DibujaGraficaSvg esSvg = new DibujaGraficaSvg(instruccion);
                salida = esSvg.dibujaSvg();
                break;
            }
            case MONTICULO_MINIMO: {
                DibujaMonticuloMinimoSvg esSvg = new DibujaMonticuloMinimoSvg(instruccion);
                salida = esSvg.dibujaSvg();
                break;
            }
            case NINGUNO:
                // Nuca sucede
                break;
            case PILA: {
                DibujaPilaSvg esSvg = new DibujaPilaSvg(instruccion);
                salida = esSvg.dibujaSvg();
                break;
            }
        }
        return salida;
    }
    /**
     * Regresa la intrucción pero en su 
     * representación en cadena.
     * @return Intrucción depurada en su 
     * represantación en cadena.
     */
    public String salidaInstruccionToString() {
        return instruccion.toString();
    }
}