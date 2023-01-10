package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.Instruccion;

/**
 * Clase para dibujar Listas en svg.
 * Hace una implemnetación concreta de la clase
 * {@link DibujaEstructuraSvg}.
 */
public class DibujaListaSvg extends DibujaEstructuraSvg<Lista<Integer>> {

    public DibujaListaSvg(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    protected Lista<Integer> creaEstructura() {
        return new Lista<>();
    }

    @Override
    public String dibujaSvg() {
        String svg = SvgUlti.XML_PROLOG;
        int longitud = estructura.getLongitud();
        double witdh = (80*longitud - 20);
        svg += SvgUlti.getOpneSvg(witdh, 100);
        double x = 15 * (witdh/140);
        double y = 40;

        int i = 0;

        for (Integer e : estructura) {
            if (i >= longitud - 1)
                break;
            svg += " " + SvgUlti.OPEN_G_TAG;
            svg += "  " +
                    SvgUlti.getRec(x, y, SvgUlti.COLOR_BLACK, SvgUlti.COLOR_WHITE);
            svg += "  " +
                    SvgUlti.getText(SvgUlti.COLOR_BLACK, x + 15, y + 21, e);
            svg += "  " +
                    SvgUlti.getArrowDouble(SvgUlti.COLOR_BLACK, x + 46, y + 21);
            svg += " " + SvgUlti.CLOSE_G_TAG;
            x += 15 + 46;
            i++;
        }

        svg += " " + SvgUlti.OPEN_G_TAG;
        svg += "  " +
                SvgUlti.getRec(x, y, SvgUlti.COLOR_BLACK, SvgUlti.COLOR_WHITE);
        svg += "  " +
                SvgUlti.getText(SvgUlti.COLOR_BLACK, x + 15, y + 21, estructura.getUltimo());

        svg += " " + SvgUlti.CLOSE_G_TAG;
        svg += SvgUlti.CLOSE_SVG;

        return svg;
    }

}
