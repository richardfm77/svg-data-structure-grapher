package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.Cola;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.Instruccion;

/**
 * Clase para dibujar colas en svg.
 * Hace una implementación concreta de la clase
 * {@link DibujaMeteSacaSvg}.
 */
public class DibujaColaSvg extends DibujaMeteSacaSvg<Cola<Integer>> {

    public DibujaColaSvg(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    protected Cola<Integer> creaEstructura() {
        return new Cola<>();
    }

    @Override
    public String dibujaSvg() {
        Lista<Integer> aux = new Lista<>();
        
        while(!estructura.esVacia()){
            aux.agregaFinal(estructura.saca());
        }

        String svg = SvgUlti.XML_PROLOG;
        int longitud = aux.getLongitud();
        double witdh = (80*longitud - 20);
        svg += SvgUlti.getOpneSvg(witdh, 100);
        double x = 15 * (witdh/140);
        double y = 40;

        int i = 0;

        for (Integer e : aux) {
            if (i >= longitud - 1)
                break;
            svg += " " + SvgUlti.OPEN_G_TAG;
            svg += "  " +
                    SvgUlti.getRec(x, y, SvgUlti.COLOR_BLACK, SvgUlti.COLOR_WHITE);
            svg += "  " +
                    SvgUlti.getText(SvgUlti.COLOR_BLACK, x + 15, y + 21, e);
            svg += "  " +
                    SvgUlti.getArrow(SvgUlti.COLOR_BLACK, x + 46, y + 21);
            svg += " " + SvgUlti.CLOSE_G_TAG;
            x += 15 + 46;
            i++;
        }

        svg += " " + SvgUlti.OPEN_G_TAG;
        svg += "  " +
                SvgUlti.getRec(x, y, SvgUlti.COLOR_BLACK, SvgUlti.COLOR_WHITE);
        svg += "  " +
                SvgUlti.getText(SvgUlti.COLOR_BLACK, x + 15, y + 21, aux.getUltimo());

        svg += " " + SvgUlti.CLOSE_G_TAG;
        svg += SvgUlti.CLOSE_SVG;

        return svg;
    }
}
