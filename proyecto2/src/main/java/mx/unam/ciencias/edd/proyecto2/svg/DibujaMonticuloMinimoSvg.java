package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.MonticuloMinimo;
import mx.unam.ciencias.edd.ValorIndexable;
import mx.unam.ciencias.edd.proyecto2.Instruccion;

/**
 * Clase para dibujar montículos mínimos en svg.
 * Hace una implemnetación concreta de la clase
 * {@link DibujaArbolAVLSvg}.
 */
public class DibujaMonticuloMinimoSvg extends DibujaArbolBinarioSvg<ArbolBinarioCompleto<Integer>> {

    private MonticuloMinimo<ValorIndexable<Integer>> monticuloMinimo;
    private int[] arreglo;

    private double separador;

    public DibujaMonticuloMinimoSvg(Instruccion instruccion) {
        super(instruccion);
    }

    @Override
    protected void agregaElementos() {
        arreglo = new int[instruccion.getElementos().length];
        monticuloMinimo = new MonticuloMinimo<>();
        agregaElementosMonticulo(instruccion);

        while (!monticuloMinimo.esVacia()) {
            Integer e = monticuloMinimo.elimina().getElemento();
            estructura.agrega(e);
        }

        Lista<Integer> aux = MonticuloMinimo.heapSort(estructura);

        int i = 0;
        for (Integer e : aux) {
            arreglo[i] = e;
            i++;
        }
    }

    @Override
    protected ArbolBinarioCompleto<Integer> creaEstructura() {
        return new ArbolBinarioCompleto<>();
    }

    @Override
    public String dibujaSvg() {
        double witdh = calculaWitdh() + 100;
        double height = calculaHeigth() + 300;

        String svg = SvgUlti.XML_PROLOG;

        svg += SvgUlti.getOpneSvg(witdh, height);

        svg += dibujaArbol(witdh);
        svg += dibujaArreglo(witdh);

        svg += SvgUlti.CLOSE_SVG;
        return svg;
    }

    private void agregaElementosMonticulo(Instruccion instruccion) {
        instruccion.paraCadaElemento(e -> {
            monticuloMinimo.agrega(new ValorIndexable<Integer>(e, e));
        });
    }

    private String dibujaArbol(double witdh) {

        Lista<VerticeArbolBinarioSvg> list = new Lista<>();

        list = calculaCordenadas(witdh);

        separador = list.getUltimo().getY();

        String svg = " " + SvgUlti.OPEN_G_TAG;
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
            svg += " " + SvgUlti.CLOSE_G_TAG;
        }

        return svg;
    }

    private String dibujaArreglo(double height) {

        String svg = "";

        int longitud = arreglo.length;
        double witdh = (60 * longitud) - 20;
        double x = 20 * (witdh / 100);
        double y = separador + 2 * SvgUlti.VERTEX_RADIUS + 200;

        for (Integer e : arreglo) {
            svg += " " + SvgUlti.OPEN_G_TAG;
            svg += "  " +
                    SvgUlti.getRec(x, y, SvgUlti.COLOR_BLACK, SvgUlti.COLOR_WHITE);
            svg += "  " +
                    SvgUlti.getText(SvgUlti.COLOR_BLACK, x + 15, y + 21, e);
            svg += " " + SvgUlti.CLOSE_G_TAG;
            x += 30;
        }
        return svg;
    }

}