package habitaciones.dominio.modelos;

/**
 * La clase Color representa varios colores de los muebles
 *
 */

public class Color {
    protected int red;
    protected int green;
    protected int blue;

    /**
     * Crear color
     *
     * @param red Color rojo
     * @param green Color green
     * @param blue Color blue
     */
    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Crear color copia
     *
     * @param Color color a copiar
     */
    public Color(Color color) {
        this.red = color.red;
        this.green = color.green;
        this.blue = color.blue;
    }

    /**
     *
     * @return Devuelve el color rojo
     */
    public int getRed() {
        return red;
    }

    /**
     *
     * @return Devuelve el color verde
     */
    public int getGreen() {
        return green;
    }

    /**
     *
     * @return Devuelve el color azul
     */
    public int getBlue() {
        return blue;
    }
}
