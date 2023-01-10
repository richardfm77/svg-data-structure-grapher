package mx.unam.ciencias.edd;

/**
 * Clase para árboles rojinegros. Un árbol rojinegro cumple las siguientes
 * propiedades:
 *
 * <ol>
 * <li>Todos los vértices son NEGROS o ROJOS.</li>
 * <li>La raíz es NEGRA.</li>
 * <li>Todas las hojas (<code>null</code>) son NEGRAS (al igual que la
 * raíz).</li>
 * <li>Un vértice ROJO siempre tiene dos hijos NEGROS.</li>
 * <li>Todo camino de un vértice a alguna de sus hojas descendientes tiene el
 * mismo número de vértices NEGROS.</li>
 * </ol>
 *
 * Los árboles rojinegros se autobalancean.
 */
public class ArbolRojinegro<T extends Comparable<T>>
        extends ArbolBinarioOrdenado<T> {

    /**
     * Clase interna protegida para vértices.
     */
    protected class VerticeRojinegro extends Vertice {

        /** El color del vértice. */
        public Color color;

        /**
         * Constructor único que recibe un elemento.
         * 
         * @param elemento el elemento del vértice.
         */
        public VerticeRojinegro(T elemento) {
            // Aquí va su código.
            super(elemento);
            color = Color.NINGUNO;
        }

        /**
         * Regresa una representación en cadena del vértice rojinegro.
         * 
         * @return una representación en cadena del vértice rojinegro.
         */
        @Override
        public String toString() {
            // Aquí va su código.
            String e = super.toString();
            if (color == Color.NEGRO)
                return "N{" + e + "}";
            else
                return "R{" + e + "}";
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * 
         * @param objeto el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeRojinegro}, su elemento es igual al elemento de
         *         éste vértice, los descendientes de ambos son recursivamente
         *         iguales, y los colores son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override
        public boolean equals(Object objeto) {
            if (objeto == null || getClass() != objeto.getClass())
                return false;
            @SuppressWarnings("unchecked")
            VerticeRojinegro vertice = (VerticeRojinegro) objeto;
            // Aquí va su código.
            return (color == vertice.color &&
                    super.equals(vertice));
        }
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinarioOrdenado}.
     */
    public ArbolRojinegro() {
        super();
    }

    /**
     * Construye un árbol rojinegro a partir de una colección. El árbol
     * rojinegro tiene los mismos elementos que la colección recibida.
     * 
     * @param coleccion la colección a partir de la cual creamos el árbol
     *                  rojinegro.
     */
    public ArbolRojinegro(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link
     * VerticeRojinegro}.
     * 
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice rojinegro con el elemento recibido dentro del mismo.
     */
    @Override
    protected Vertice nuevoVertice(T elemento) {
        // Aquí va su código.
        return new VerticeRojinegro(elemento);
    }

    /**
     * Regresa el color del vértice rojinegro.
     * 
     * @param vertice el vértice del que queremos el color.
     * @return el color del vértice rojinegro.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *                            VerticeRojinegro}.
     */
    public Color getColor(VerticeArbolBinario<T> vertice) {
        // Aquí va su código.
        VerticeRojinegro vRN = (VerticeRojinegro) vertice;
        return vRN.color;
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioOrdenado#agrega}, y después balancea el árbol recoloreando
     * vértices y girando el árbol como sea necesario.
     * 
     * @param elemento el elemento a agregar.
     */
    @Override
    public void agrega(T elemento) {
        // Aquí va su código.
        super.agrega(elemento);

        VerticeRojinegro v = (VerticeRojinegro) ultimoAgregado;
        v.color = Color.ROJO;

        agrega(v);
    }

    /**
     * Elimina un elemento del árbol. El método elimina el vértice que contiene
     * el elemento, y recolorea y gira el árbol como sea necesario para
     * rebalancearlo.
     * 
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override
    public void elimina(T elemento) {
        // Aquí va su código.
        VerticeRojinegro v = (VerticeRojinegro) busca(elemento);

        if (v == null)
            return;

        elementos--;

        if (v.izquierdo != null && v.derecho != null)
            v = (VerticeRojinegro) intercambiaEliminable(v);

        VerticeRojinegro fantasma = null;

        if (v.izquierdo == null && v.derecho == null) {
            fantasma = (VerticeRojinegro) nuevoVertice(null);
            fantasma.color = Color.NEGRO;
            v.izquierdo = fantasma;
            fantasma.padre = v;
        }

        VerticeRojinegro h = (VerticeRojinegro) 
        (v.izquierdo == null ? v.derecho : v.izquierdo);

        eliminaVertice(v);

        if (h.color == Color.ROJO) {
            h.color = Color.NEGRO;
            return;
        }

        if (v.color == Color.NEGRO && h.color == Color.NEGRO)
            rebalanceo(h);

        if (fantasma != null)
            eliminaVertice(fantasma);
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la izquierda por los usuarios de la
     * clase, porque se desbalancean.
     * 
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override
    public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                "pueden girar a la izquierda " +
                "por el usuario.");
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la derecha por los usuarios de la
     * clase, porque se desbalancean.
     * 
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override
    public void giraDerecha(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                "pueden girar a la derecha " +
                "por el usuario.");
    }

    // Métodos auxiliares

    // Auxiliar de agrega.
    private void agrega(VerticeRojinegro v) {
        // Caso 1
        if (v.padre == null) {
            v.color = Color.NEGRO;
            return;
        }

        VerticeRojinegro p = (VerticeRojinegro) v.padre;

        // Caso 2
        if (p.color == Color.NEGRO)
            return;

        VerticeRojinegro a = (VerticeRojinegro) p.padre;
        VerticeRojinegro t = (VerticeRojinegro) 
        (p == a.izquierdo ? a.derecho : a.izquierdo);

        // Caso 3
        if (t != null && t.color == Color.ROJO) {
            t.color = Color.NEGRO;
            p.color = Color.NEGRO;
            a.color = Color.ROJO;
            agrega(a);
            return;
        }

        // true: si p es dercho; false: si p es izquierdo.
        boolean ladoP = a.derecho == p;
        // true: si v es izquierdo; false: si v es derecho.
        boolean ladoV = p.izquierdo == v;

        VerticeRojinegro temporal = p;

        // Caso 4
        if ((!ladoP) && (!ladoV) || (ladoP) && (ladoV)) {
            if (ladoP) {
                super.giraDerecha(p);
            } else {
                super.giraIzquierda(p);
            }
            p = v;
            v = temporal;
        }

        // Caso 5
        p.color = Color.NEGRO;
        a.color = Color.ROJO;

        if (v == p.izquierdo)
            super.giraDerecha(a);
        else
            super.giraIzquierda(a);
    }

    // Método de rebalanceo de elimina.
    private void rebalanceo(VerticeRojinegro v) {
        // Caso 1
        if (v.padre == null)
            return;

        VerticeRojinegro p = (VerticeRojinegro) v.padre;

        VerticeRojinegro h = (VerticeRojinegro) 
        (p.izquierdo == v ? p.derecho : p.izquierdo);

        // Caso 2
        if (h.color == Color.ROJO && p.color == Color.NEGRO) {
            p.color = Color.ROJO;
            h.color = Color.NEGRO;

            if (p.izquierdo == v)
                super.giraIzquierda(p);
            else
                super.giraDerecha(p);

            h = (VerticeRojinegro) (p.izquierdo == v ? p.derecho : p.izquierdo);
        }

        VerticeRojinegro hi = (VerticeRojinegro) h.izquierdo;
        VerticeRojinegro hd = (VerticeRojinegro) h.derecho;

        // Caso 3
        if (p.color == Color.NEGRO && h.color == Color.NEGRO) {
            if (hi == null && hd == null) {
                h.color = Color.ROJO;
                rebalanceo(p);
                return;
            }

            if (hi != null && hd != null) {
                if (hi.color == Color.NEGRO && hd.color == Color.NEGRO) {
                    h.color = Color.ROJO;
                    rebalanceo(p);
                    return;
                }
            }
        }

        // Caso 4
        if (h.color == Color.NEGRO && p.color == Color.ROJO) {
            if (hi == null && hd == null) {
                h.color = Color.ROJO;
                p.color = Color.NEGRO;
                return;
            }

            if (hi != null && hd != null) {
                if (hi.color == Color.NEGRO && hd.color == Color.NEGRO) {
                    h.color = Color.ROJO;
                    p.color = Color.NEGRO;
                    return;
                }
            }
        }

        // Caso 5

        Color colorHi = Color.NINGUNO;
        if (hi == null)
            colorHi = Color.NEGRO;
        else if (hi.color == Color.ROJO)
            colorHi = Color.ROJO;
        else
            colorHi = Color.NEGRO;

        Color colorHd = Color.NINGUNO;
        if (hd == null)
            colorHd = Color.NEGRO;
        else if (hd.color == Color.ROJO)
            colorHd = Color.ROJO;
        else
            colorHd = Color.NEGRO;

        if ((p.izquierdo == v && colorHi == Color.ROJO && colorHd == Color.NEGRO)
                ||
            (p.derecho == v && colorHi == Color.NEGRO && colorHd == Color.ROJO)) {

            h.color = Color.ROJO;

            if (colorHi == Color.ROJO)
                hi.color = Color.NEGRO;
            else if (hd != null)
                hd.color = Color.NEGRO;

            if (p.izquierdo == v)
                super.giraDerecha(h);
            else
                super.giraIzquierda(h);

            h = (VerticeRojinegro) (p.izquierdo == v ? p.derecho : p.izquierdo);
            hi = (VerticeRojinegro) h.izquierdo;
            hd = (VerticeRojinegro) h.derecho;
        }

        // Caso 6
        h.color = p.color;
        p.color = Color.NEGRO;

        if (p.izquierdo == v) {
            if (hd != null)
                hd.color = Color.NEGRO;
            super.giraIzquierda(p);
        } else {
            //if (hi != null)
                hi.color = Color.NEGRO;
            super.giraDerecha(p);
        }
    }

}
