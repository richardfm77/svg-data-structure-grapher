package mx.unam.ciencias.edd.proyecto2;

/**
 * Interfaz fucional, para realizar una acción 
 * con los elementos de una {@link Instruccion}. 
 */

@FunctionalInterface
public interface AccionElementoInstruccion {
    /**
     * Acción para realizar por cada elemento.
     * @param e un elemeto de tipo entero.
     */
    public void actua(int e); 
}
