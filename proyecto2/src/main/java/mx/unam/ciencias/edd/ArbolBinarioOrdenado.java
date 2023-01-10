package mx.unam.ciencias.edd;

import java.util.Iterator;

/**
 * <p>
 * Clase para árboles binarios ordenados. Los árboles son genéricos, pero
 * acotados a la interfaz {@link Comparable}.
 * </p>
 *
 * <p>
 * Un árbol instancia de esta clase siempre cumple que:
 * </p>
 * <ul>
 * <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 * descendientes por la izquierda.</li>
 * <li>Cualquier elemento en el árbol es menor o igual que todos sus
 * descendientes por la derecha.</li>
 * </ul>
 */
public class ArbolBinarioOrdenado<T extends Comparable<T>>
        extends ArbolBinario<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los vértices en DFS in-order. */
        private Pila<Vertice> pila;

        /* Inicializa al iterador. */
        private Iterador() {
            // Aquí va su código.
            pila = new Pila<>();
            Vertice v = raiz;
            while (v != null) {
                pila.mete(v);
                v = v.izquierdo;
            }
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override
        public boolean hasNext() {
            // Aquí va su código.
            return !pila.esVacia();
        }

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override
        public T next() {
            // Aquí va su código.
            Vertice v = pila.saca();
            T e = v.elemento;
            v = v.derecho;
            while (v != null) {
                pila.mete(v);
                v = v.izquierdo;
            }
            return e;
        }
    }

    /**
     * El vértice del último elemento agegado. Este vértice sólo se puede
     * garantizar que existe <em>inmediatamente</em> después de haber agregado
     * un elemento al árbol. Si cualquier operación distinta a agregar sobre el
     * árbol se ejecuta después de haber agregado un elemento, el estado de esta
     * variable es indefinido.
     */
    protected Vertice ultimoAgregado;

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioOrdenado() {
        super();
    }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     * 
     * @param coleccion la colección a partir de la cual creamos el árbol
     *                  binario ordenado.
     */
    public ArbolBinarioOrdenado(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     * 
     * @param elemento el elemento a agregar.
     */
    @Override
    public void agrega(T elemento) {
        // Aquí va su código.
        if (elemento == null)
            throw new IllegalArgumentException();

        Vertice v = nuevoVertice(elemento);
        elementos++;
        ultimoAgregado = v;

        if (raiz == null) {
            raiz = v;
            return;
        }

        auxAgrega(raiz, v);

    }

    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     * 
     * @param elemento el elemento a eliminar.
     */
    @Override
    public void elimina(T elemento) {
        // Aquí va su código.
        if (elemento == null)
            throw new IllegalArgumentException();

        Vertice v = vertice(busca(elemento));

        if (v == null)
            return;

        elementos--;

        if (elementos == 0) {
            raiz = null;
            return;
        }

        Vertice eliminar = (v.izquierdo != null && v.derecho != null)
                ? intercambiaEliminable(v)
                : v;

        eliminaVertice(eliminar);
    }

    /**
     * Intercambia el elemento de un vértice con dos hijos distintos de
     * <code>null</code> con el elemento de un descendiente que tenga a lo más
     * un hijo.
     * 
     * @param vertice un vértice con dos hijos distintos de <code>null</code>.
     * @return el vértice descendiente con el que vértice recibido se
     *         intercambió. El vértice regresado tiene a lo más un hijo distinto
     *         de <code>null</code>.
     */
    protected Vertice intercambiaEliminable(Vertice vertice) {
        // Aquí va su código.
        Vertice verticeMaximo = MaximoEnSubArbol(vertice.izquierdo);

        T elementoMaximo = verticeMaximo.elemento;
        vertice.elemento = elementoMaximo;

        return verticeMaximo;
    }

    /**
     * Elimina un vértice que a lo más tiene un hijo distinto de
     * <code>null</code> subiendo ese hijo (si existe).
     * 
     * @param vertice el vértice a eliminar; debe tener a lo más un hijo
     *                distinto de <code>null</code>.
     */
    protected void eliminaVertice(Vertice vertice) {
        // Aquí va su código.
        Vertice hijo = vertice.izquierdo == null ? vertice.derecho : vertice.izquierdo;

        Vertice padre = vertice.padre;

        if (padre != null) {
            if (vertice == padre.izquierdo) {
                padre.izquierdo = hijo;
            } else {
                padre.derecho = hijo;
            }
        } else {
            raiz = hijo;
        }

        if (hijo != null) {
            hijo.padre = padre;
        }
    }

    /**
     * Busca un elemento en el árbol recorriéndolo in-order. Si lo encuentra,
     * regresa el vértice que lo contiene; si no, regresa <code>null</code>.
     * 
     * @param elemento el elemento a buscar.
     * @return un vértice que contiene al elemento buscado si lo
     *         encuentra; <code>null</code> en otro caso.
     */
    @Override
    public VerticeArbolBinario<T> busca(T elemento) {
        // Aquí va su código.
        return busca(raiz, elemento);
    }

    /**
     * Regresa el vértice que contiene el último elemento agregado al
     * árbol. Este método sólo se puede garantizar que funcione
     * <em>inmediatamente</em> después de haber invocado al método {@link
     * agrega}. Si cualquier operación distinta a agregar sobre el árbol se
     * ejecuta después de haber agregado un elemento, el comportamiento de este
     * método es indefinido.
     * 
     * @return el vértice que contiene el último elemento agregado al árbol, si
     *         el método es invocado inmediatamente después de agregar un
     *         elemento al árbol.
     */
    public VerticeArbolBinario<T> getUltimoVerticeAgregado() {
        // Aquí va su código.
        return ultimoAgregado;
    }

    /**
     * Gira el árbol a la derecha sobre el vértice recibido. Si el vértice no
     * tiene hijo izquierdo, el método no hace nada.
     * 
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraDerecha(VerticeArbolBinario<T> vertice) {
        // Aquí va su código.
        Vertice q = super.vertice(vertice);
        if (q == null || q.izquierdo == null || raiz == null)
            return;

        Vertice p = q.izquierdo;

        p.padre = q.padre;

        if (q != raiz) {
            if (q == q.padre.izquierdo)
                p.padre.izquierdo = p;
            else
                p.padre.derecho = p;
        } else {
            raiz = p;
        }

        q.izquierdo = p.derecho;
        if (p.derecho != null) {
            p.derecho.padre = q;
        }
        p.derecho = q;
        q.padre = p;
    }

    /**
     * Gira el árbol a la izquierda sobre el vértice recibido. Si el vértice no
     * tiene hijo derecho, el método no hace nada.
     * 
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        // Aquí va su código.
        Vertice p = super.vertice(vertice);

        if (p == null || p.derecho == null || raiz == null)
            return;

        Vertice q = p.derecho;

        q.padre = p.padre;

        if (p != raiz) {
            if (p == p.padre.izquierdo) {
                q.padre.izquierdo = q;
            } else {
                q.padre.derecho = q;
            }
        } else {
            raiz = q;
        }

        p.derecho = q.izquierdo;
        if (q.izquierdo != null)
            q.izquierdo.padre = p;
        q.izquierdo = p;
        p.padre = q;
    }

    /**
     * Realiza un recorrido DFS <em>pre-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * 
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPreOrder(AccionVerticeArbolBinario<T> accion) {
        // Aquí va su código.
        dfsPreOrder(raiz, accion);
    }

    /**
     * Realiza un recorrido DFS <em>in-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * 
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsInOrder(AccionVerticeArbolBinario<T> accion) {
        // Aquí va su código.
        dfsInOrder(raiz, accion);
    }

    /**
     * Realiza un recorrido DFS <em>post-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * 
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPostOrder(AccionVerticeArbolBinario<T> accion) {
        // Aquí va su código.
        dfsPostOrder(raiz, accion);
    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * 
     * @return un iterador para iterar el árbol.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    // Métodos auxiliares

    // Auxilar para agrega.
    private void auxAgrega(Vertice actual, Vertice nuevo) {
        if (actual.elemento.compareTo(nuevo.elemento) >= 0) {
            if (actual.izquierdo == null) {
                actual.izquierdo = nuevo;
                nuevo.padre = actual;
            } else {
                auxAgrega(actual.izquierdo, nuevo);
            }
        } else {
            if (actual.derecho == null) {
                actual.derecho = nuevo;
                nuevo.padre = actual;
            } else {
                auxAgrega(actual.derecho, nuevo);
            }
        }
    }

    // Auxiliar para elimina
    private Vertice MaximoEnSubArbol(Vertice vertice) {
        if (vertice.derecho == null)
            return vertice;
        return MaximoEnSubArbol(vertice.derecho);
    }

    // Auxiliar para busca()

    private Vertice busca(Vertice v, T e) {

        if (v == null)
            return null;

        Vertice vI = null, vD = null;

        if (e.compareTo(v.elemento) == 0)
            return v;
        else if (e.compareTo(v.elemento) < 0)
            vI = busca(v.izquierdo, e);
        else
            vD = busca(v.derecho, e);

        return vI == null ? vD : vI;
    }

    // Auxiliar de dfsPreOrder
    private void dfsPreOrder(Vertice v, AccionVerticeArbolBinario<T> a) {
        if (v == null)
            return;
        a.actua(v);
        dfsPreOrder(v.izquierdo, a);
        dfsPreOrder(v.derecho, a);
    }

    // Auxiliar de dfsInOrder
    private void dfsInOrder(Vertice v, AccionVerticeArbolBinario<T> a) {
        if (v == null)
            return;
        dfsInOrder(v.izquierdo, a);
        a.actua(v);
        dfsInOrder(v.derecho, a);
    }

    // Auxiliar de dfsPostOrder
    private void dfsPostOrder(Vertice v, AccionVerticeArbolBinario<T> a) {
        if (v == null)
            return;
        dfsPostOrder(v.izquierdo, a);
        dfsPostOrder(v.derecho, a);
        a.actua(v);
    }
}
