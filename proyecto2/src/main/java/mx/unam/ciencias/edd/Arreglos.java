package mx.unam.ciencias.edd;

import java.util.Comparator;

/**
 * Clase para ordenar y buscar arreglos genéricos.
 */
public class Arreglos {

    /* Constructor privado para evitar instanciación. */
    private Arreglos() {}

    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordenar el arreglo.
     */
    public static <T> void
    quickSort(T[] arreglo, Comparator<T> comparador) {
        // Aquí va su código.
        auxQuickSort(arreglo, comparador, 0, arreglo.length - 1);
    }

    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    quickSort(T[] arreglo) {
        quickSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordernar el arreglo.
     */
    public static <T> void
    selectionSort(T[] arreglo, Comparator<T> comparador) {
        // Aquí va su código.
        for (int i = 0; i < arreglo.length; i++) {
            int m = i;
            for (int j = i + 1; j < arreglo.length; j++) {
                if (comparador.compare(arreglo[j], arreglo[m]) < 0)
                    m = j;
            }
            T var = arreglo[i];
            arreglo[i] = arreglo[m];
            arreglo[m] = var;
        } 
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    selectionSort(T[] arreglo) {
        selectionSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo dónde buscar.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador para hacer la búsqueda.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T> int
    busquedaBinaria(T[] arreglo, T elemento, Comparator<T> comparador) {
        // Aquí va su código.
        return auxBusquedaBinaria(arreglo, elemento, comparador,
                0, arreglo.length - 1);
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     * @param elemento el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T extends Comparable<T>> int
    busquedaBinaria(T[] arreglo, T elemento) {
        return busquedaBinaria(arreglo, elemento, (a, b) -> a.compareTo(b));
    }

    // Métodos auxiliares

    // Auxiliar de QuickSort.
    private static <T> void auxQuickSort(T[] arreglo, Comparator<T> comparador, int a, int b) {

        if (b <= a)
            return;

        int i = a + 1;
        int j = b;

        while (i < j) {
            if (comparador.compare(arreglo[i], arreglo[a]) > 0
                    && comparador.compare(arreglo[j], arreglo[a]) <= 0) {

                T v = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = v;

                i++;
                j--;

            } else if (comparador.compare(arreglo[i], arreglo[a]) <= 0) {
                i++;
            } else {
                j--;
            }
        }

        if (comparador.compare(arreglo[i], arreglo[a]) > 0)
            i--;

        T v = arreglo[a];
        arreglo[a] = arreglo[i];
        arreglo[i] = v;

        auxQuickSort(arreglo, comparador, a, i - 1);
        auxQuickSort(arreglo, comparador, i + 1, b);
    }

    // Auxiliar Búsqueda binaria
    private static <T> int auxBusquedaBinaria(T[] arreglo, T elemento,
            Comparator<T> comparador, int a, int b) {

        if (b < a)
            return -1;

        int m = a + (b - a) / 2;

        if (comparador.compare(elemento, arreglo[m]) == 0) {
            return m;
        } else if (comparador.compare(elemento, arreglo[m]) < 0) {
            return auxBusquedaBinaria(arreglo, elemento, comparador, a, m - 1);
        } else {
            return auxBusquedaBinaria(arreglo, elemento, comparador, m + 1, b);
        }
    }

}
