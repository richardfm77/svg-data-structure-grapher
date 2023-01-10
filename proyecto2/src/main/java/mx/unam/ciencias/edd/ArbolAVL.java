package mx.unam.ciencias.edd;

/**
 * <p>
 * Clase para árboles AVL.
 * </p>
 *
 * <p>
 * Un árbol AVL cumple que para cada uno de sus vértices, la diferencia entre
 * la áltura de sus subárboles izquierdo y derecho está entre -1 y 1.
 * </p>
 */
public class ArbolAVL<T extends Comparable<T>>
        extends ArbolBinarioOrdenado<T> {

    /**
     * Clase interna protegida para vértices.
     */
    protected class VerticeAVL extends Vertice {

        /** La altura del vértice. */
        public int altura;

        /**
         * Constructor único que recibe un elemento.
         * 
         * @param elemento el elemento del vértice.
         */
        public VerticeAVL(T elemento) {
            // Aquí va su código.
            super(elemento);
        }

        /**
         * Regresa la altura del vértice.
         * 
         * @return la altura del vértice.
         */
        @Override
        public int altura() {
            // Aquí va su código.
            return altura;
        }

        /**
         * Regresa una representación en cadena del vértice AVL.
         * 
         * @return una representación en cadena del vértice AVL.
         */
        @Override
        public String toString() {
            // Aquí va su código.
            int balance = getBalance(this);
            String cadena = String.format("%s %d/%d",
                    elemento.toString(),
                    altura, balance);
            return cadena;
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * 
         * @param objeto el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeAVL}, su elemento es igual al elemento de éste
         *         vértice, los descendientes de ambos son recursivamente
         *         iguales, y las alturas son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override
        public boolean equals(Object objeto) {
            if (objeto == null || getClass() != objeto.getClass())
                return false;
            @SuppressWarnings("unchecked")
            VerticeAVL vertice = (VerticeAVL) objeto;
            // Aquí va su código.
            return super.equals(vertice);
        }
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinarioOrdenado}.
     */
    public ArbolAVL() {
        super();
    }

    /**
     * Construye un árbol AVL a partir de una colección. El árbol AVL tiene los
     * mismos elementos que la colección recibida.
     * 
     * @param coleccion la colección a partir de la cual creamos el árbol AVL.
     */
    public ArbolAVL(Coleccion<T> coleccion) {
        // Aquí va su código.
        super(coleccion);
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link VerticeAVL}.
     * 
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice con el elemento recibido dentro del mismo.
     */
    @Override
    protected Vertice nuevoVertice(T elemento) {
        // Aquí va su código.
        return new VerticeAVL(elemento);
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioOrdenado#agrega}, y después balancea el árbol girándolo como
     * sea necesario.
     * 
     * @param elemento el elemento a agregar.
     */
    @Override
    public void agrega(T elemento) {
        // Aquí va su código.
        super.agrega(elemento);
        VerticeAVL v = (VerticeAVL) ultimoAgregado;
        v.altura = 0;
        rebalanceo((VerticeAVL) v.padre);
    }

    /**
     * Elimina un elemento del árbol. El método elimina el vértice que contiene
     * el elemento, y gira el árbol como sea necesario para rebalancearlo.
     * 
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override
    public void elimina(T elemento) {
        // Aquí va su código.
        VerticeAVL v = (VerticeAVL) busca(elemento);
        if (v == null)
            return;
        elementos--;
        if (v.izquierdo != null && v.derecho != null)
            v = (VerticeAVL) intercambiaEliminable(v);
        eliminaVertice(v);
        rebalanceo((VerticeAVL) v.padre);
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles AVL
     * no pueden ser girados a la derecha por los usuarios de la clase, porque
     * se desbalancean.
     * 
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override
    public void giraDerecha(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles AVL no  pueden " +
                "girar a la izquierda por el " +
                "usuario.");
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles AVL
     * no pueden ser girados a la izquierda por los usuarios de la clase, porque
     * se desbalancean.
     * 
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override
    public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles AVL no  pueden " +
                "girar a la derecha por el " +
                "usuario.");
    }

    // Métodos auxiliares

    // Algoritmo de rebalanceo para árboles AVL
    private void rebalanceo(VerticeAVL v) {
        if (v == null)
            return;

        v.altura = calculaAltrua(v);
        int b = getBalance(v);
        int H = v.altura;

        // Caso 1
        if (b == -2) {
            VerticeAVL q = (VerticeAVL) v.derecho;
            VerticeAVL x = (VerticeAVL) q.izquierdo;

            if (getBalance(q) == 1) {
                super.giraDerecha(q);
                q.altura = H - 2;
                x.altura = H - 1;
                q = (VerticeAVL) v.derecho;
                x = (VerticeAVL) q.izquierdo;
            }

            H = calculaAltrua(v);
            super.giraIzquierda(v);
            int Hx = calculaAltrua(x); 

            if (Hx == H - 2) {
                v.altura = H - 1;
                q.altura = H;
            } else if (Hx == H - 3 || Hx == H - 4) {
                v.altura = H - 2;
                q.altura = H - 1;
            }
            v = q;
        }

        // Caso 2
        if (b == 2) {
            VerticeAVL p = (VerticeAVL) v.izquierdo;
            VerticeAVL y = (VerticeAVL) p.derecho;

            if (getBalance(p) == -1) {
                super.giraIzquierda(p);
                y.altura = H - 1;
                p.altura = H - 2;

                p = (VerticeAVL) v.izquierdo;
                y = (VerticeAVL) p.derecho;
            }

            H = calculaAltrua(v);
            super.giraDerecha(v);
            int Hy = calculaAltrua(y);

            if (Hy == H - 2) {
                v.altura = H - 1;
                p.altura = H;
            } else if (Hy == H - 3  || Hy == H - 4) {
                v.altura = H - 2;
                p.altura = H - 1;
            }
            v = p;
        }
        // Recursión
        rebalanceo((VerticeAVL) v.padre);
    }

    // Calcula el balance de un vérticeAVL.
    private int getBalance(VerticeAVL v) {
        return calculaAltrua((VerticeAVL) v.izquierdo)
                -
                calculaAltrua((VerticeAVL) v.derecho);
    }

    // Calcula la altura de un vérticeAVL.
    private int calculaAltrua(VerticeAVL v) {
        if (v == null)
            return -1;

        VerticeAVL vI = (VerticeAVL) v.izquierdo;
        VerticeAVL vD = (VerticeAVL) v.derecho;

        int alturaI = vI != null ? vI.altura : -1;
        int alturaD = vD != null ? vD.altura : -1;

        return Math.max(alturaI, alturaD) + 1;
    }

}
