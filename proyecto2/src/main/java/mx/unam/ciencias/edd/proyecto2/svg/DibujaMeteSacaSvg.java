package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.MeteSaca;
import mx.unam.ciencias.edd.proyecto2.Instruccion;

/**
 * Clase abstracta para dibujar en svg,
 * estrecturas que extiendan de mete saca.
 */
public abstract class DibujaMeteSacaSvg<T extends MeteSaca<Integer>> {

    Instruccion instruccion;
    protected T estructura;

    public DibujaMeteSacaSvg(Instruccion instruccion) {
        this.instruccion = instruccion;
        estructura = creaEstructura();
        agregaElementos();
    }

    public String dibujaEstructuraToString() {
        return estructura.toString();
    }

    abstract protected T creaEstructura();

    abstract public String dibujaSvg();

    // Métoos auxiliares
    private void agregaElementos(){
        instruccion.paraCadaElemento((e) -> {
            estructura.mete(e);
        });
    }

}
