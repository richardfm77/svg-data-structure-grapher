package mx.unam.ciencias.edd.proyecto2;

/**
 * Clase de intrucciones, 
 * una intrucción tiene una {@link Estructura},
 * y un arreglo con los elementos de la 
 * estructura de datos.   
 */
public class Instruccion {

    // La estructura de datos.
    private Estructura estructura;

    /* 
    * El arreglo con los elementos de 
    * la estructura de datos.
    */
    private int[] elementos;

    /**
     * Construye uan intrucción.
     * @param estructura la {@link Estructura}.
     * @param elementos los elementos de una 
     * estructura de datos.
     */
    public Instruccion(Estructura estructura, int[] elementos) {
        this.estructura = estructura;
        this.elementos = elementos;
    }

    /**
     * Define una {@link Estructura}
     * @param estructura
     */
    public void setEstructura(Estructura estructura){
        this.estructura = estructura;
    }

    /**
     * @return la {@link Estructura}.
     */
    public Estructura getEstructura(){
        return estructura;
    }

    /**
     *  Define los elementos.
     * @param elementos los 
     * elemntos de la estructura.
     */
    public void setElementos(int[] elementos){
        this.elementos = elementos;
    }

    /**
     * @return los elementos 
     * de una estructura de datos.
     */
    public int [] getElementos(){
        return elementos;
    }

    /**
     * Realiza una acción por cada 
     * elemento de la estructura de 
     * datos.
     * 
     * @param a acción a realizar por cada 
     * elemento de la estructura de datos. 
     */
    public void paraCadaElemento(AccionElementoInstruccion a){
        for(int e : elementos)
            a.actua(e);
    }

    /**
     * Regresa la representación en cadena 
     * de una instrucción.
     */
    @Override
    public String toString() {
        String elementosString = "";
        for(int e : elementos)
            elementosString += e + " "; 
        return estructura.toString() + " " + elementosString.trim();
    }

}
