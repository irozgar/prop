package compartidas;
import java.util.*;


public abstract class Casilla<T extends Contenido> {
    protected List<T> lista;
    protected Posicion pos;
    protected boolean llena;

    //Constructoras

    /**
     * @pre Cierto
     * @post Crea una Casilla vacia sin posici�n
     */
    public Casilla() {
        lista=new ArrayList<T>();
        pos=null;
    }

    /**
     * @pre Cierto
     * @param pos La posici�n de la casilla en el contenedor.
     * @post Se genera una Casilla no llena y con una lista vac�a assignada
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
     * @post Devuelve cierto si la Casilla esta vac�a.
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
     * @param c El contenido que se quiere a�adir.
     * @post Devuelve cierto si el contenido se podr�a poner en la casilla
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
     * @param c El contedido que se quiere a�adir.
     * @post Devuelve cierto si se puede a�adir el contenido en la Casilla y
     *  si se puede se a�ade a la lista de la casilla y se le asigna la posici�n
     *  de la casilla al contenido. Devuelve falso si no cabe.
     */
    public boolean anadirContenido(T c) {
        if(cabeContenido(c)) {
            lista.add(c);
            return true;
        } else return false;
    }

    /**
     * @pre La posici�n pos es la posici�n de la casilla.
     * @param c El contenido que se quiere a�adir.
     * @post Devuelve falso si el contenido no esta en la lista de la Casilla
     * y si esta devuelve cierto y lo quita de la lista.
     */
    public boolean quitarContenido(T c) {
        return lista.remove(c);
    }

    /**
     * @pre Cierto.
     * @post Vac�a la lista de contenidos de la casilla.
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
     * @post Devuelve la coordenada x de la posici�n de la casilla.
     */
    public int getX() {
        return pos.getX();
    }

    /**
     * @pre La casilla tenga una posicion
     * @post Devuelve la coordenada y de la posici�n de la casilla.
     */
    public int getY() {
        return pos.getY();
    }
}
