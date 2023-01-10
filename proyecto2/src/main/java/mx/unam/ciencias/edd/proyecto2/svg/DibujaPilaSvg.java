package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.Pila;
import mx.unam.ciencias.edd.proyecto2.Instruccion;

/**
 * Clase para dibujar montículos pilas en svg.
 * Hace una implemnetación concreta de la clase
 * {@link DibujaMeteSacaSvg}.
 */
public class DibujaPilaSvg extends DibujaMeteSacaSvg<Pila<Integer>>{
    
    public DibujaPilaSvg(Instruccion instruccion){
        super(instruccion);
    }
    
    @Override
    protected Pila<Integer> creaEstructura() {
        return new Pila<>();
    }

    @Override
    public String dibujaSvg() {

        Lista<Integer> aux = new Lista<>();

        while(!estructura.esVacia()){
            aux.agregaFinal(estructura.saca());
        }

        String svg = SvgUlti.XML_PROLOG;

        double liongitud = aux.getElementos();

        svg += SvgUlti.getOpneSvg(100, 49*liongitud);

        double x = 36;
        double y = 10 *(liongitud);

        for(Integer e : aux){
            svg += " " + SvgUlti.OPEN_G_TAG;
            svg += "  " +
                    SvgUlti.getRec(x, y, SvgUlti.COLOR_BLACK, SvgUlti.COLOR_WHITE);
            svg += "  " +
                    SvgUlti.getText(SvgUlti.COLOR_BLACK, x + 15, y + 21, e);
            svg += " " + SvgUlti.CLOSE_G_TAG;
            y += 30;
        }

        svg += SvgUlti.CLOSE_SVG;

        return svg;
    }
}
