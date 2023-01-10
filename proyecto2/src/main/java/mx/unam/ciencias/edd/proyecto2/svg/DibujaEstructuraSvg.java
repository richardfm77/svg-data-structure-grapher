package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.Coleccion;
import mx.unam.ciencias.edd.proyecto2.Instruccion;

/**
 * Clase abstracta para dibujar estructuras de 
 * datos en svg.
 */
public abstract class DibujaEstructuraSvg<T extends Coleccion<Integer>> {

    protected Instruccion instruccion;
    protected T estructura;

    public DibujaEstructuraSvg(Instruccion instruccion) {
        this.instruccion = instruccion;
        estructura = creaEstructura();
        agregaElementos();
    }

    public String dibujaEstructuraToString() {
        return estructura.toString();
    }

    abstract protected T creaEstructura();

    abstract public String dibujaSvg();

    protected void agregaElementos() {
        instruccion.paraCadaElemento((e) -> {
            estructura.agrega(e);
        });
    }
}
