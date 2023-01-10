package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.ArbolAVL;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.VerticeArbolBinario;
import mx.unam.ciencias.edd.proyecto2.Instruccion;

/**
 * Clase para dibujar Árboles AVL.
 * Hace una implementación concreta de la clase {@link DibujaArbolBinarioSvg}.  
 */

public class DibujaArbolAVLSvg extends DibujaArbolBinarioSvg<ArbolAVL<Integer>> {

    public DibujaArbolAVLSvg(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    protected ArbolAVL<Integer> creaEstructura() {
        return new ArbolAVL<>();
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
        for (VerticeArbolBinarioSvg v : list) {
            svg += " " + SvgUlti.OPEN_G_TAG;
            double cx = v.getX();
            double cy = v.getY();
            svg += "  " + SvgUlti.getCirc(cx, cy, SvgUlti.COLOR_BLUE);
            svg += "  " + SvgUlti.getText(SvgUlti.COLOR_WHITE, cx, cy + 5, v.getVertice().get());
            svg += "  " + SvgUlti.getText(SvgUlti.COLOR_BLACK, cx, cy - 25, getAlturaBlance(v.getVertice()));
            svg += " " + SvgUlti.CLOSE_G_TAG;
        }
        svg += SvgUlti.CLOSE_SVG;
        return svg;
    }

    private String getAlturaBlance(VerticeArbolBinario<Integer> v) {
        String array[] = v.toString().split(" ");
        return array[1];
    }
}
