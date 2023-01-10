package mx.unam.ciencias.edd.proyecto2;

/**
 * Enumeración para las distintas 
 * estructuras de datos.
 */

public enum Estructura {
    
    ARBOL_AVL,
    ARBOL_BINARIO_COMPLETO,
    ARBOL_BINARIO_ORDENADO,
    ARBOL_ROJINEGRO,
    ARREGLOS,
    COLA,
    GRAFICA,
    LISTA,
    MONTICULO_MINIMO,
    PILA,
    NINGUNO;                          

    /**
     * No dice si una cadena coincide con la 
     * de una estructura de datos.
     * 
     * @param cadena La cadena a comparar.
     * @return true si la cadena coincide con un 
     * String de una estructura de datos.
     * fañse en otro caso.
     */
    public static boolean contiene(String cadena) {
        switch (cadena) {
            case "ArbolAVL":
                return true;
            case "ArbolBinarioCompleto":
                return true;
            case "ArbolBinarioOrdenado":
                return true;
            case "ArbolRojinegro":
                return true;
            case "Arreglos":
                return true;
            case "Cola":
                return true;
            case "Grafica":
                return true;
            case "Lista":
                return true;
            case "MonticuloMinimo":
                return true;
            case "Pila":
                return true;
            default:
                return false;
        }
    }

    /**
     * Regresa la enumeración de una estructura 
     * de datos, mediante la identicficación 
     * de una cadena. 
     * 
     * @param cadena para pedir la enumeración de 
     * una estructura de datos.
     * @return la enumeración de uan estructura 
     * de datos.
     */
    public static Estructura getEstructura(String cadena) {
        switch (cadena) {
            case "ArbolAVL":
                return ARBOL_AVL;
            case "ArbolBinarioCompleto":
                return ARBOL_BINARIO_COMPLETO;
            case "ArbolBinarioOrdenado":
                return ARBOL_BINARIO_ORDENADO;
            case "ArbolRojinegro":
                return ARBOL_ROJINEGRO;
            case "Arreglos":
                return ARREGLOS;
            case "Cola":
                return COLA;
            case "Grafica":
                return GRAFICA;
            case "Lista":
                return LISTA;
            case "MonticuloMinimo":
                return MONTICULO_MINIMO;
            case "Pila":
                return PILA;
            default:
                return NINGUNO;
        }
    }

    /**
     * Da la representación en cadena de
     * una estructura de datos.
     * @return la representacion en cadena, 
     * de una estructura de datos.
     */
    @Override
    public String toString() {
        switch (this) {
            case ARBOL_AVL:
                return "ArbolAVL";
            case ARBOL_BINARIO_COMPLETO:
                return "ArbolBinarioCompleto";
            case ARBOL_BINARIO_ORDENADO:
                return "ArbolBinarioOrdenado";
            case ARBOL_ROJINEGRO:
                return "ArbolRojinegro";
            case ARREGLOS:
                return "Arreglos";
            case COLA:
                return "Cola";
            case GRAFICA:
                return "Grafica";
            case LISTA:
                return "Lista";
            case MONTICULO_MINIMO:
                return "MonticuloMinimo";
            case PILA:
                return "Pila";
            default:
                return null;
        }
    }
}
