package compartidas;
import java.util.List;

public abstract class Contenedor<T extends Contenido, E extends Casilla<T>> {
    protected int filas;
    protected int columnas;
    protected E[][] matriu;
    protected boolean fallo;

    /**
     * @pre Cierto
     * @post Devuelve una copia del Contenedor
     * @return Copia del Contenedor
     */
    public abstract Contenedor<T,E> getCopy();

    //Consultoras
    /**
     * @pre Cierto.
     * @post Devuelve el número de filas del contenedor.
     */
    public int getFilas() {
        return filas;
    }

    /**
     * @pre Cierto.
     * @post Devuelve el número de columnas del contenedor.
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * @pre pos sea una Posicion del contenedor.
     * @param pos La posición de la casilla que se quiere comprobar.
     * @post Retorna cierto si la Posicion pos del contenedor está llena.
     */
    public boolean estaLlena(Posicion pos) {
        return matriu[pos.getX()][pos.getY()].estaLlena();
    }

    /**
     * @pre pos sea una Posicion del contenedor.
     * @param pos La posición de la casilla que se quiere probar.
     * @param c El contenido que se quiere probar si cabe en la casilla.
     * @post Retorna cierto si c se puede añadir a la posicion pos.
     */
    public boolean cabeContenido(Posicion pos, T c) {
        return matriu[pos.getX()][pos.getY()].cabeContenido(c);
    }

    /**
     * @pre pos sea una Posicion del contenedor.
     * @param pos La posición de la casilla que se quiere obtener la lista.
     * @post Retorna una lista con los contenidos T de la Posicion pos.
     */
    public List<T> getContenido(Posicion pos) {
        return matriu[pos.getX()][pos.getY()].getContenido();
    }

    /**
     * @pre Cierto
     * @post Si ha fallado la construcción del Contenedor devuelve cierto, sinó falso
     * @return devuelve un booleano indicando si tiene fallo
     */
    public boolean tieneFallo() {
        return fallo;
    }
    //Modificadoras

    /**
     * @pre  filas > 0 y el contenedor esta vacío.
     * @param filas El nuevo numero de filas.
     * @post Cambia el número de filas.
     */
    public abstract void setFilas(int filas);

    /**
     * @pre  columnas > 0 y el contenedor esta vacío.
     * @param columnas El nuevo numero de columnas.
     * @post Cambia el número de columnas.
     */
    public abstract void setColumnas(int columnas);

    /**
     * @pre  pos sea una Posicion del contenedor.
     * @param pos La posición donde se quiere añadir el contenido.
     * @param c El contenido que se quiere añadir.
     * @post Retorna cierto si ha podido añadir c a la Posicion pos
     * y falso en caso contrario.
     */
    public boolean anadirContenido(Posicion pos, T c) {
        return matriu[pos.getX()][pos.getY()].anadirContenido(c);
    }

    /**
     * @pre  pos sea una Posicion del contenedor.
     * @param pos La posición donde se quiere quitar el contenido.
     * @param c El contenido que se quiere quitar.
     * @post Retorna cierto si ha podido eliminar c de la Posicion pos
     * y falso en caso contrario.
     */
    public boolean quitarContenido(Posicion pos, T c) {
        return matriu[pos.getX()][pos.getY()].quitarContenido(c);
    }

    /**
     * @pre  Cierto.
     * @post Retorna cierto si todas las casillas del contenedor estan
     * vacias y falso en caso contrario.
     */
    public boolean estaVacia() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!matriu[i][j].estaVacia()) return false;
            }
        }
        return true;
    }

    /**
     * @pre  Cierto.
     * @post Se vacían todas las casillas del contenedor.
     */
    public void vaciarContenedor() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriu[i][j].vaciarCasilla();
            }
        }
    }

    /**
     * @pre No se haya podido construir el Contenedor
     * @post Marca el Contenedor como que no se ha podido construir
     */
    public void setFallo() {
        fallo=true;
    }

    /**
     * @pre Cierto
     * @post Marca al Contenedor como que de momento se puede construir
     */
    public void resetFallo() {
        fallo=false;
    }
}
