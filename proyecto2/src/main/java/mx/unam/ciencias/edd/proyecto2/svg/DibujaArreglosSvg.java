package mx.unam.ciencias.edd.proyecto2.svg;

/**
 * Clase para bibujar Arreglos en SVG.
 */
public class DibujaArreglosSvg {
    
    private int [] arreglo;

    public DibujaArreglosSvg(int [] arreglo){
        this.arreglo = arreglo;
    }

    public String dibujaSvg(){
        
        String svg = SvgUlti.XML_PROLOG;

        int longitud = arreglo.length;
        double witdh = (60*longitud) - 20;

        svg += SvgUlti.getOpneSvg(witdh, 100);
        double x = 20 * (witdh / 100);
        double y = 40;

        for(Integer e : arreglo){
            svg += " " + SvgUlti.OPEN_G_TAG;
            svg += "  " +
                    SvgUlti.getRec(x, y, SvgUlti.COLOR_BLACK, SvgUlti.COLOR_WHITE);
            svg += "  " +
                    SvgUlti.getText(SvgUlti.COLOR_BLACK, x + 15, y + 21, e);
            svg += " " + SvgUlti.CLOSE_G_TAG;
            x += 30;
        }

        svg += SvgUlti.CLOSE_SVG;

        return svg;
    }

}
