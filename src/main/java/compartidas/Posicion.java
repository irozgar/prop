package compartidas;

public class Posicion {
    protected int x;
    protected int y;

    //CONSTRUCTORES

    /**
     * @pre Cierto.
     * @post constructora de posicion sin inicializar o posicion no valida.
     */
    public Posicion() {
        x = -1;
        y = -1;
    }

    /**
     * @pre Cierto.
     * @post poisicion inicializada con los valores x e y.
     */
    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //CONSULTORAS
    /**
     * @pre Cierto.
     * @post retorna la variable x de la posicion.
     */
    public int getX() {
        return x;
    }

    /**
     * @pre Cierto.
     * @post retorna la variable y de la posicion.
     */
    public int getY() {
        return y;
    }
}
