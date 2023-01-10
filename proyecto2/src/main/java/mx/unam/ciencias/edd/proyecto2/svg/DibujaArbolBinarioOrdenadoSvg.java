package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.Instruccion;

/**
 * Clase para dibujar Árboles binarios ordenados.
 * Hace una implementación concreta de la clase {@link DibujaArbolBinarioSvg}.  
 */
public class DibujaArbolBinarioOrdenadoSvg extends DibujaArbolBinarioSvg<ArbolBinarioOrdenado<Integer>> {

    public DibujaArbolBinarioOrdenadoSvg(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    protected ArbolBinarioOrdenado<Integer> creaEstructura() {
        return new ArbolBinarioOrdenado<>();
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
        for(VerticeArbolBinarioSvg v : list){
            svg += v.getAristaPadre();
        }
        svg += " " + SvgUlti.CLOSE_G_TAG;
        for (VerticeArbolBinarioSvg v : list) {
            svg += " " + SvgUlti.OPEN_G_TAG;
            double cx = v.getX();
            double cy = v.getY();
            svg += "  " + SvgUlti.getCirc(cx, cy, SvgUlti.COLOR_BLUE);
            svg += "  " + SvgUlti.getText(SvgUlti.COLOR_WHITE, cx, cy + 5, v.getVertice().get());
            svg += " " + SvgUlti.CLOSE_G_TAG;
        }
        svg += SvgUlti.CLOSE_SVG;
        return svg;
    }
}
