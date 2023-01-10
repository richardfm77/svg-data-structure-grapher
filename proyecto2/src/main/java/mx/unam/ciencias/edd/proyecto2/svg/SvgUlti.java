package mx.unam.ciencias.edd.proyecto2.svg;

/**
 * Clase para guardar todas las utilidades,
 * del entorno SVG. 
 */
public class SvgUlti {

        static final String XML_PROLOG = "<?xml version='1.0' encoding='UTF-8' ?>\n";
        static final String CLOSE_SVG = "</svg>\n";

        static final String OPEN_G_TAG = "<g>\n";
        static final String CLOSE_G_TAG = "</g>\n";

        static final String FONT_SANS_SERIF = "sans-serif";
        static final String FONT_TEXT_ANCHOR = "middle";
        static final int FONT_SIZE_TEXT = 20;

        static final int FONT_SIZE_ARROW = 30;
        static final String TEXT_LEFT_RIGHT_ARROW = "↔";
        static final String TEXT_RIGHTWARD_ARROW = "→";

        static final double VERTEX_RADIUS = 20;

        static final String COLOR_WHITE = "#B2BEBF";
        static final String COLOR_GREY = "#889C9B";
        static final String COLOR_BLUE = "#486966";
        static final String COLOR_BLACK = "#3B3936";
        static final String COLOR_RED = "#BD2A2E";

        public static String getOpnegTagWithIndex(String index) {
                return String.format("<g id='%s'>\n", index);
        }

        public static String getOpneSvg(double witdh, double height) {
                return String.format("<svg width='%.2f' height='%.2f'> \n", witdh, height);
        }

        public static String getText(String color,
                        double x, double y, Integer e) {
                return String.format(
                                "<text fill='%s' font-family='%s' font-size='%s' x='%.2f' y='%.2f' text-anchor='%s'>%d</text>\n",
                                color, FONT_SANS_SERIF, FONT_SIZE_TEXT,
                                x, y, FONT_TEXT_ANCHOR, e);
        }

        public static String getText(String color,
                        double x, double y, String e) {
                return String.format(
                                "<text fill='%s' font-family='%s' font-size='%s' x='%.2f' y='%.2f' text-anchor='%s'>%s</text>\n",
                                color, FONT_SANS_SERIF, FONT_SIZE_TEXT,
                                x, y, FONT_TEXT_ANCHOR, e);
        }

        public static String getArrowDouble(String color,
                        double x, double y) {
                return String.format(
                                "<text fill='%s' font-family='%s' font-size='%s' x='%.2f' y='%.2f' text-anchor='%s'>%s</text>\n",
                                color, FONT_SANS_SERIF, FONT_SIZE_ARROW,
                                x, y, FONT_TEXT_ANCHOR, TEXT_LEFT_RIGHT_ARROW);
        }

        public static String getArrow(String color,
                        double x, double y) {
                return String.format(
                                "<text fill='%s' font-family='%s' font-size='%s' x='%.2f' y='%.2f' text-anchor='%s'>%s</text>\n",
                                color, FONT_SANS_SERIF, FONT_SIZE_ARROW,
                                x, y, FONT_TEXT_ANCHOR, TEXT_RIGHTWARD_ARROW);
        }

        public static String getRec(double x, double y, String colorBorde,
                        String colorFondo) {
                return String.format(
                                "<rect x='%.2f' y='%.2f' width='30' height='30' stroke='%s' stroke-width='1' fill='%s' />\n",
                                x, y, colorBorde, colorFondo);
        }

        public static String getLine(double x1, double y1, double x2,
                        double y2, String color) {
                return String.format("<line x1='%.2f' y1='%.2f' x2='%.2f' y2='%.2f' stroke='%s' stroke-width='3' />\n",
                                x1, y1, x2, y2, color);
        }

        public static String getCirc(double cx, double cy, String colorFondo) {
                return String.format("<circle cx='%.2f' cy='%.2f' r='%.2f' stroke-width='0' fill='%s' />\n",
                                cx, cy, VERTEX_RADIUS, colorFondo);
        }

}
