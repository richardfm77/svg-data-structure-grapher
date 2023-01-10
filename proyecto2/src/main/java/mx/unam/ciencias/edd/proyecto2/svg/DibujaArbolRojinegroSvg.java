package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.Color;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.Instruccion;

/**
 * Clase para dibujar Árboles rojinegros.
 * Hace una implementación concreta de la clase {@link DibujaArbolBinarioSvg}.  
 */
public class DibujaArbolRojinegroSvg
        extends DibujaArbolBinarioSvg<ArbolRojinegro<Integer>> {

    public DibujaArbolRojinegroSvg(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    protected ArbolRojinegro<Integer> creaEstructura() {
        return new ArbolRojinegro<>();
    }

    @Override
    public String dibujaSvg() {

        double witdh = calculaWitdh() + 100;
        double height = calculaHeigth();

        Lista<VerticeArbolBinarioSvg> list = new Lista<>();

        list = calculaCordenadas(witdh);

        String svg = SvgUlti.XML_PROLOG;
        svg += SvgUlti.getOpneSvg(witdh, height);
        svg += " " + SvgUlti.OPEN_G_TAG;
        for (VerticeArbolBinarioSvg v : list) {
            svg += v.getAristaPadre();
        }
        svg += " " + SvgUlti.CLOSE_G_TAG;
        String color = "";
        for (VerticeArbolBinarioSvg v : list) {
            color = estructura.getColor(v.getVertice()) == Color.ROJO ? SvgUlti.COLOR_RED : SvgUlti.COLOR_BLACK;
            double cx = v.getX();
            double cy = v.getY();
            svg += " " + SvgUlti.OPEN_G_TAG;
            svg += "  " + SvgUlti.getCirc(cx, cy, color);
            svg += "  " + SvgUlti.getText(SvgUlti.COLOR_WHITE, cx, cy + 5, v.getVertice().get());
            svg += " " + SvgUlti.CLOSE_G_TAG;
        }
        svg += SvgUlti.CLOSE_SVG;
        return svg;
    }
}
