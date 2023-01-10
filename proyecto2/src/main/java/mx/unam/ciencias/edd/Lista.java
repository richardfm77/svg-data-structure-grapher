package mx.unam.ciencias.edd;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>
 * Clase genérica para listas doblemente ligadas.
 * </p>
 *
 * <p>
 * Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.
 * </p>
 *
 * <p>
 * Las listas no aceptan a <code>null</code> como elemento.
 * </p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            // Aquí va su código.
            this.elemento = elemento;
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nuevo iterador. */
        private Iterador() {
            // Aquí va su código.
            start();
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override
        public boolean hasNext() {
            // Aquí va su código.
            return siguiente != null;
        }

        /* Nos da el elemento siguiente. */
        @Override
        public T next() {
            // Aquí va su código.
            if (siguiente == null)
                throw new NoSuchElementException();

            T t = siguiente.elemento;

            anterior = siguiente;
            siguiente = siguiente.siguiente;

            return t;
        }

        /* Nos dice si hay un elemento anterior. */
        @Override
        public boolean hasPrevious() {
            // Aquí va su código.
            return anterior != null;
        }

        /* Nos da el elemento anterior. */
        @Override
        public T previous() {
            // Aquí va su código.
            if (anterior == null)
                throw new NoSuchElementException();

            T t = anterior.elemento;

            siguiente = anterior;
            anterior = anterior.anterior;

            return t;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override
        public void start() {
            // Aquí va su código.
            anterior = null;
            siguiente = cabeza;
        }

        /* Mueve el iterador al final de la lista. */
        @Override
        public void end() {
            // Aquí va su código.
            siguiente = null;
            anterior = rabo;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * 
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        // Aquí va su código.
        return longitud;
    }

    /**
     * Regresa el número elementos en la lista. El método es idéntico a {@link
     * #getLongitud}.
     * 
     * @return el número elementos en la lista.
     */
    @Override
    public int getElementos() {
        // Aquí va su código.
        return getLongitud();
    }

    /**
     * Nos dice si la lista es vacía.
     * 
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override
    public boolean esVacia() {
        // Aquí va su código.
        return cabeza == null;
    }

    /**
     * Agrega un elemento a la lista. Si la lista no tiene elementos, el
     * elemento a agregar será el primero y último. El método es idéntico a
     * {@link #agregaFinal}.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override
    public void agrega(T elemento) {
        // Aquí va su código.
        agregaFinal(elemento);
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        // Aquí va su código.
        if (elemento == null)
            throw new IllegalArgumentException();

        Nodo n = new Nodo(elemento);

        if (rabo == null) {
            cabeza = rabo = n;
        } else {
            rabo.siguiente = n;
            n.anterior = rabo;
            rabo = n;
        }
        longitud++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        // Aquí va su código.
        if (elemento == null)
            throw new IllegalArgumentException();

        Nodo n = new Nodo(elemento);

        if (cabeza == null) {
            cabeza = rabo = n;
        } else {
            cabeza.anterior = n;
            n.siguiente = cabeza;
            cabeza = n;
        }
        longitud++;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * 
     * @param i        el índice dónde insertar el elemento. Si es menor que 0 el
     *                 elemento se agrega al inicio de la lista, y si es mayor o
     *                 igual
     *                 que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        // Aquí va su código.
        if (elemento == null)
            throw new IllegalArgumentException();

        if (i <= 0) {
            agregaInicio(elemento);
        } else if (i >= longitud) {
            agregaFinal(elemento);
        } else {
            Nodo s = getNodo(i);
            Nodo n = new Nodo(elemento);
            Nodo a = s.anterior;

            n.anterior = a;
            a.siguiente = n;
            n.siguiente = s;
            s.anterior = n;

            longitud++;
        }
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * 
     * @param elemento el elemento a eliminar.
     */
    @Override
    public void elimina(T elemento) {
        // Aquí va su código.
        Nodo eliminar = getNodo(elemento);

        if (eliminar == null)
            return;

        if (cabeza.equals(eliminar)) {
            eliminaPrimeroLista();
        } else if (rabo.equals(eliminar)) {
            eliminaUltimoLista();
        } else {

            Nodo a = eliminar.anterior;
            Nodo s = eliminar.siguiente;

            a.siguiente = s;
            s.anterior = a;

            longitud--;
        }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * 
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        // Aquí va su código.
        if (cabeza == null)
            throw new NoSuchElementException();

        T e = cabeza.elemento;
        eliminaPrimeroLista();

        return e;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * 
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        // Aquí va su código.
        if (rabo == null)
            throw new NoSuchElementException();

        T e = rabo.elemento;
        eliminaUltimoLista();

        return e;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * 
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    @Override
    public boolean contiene(T elemento) {
        // Aquí va su código.
        return getNodo(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * 
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        // Aquí va su código.
        return algList(0);
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * 
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        // Aquí va su código.
        return algList(1);
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    @Override
    public void limpia() {
        // Aquí va su código.
        cabeza = rabo = null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * 
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        // Aquí va su código.
        if (cabeza == null)
            throw new NoSuchElementException();
        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * 
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        // Aquí va su código.
        if (rabo == null)
            throw new NoSuchElementException();
        return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * 
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *                                 igual que el número de elementos en la lista.
     */
    public T get(int i) {
        // Aquí va su código.
        if (i < 0 || i >= longitud)
            throw new ExcepcionIndiceInvalido();

        Nodo var = getNodo(i);

        return var.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * 
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        // Aquí va su código.
        int contador = 0;
        Nodo n = cabeza;
        while (n != null) {
            if (elemento.equals(n.elemento))
                return contador;
            contador++;
            n = n.siguiente;
        }
        return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * 
     * @return una representación en cadena de la lista.
     */
    @Override
    public String toString() {
        // Aquí va su código.
        if (cabeza == null)
            return "[]";

        if (longitud == 1)
            return String.format("[%s]", rabo.elemento);

        String cadena = String.format("[%s, ", cabeza.elemento);

        Nodo n = cabeza.siguiente;

        while (n.siguiente != null) {
            cadena += String.format("%s, ", n.elemento);
            n = n.siguiente;
        }

        cadena += String.format("%s]", rabo.elemento);

        return cadena;
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * 
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked")
        Lista<T> lista = (Lista<T>) objeto;
        // Aquí va su código.
        if (lista.longitud != longitud)
            return false;

        Nodo nThis = cabeza;
        Nodo n = lista.cabeza;

        while (n != null) {
            if (!nThis.elemento.equals(n.elemento))
                return false;
            nThis = nThis.siguiente;
            n = n.siguiente;
        }

        return true;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * 
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * 
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    /**
     * Regresa una copia de la lista, pero ordenada. Para poder hacer el
     * ordenamiento, el método necesita una instancia de {@link Comparator} para
     * poder comparar los elementos de la lista.
     * 
     * @param comparador el comparador que la lista usará para hacer el
     *                   ordenamiento.
     * @return una copia de la lista, pero ordenada.
     */
    public Lista<T> mergeSort(Comparator<T> comparador) {
        // Aquí va su código.
        return auxMergeSort(this.copia(), comparador);
    }

    /**
     * Regresa una copia de la lista recibida, pero ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la interfaz {@link
     * Comparable}.
     * 
     * @param <T>   tipo del que puede ser la lista.
     * @param lista la lista que se ordenará.
     * @return una copia de la lista recibida, pero ordenada.
     */
    public static <T extends Comparable<T>> Lista<T> mergeSort(Lista<T> lista) {
        return lista.mergeSort((a, b) -> a.compareTo(b));
    }

    /**
     * Busca un elemento en la lista ordenada, usando el comparador recibido. El
     * método supone que la lista está ordenada usando el mismo comparador.
     * 
     * @param elemento   el elemento a buscar.
     * @param comparador el comparador con el que la lista está ordenada.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean busquedaLineal(T elemento, Comparator<T> comparador) {
        // Aquí va su código.
        Nodo n = cabeza;

        while (n != null) {
            if (comparador.compare(n.elemento, elemento) == 0)
                return true;
            n = n.siguiente;
        }
        return false;
    }

    /**
     * Busca un elemento en una lista ordenada. La lista recibida tiene que
     * contener nada más elementos que implementan la interfaz {@link
     * Comparable}, y se da por hecho que está ordenada.
     * 
     * @param <T>      tipo del que puede ser la lista.
     * @param lista    la lista donde se buscará.
     * @param elemento el elemento a buscar.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public static <T extends Comparable<T>> boolean busquedaLineal(Lista<T> lista, T elemento) {
        return lista.busquedaLineal(elemento, (a, b) -> a.compareTo(b));
    }

    // Métodos auxiliares

    // Auxiliar de mergeSort
    private Lista<T> auxMergeSort(Lista<T> lista, Comparator<T> comparador) {

        int l = lista.longitud;

        if (l <= 1)
            return lista;

        Lista<T> l1 = new Lista<T>();
        Lista<T> l2 = new Lista<T>();

        int m = l % 2 == 0 ? l / 2 : (l - 1) / 2;

        Nodo n = lista.cabeza;

        for (int i = 1; n != null; i++) {
            if (i <= m)
                l1.agregaFinal(n.elemento);
            else
                l2.agregaFinal(n.elemento);
            n = n.siguiente;
        }

        l1 = auxMergeSort(l1, comparador);
        l2 = auxMergeSort(l2, comparador);

        return merge(l1, l2, comparador);
    }

    // Mezcla dos listas
    public Lista<T> merge(Lista<T> l1, Lista<T> l2, Comparator<T> comparador) {

        Lista<T> mezcla = new Lista<T>();

        Nodo n1 = l1.cabeza;
        Nodo n2 = l2.cabeza;

        while (n1 != null && n2 != null) {
            if (comparador.compare(n1.elemento, n2.elemento) <= 0) {
                mezcla.agregaFinal(n1.elemento);
                n1 = n1.siguiente;
            } else {
                mezcla.agregaFinal(n2.elemento);
                n2 = n2.siguiente;
            }
        }

        Nodo n = n1 == null ? n2 : n1;

        while (n != null) {
            mezcla.agregaFinal(n.elemento);
            n = n.siguiente;
        }

        return mezcla;
    }

    // Regresa un Nodo por indice.
    private Nodo getNodo(int i) {
        Nodo n = cabeza;
        for (int contador = 0; n != null; contador++) {
            if (contador == i)
                return n;
            n = n.siguiente;
        }
        return n;
    }

    // Regresa un Nodo por elemento.
    private Nodo getNodo(T elemento) {
        Nodo n = cabeza;

        while (n != null) {
            if (elemento.equals(n.elemento))
                return n;
            n = n.siguiente;
        }

        return n;
    }

    // Algoritmo para lista
    private Lista<T> algList(int opc) {
        Lista<T> newlist = new Lista<T>();
        Nodo n = cabeza;
        while (n != null) {
            if (opc == 0) {
                newlist.agregaInicio(n.elemento);
            } else {
                newlist.agregaFinal(n.elemento);
            }
            n = n.siguiente;
        }
        return newlist;
    }

    // Elimina el primer elemento de la lista
    private void eliminaPrimeroLista() {
        if (rabo.equals(cabeza)) {
            cabeza = rabo = null;
        } else {
            Nodo temporal = cabeza.siguiente;
            cabeza = temporal;
            temporal.anterior = null;
        }
        longitud--;
    }

    // Elimina el ultimo elemento de la lista
    private void eliminaUltimoLista() {
        if (rabo.equals(cabeza)) {
            cabeza = rabo = null;
        } else {
            Nodo temporal = rabo.anterior;
            rabo = temporal;
            temporal.siguiente = null;
        }
        longitud--;
    }
}
