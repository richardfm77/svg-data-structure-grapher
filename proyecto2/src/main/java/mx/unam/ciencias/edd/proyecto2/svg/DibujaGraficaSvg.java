package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.VerticeGrafica;
import mx.unam.ciencias.edd.proyecto2.Instruccion;
/**
 * Clase para dibujar gráficas en svg.
 * Hace una implemnetación concreta de la clase
 * {@link DibujaEstructuraSvg}.
 */
public class DibujaGraficaSvg extends DibujaEstructuraSvg<Grafica<Integer>> {

    private class VerticeGraficaSvg {

        private VerticeGrafica<Integer> v;

        private double x;
        private double y;

        private VerticeGraficaSvg(VerticeGrafica<Integer> v, double x, double y) {
            this.v = v;
            this.x = x;
            this.y = y;
        }
    }

    public DibujaGraficaSvg(Instruccion instruccion) {
        super(instruccion);
        conecta(instruccion.getElementos());
    }

    @Override
    protected void agregaElementos() {
        instruccion.paraCadaElemento(e -> {
            try {
                estructura.agrega(e);
            } catch (IllegalArgumentException exep) {
            }
        });
    }

    @Override
    protected Grafica<Integer> creaEstructura() {
        return new Grafica<>();
    }

    @Override
    public String dibujaSvg() {

        Lista<VerticeGraficaSvg> aux = new Lista<>();
        estructura.paraCadaVertice(v -> {
            aux.agrega(new VerticeGraficaSvg(v, 0, 0));
        });

        final double DIAMETRO = 60 * estructura.getElementos();
        final double RADIO = DIAMETRO / 2;
        final double HK = RADIO + 50;
        double witdhAndHeight;
        witdhAndHeight = 100 + DIAMETRO;

        calculaCordenadas(aux, RADIO, HK);

        String svg = SvgUlti.XML_PROLOG;
        svg += SvgUlti.getOpneSvg(witdhAndHeight, witdhAndHeight);
        for (VerticeGraficaSvg v : aux) {
            svg += " " + SvgUlti.OPEN_G_TAG;
            svg += dibujaAristas(v.v, aux, v.x, v.y);
            svg += " " + SvgUlti.CLOSE_G_TAG;
        }
        for (VerticeGraficaSvg v : aux) {
            svg += " " + SvgUlti.OPEN_G_TAG;
            svg += "  " + SvgUlti.getCirc(v.x, v.y, SvgUlti.COLOR_BLUE);
            svg += "  " + SvgUlti.getText(SvgUlti.COLOR_WHITE, v.x, v.y + 5, v.v.get());
            svg += " " + SvgUlti.CLOSE_G_TAG;
        }
        svg += SvgUlti.CLOSE_SVG;
        return svg;
    }

    private void conecta(int elementos[]) {
        try {
            int totalElementos = elementos.length;
            for (int i = 0; i < totalElementos; i += 2) {
                if (i + 1 >= totalElementos)
                    continue;
                if (elementos[i] == elementos[i + 1])
                    continue;
                estructura.conecta(elementos[i], elementos[i + 1]);
            }
        } catch (IllegalArgumentException e) {
        }
    }

    private void calculaCordenadas(Lista<VerticeGraficaSvg> list, double r, double hk) {
        int numElementos = estructura.getElementos();
        double angulo = 0;
        double deltaAngulo = (2 * Math.PI) / numElementos;
        for (VerticeGraficaSvg v : list) {
            v.x = hk + Math.cos(angulo) * r;
            v.y = hk + Math.sin(angulo) * r;
            angulo += deltaAngulo;
        }
    }

    private String dibujaAristas(VerticeGrafica<Integer> v1,
            Lista<VerticeGraficaSvg> aux, double x1, double y1) {
        String svg = "";
        for (VerticeGrafica<Integer> e : v1.vecinos()) {
            for (VerticeGraficaSvg v2 : aux) {
                if (e.get() == v2.v.get()) {
                    svg += "  " + SvgUlti.getLine(x1, y1, v2.x, v2.y, SvgUlti.COLOR_BLACK);
                    break;
                }
            }
        }
        return svg;
    }
}
