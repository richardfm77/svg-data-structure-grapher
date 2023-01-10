package mx.unam.ciencias.edd;

import java.util.NoSuchElementException;

/**
 * Interfaz para vértices de árboles binarios.
 */
public interface VerticeArbolBinario<T> {

    /**
     * Nos dice si el vértice tiene vértice padre.
     * @return <code>true</code> si el vértice tiene vértice padre,
     *         <code>false</code> en otro caso.
     */
    public boolean hayPadre();

    /**
     * Nos dice si el vértice tiene vértice izquierdo.
     * @return <code>true</code> si el vértice tiene vértice izquierdo,
     *         <code>false</code> en otro caso.
     */
    public boolean hayIzquierdo();

    /**
     * Nos dice si el vértice tiene vértice derecho.
     * @return <code>true</code> si el vértice tiene vértice derecho,
     *         <code>false</code> en otro caso.
     */
    public boolean hayDerecho();

    /**
     * Regresa el vértice padre del vértice.
     * @return el vértice padre del vértice.
     * @throws NoSuchElementException si el vértice no tiene padre.
     */
    public VerticeArbolBinario<T> padre();

    /**
     * Regresa el vértice izquierdo del vértice.
     * @return el vértice izquierdo del vértice.
     * @throws NoSuchElementException si el vértice no tiene izquierdo.
     */
    public VerticeArbolBinario<T> izquierdo();

    /**
     * Regresa el vértice derecho del vértice.
     * @return el vértice derecho del vértice.
     * @throws NoSuchElementException si el vértice no tiene derecho.
     */
    public VerticeArbolBinario<T> derecho();

    /**
     * Regresa la altura del vértice.
     * @return la altura del vértice.
     */
    public int altura();

    /**
     * Regresa la profundidad del vértice.
     * @return la profundidad del vértice.
     */
    public int profundidad();

    /**
     * Regresa el elemento que contiene el vértice.
     * @return el elemento que contiene el vértice.
     */
    public T get();
}
