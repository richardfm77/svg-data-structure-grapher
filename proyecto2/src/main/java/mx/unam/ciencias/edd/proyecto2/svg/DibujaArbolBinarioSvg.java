package mx.unam.ciencias.edd.proyecto2.svg;

import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.VerticeArbolBinario;
import mx.unam.ciencias.edd.proyecto2.Instruccion;

/**
 * Clase abstracta para dibujar árboles binarios
 * en svg.
 *  
 */
public abstract class DibujaArbolBinarioSvg<T extends ArbolBinario<Integer>>
        extends DibujaEstructuraSvg<T> {

    public class VerticeArbolBinarioSvg {

        private VerticeArbolBinario<Integer> v;
        private double x;
        private double y;

        private String aristaPadre;

        protected VerticeArbolBinarioSvg(VerticeArbolBinario<Integer> v, double x, double y) {
            this.v = v;
            this.x = x;
            this.y = y;
        }

        protected VerticeArbolBinario<Integer> getVertice() {
            return v;
        }

        protected void setVertice(VerticeArbolBinario<Integer> v) {
            this.v = v;
        }

        protected double getX() {
            return x;
        }

        protected void setX(double x) {
            this.x = x;
        }

        protected double getY() {
            return y;
        }

        protected void setY(double y) {
            this.y = y;
        }

        protected String getAristaPadre() {
            return aristaPadre;
        }

        protected void setAristaPadre(String aristaPadre) {
            this.aristaPadre = aristaPadre;
        }

    }

    public DibujaArbolBinarioSvg(Instruccion instruccion) {
        super(instruccion);
    }

    protected double calculaWitdh() {
        int h = estructura.altura();
        double apmlitud = 0;
        double deltaX = 0;
        double sum = 0;
        while (h > 0) {
            apmlitud = Math.pow(2, h);
            deltaX = apmlitud != 1 ? 40 * (apmlitud - 1) : 40;
            sum += 2 * (deltaX + 2 * SvgUlti.VERTEX_RADIUS);
            h--;
        }
        return sum;
    }

    protected double calculaHeigth() {
        int h = estructura.altura();
        final double YSEPARADOR = 70;
        return 2 * SvgUlti.VERTEX_RADIUS * h + (h + 2) * YSEPARADOR;
    }

    protected Lista<VerticeArbolBinarioSvg> calculaCordenadas(double witdh) {
        Lista<VerticeArbolBinarioSvg> list = new Lista<>();
        VerticeArbolBinario<Integer> t = estructura.raiz();
        double x = witdh / 2;
        double y = 70 + SvgUlti.VERTEX_RADIUS;
        VerticeArbolBinarioSvg v = new VerticeArbolBinarioSvg(t, x, y);
        v.setAristaPadre("");
        list.agrega(v);
        auxRecorreRecursivo(v, list, estructura.altura());
        return list;
    }

    private void auxRecorreRecursivo(VerticeArbolBinarioSvg v,
            Lista<VerticeArbolBinarioSvg> list, int h) {

        VerticeArbolBinarioSvg izquierdo;
        VerticeArbolBinarioSvg derecho;
        double y = v.y + 2 * SvgUlti.VERTEX_RADIUS + 70;
        double x = 0;
        double apmlitud = Math.pow(2, h);
        double deltaX = apmlitud != 1 ? 40 * (apmlitud - 1) : 40;

        if (v.getVertice().hayIzquierdo()) {
            x = v.x - 2 * SvgUlti.VERTEX_RADIUS - deltaX;
            izquierdo = new VerticeArbolBinarioSvg(v.getVertice().izquierdo(), x, y);
            izquierdo.setAristaPadre("  " + SvgUlti.getLine(x, y, v.x, v.y, SvgUlti.COLOR_BLACK));
            list.agrega(izquierdo);
            auxRecorreRecursivo(izquierdo, list, h - 1);
        }
        if (v.getVertice().hayDerecho()) {
            x = v.x + 2 * SvgUlti.VERTEX_RADIUS + deltaX;
            derecho = new VerticeArbolBinarioSvg(v.getVertice().derecho(), x, y);
            derecho.setAristaPadre("  " + SvgUlti.getLine(x, y, v.x, v.y, SvgUlti.COLOR_BLACK));
            list.agrega(derecho);
            auxRecorreRecursivo(derecho, list, h - 1);
        }
    }

}
