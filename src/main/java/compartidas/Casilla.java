package compartidas;
import java.util.*;


public abstract class Casilla<T extends Contenido> {
    protected List<T> lista;
    protected Posicion pos;
    protected boolean llena;

    //Constructoras

    /**
     * @pre Cierto
     * @post Crea una Casilla vacia sin posición
     */
    public Casilla() {
        lista=new ArrayList<T>();
        pos=null;
    }

    /**
     * @pre Cierto
     * @param pos La posición de la casilla en el contenedor.
     * @post Se genera una Casilla no llena y con una lista vacía assignada
    	a la posicion pos.
     */
    public Casilla(Posicion pos) {
        lista=new ArrayList<T>();
        this.pos = pos;
    }

    //Consultoras

    /**
     * @pre Cierto.
     * @post Devuelve cierto si la Casilla esta llena.
     */
    public boolean estaLlena() {
        return llena;
    }

    /**
     * @pre Cierto.
     * @post Devuelve cierto si la Casilla esta vacía.
     */
    public boolean estaVacia() {
        return lista.isEmpty();
    }

    /**
     * @pre Cierto.
     * @post Devuelve una lista con lo contenidos de la Casilla.
     */
    public List<T> getContenido() {
        return lista;
    }

    /**
     * @pre Cierto.
     * @param c El contenido que se quiere añadir.
     * @post Devuelve cierto si el contenido se podría poner en la casilla
     * sin haber comprobado las restricciones y falso en caso contrario.
     */
    public abstract boolean cabeContenido(T c);


    //Modificadoras

    /**
     * @pre Posicion pos es una posicion valida.
     * @post Asigna la Posicion pos a la Casilla
     * @param pos Posicion de la Casilla
     */
    public void asignarPosicion(Posicion pos) {
        this.pos=pos;
    }
    /**
     * @pre Cierto
     * @param c El contedido que se quiere añadir.
     * @post Devuelve cierto si se puede añadir el contenido en la Casilla y
     *  si se puede se añade a la lista de la casilla y se le asigna la posición
     *  de la casilla al contenido. Devuelve falso si no cabe.
     */
    public boolean anadirContenido(T c) {
        if(cabeContenido(c)) {
            lista.add(c);
            return true;
        } else return false;
    }

    /**
     * @pre La posición pos es la posición de la casilla.
     * @param c El contenido que se quiere añadir.
     * @post Devuelve falso si el contenido no esta en la lista de la Casilla
     * y si esta devuelve cierto y lo quita de la lista.
     */
    public boolean quitarContenido(T c) {
        return lista.remove(c);
    }

    /**
     * @pre Cierto.
     * @post Vacía la lista de contenidos de la casilla.
     */
    public void vaciarCasilla() {
        lista.clear();
    }

    /**
     * @pre Cierto.
     * @post Devuelve en numero de contenidos de la lista de la casilla.
     */
    public int numElementos() {
        return lista.size();
    }

    /**
     * @pre La casilla tenga una posicion
     * @post Devuelve la coordenada x de la posición de la casilla.
     */
    public int getX() {
        return pos.getX();
    }

    /**
     * @pre La casilla tenga una posicion
     * @post Devuelve la coordenada y de la posición de la casilla.
     */
    public int getY() {
        return pos.getY();
    }
}
