package habitaciones.dominio.modelos;

/**
* La clase representa una habitación, un determinado numero de
* baldosas en las cuales tiene colocados los muebles según el
* tamaño de cada uno.
*/

import compartidas.*;
import habitaciones.dominio.modelos.Baldosa;
import habitaciones.dominio.modelos.Mueble;
import habitaciones.gestion.Objeto;
import java.util.Iterator;
import java.util.List;

public class Habitacion extends Contenedor<Mueble, Baldosa> {

    /**
     * Crea una habitación sin tamaño
     */
    public Habitacion() {
        filas = 0;
        columnas = 0;
        fallo = true;
    }

    /**
     * Crea una nueva habitación de dimensiones nFilas x nCols e inicializa todas sus
     * casillas a falso
     *
     * @param nFilas Número de filas de casillas ocupables que habrá en la habitación
     * @param nCols  Número de columnas de casillas ocupables que habrá en la habitación
     *
     */
    public Habitacion(int nFilas, int nCols) {
        filas = nFilas + 2;
        columnas = nCols + 2;

        fallo = false;
        creaMatriz();
    }

    /**
     * Copia una habitación, todas sus casillas y todos los muebles colocados
     *
     * @param hab Habitación original de la que se copiaran los datos
     *
     */
    public Habitacion(Habitacion hab) {
        filas = hab.filas;
        columnas = hab.columnas;
        fallo = hab.fallo;

        matriu = new Baldosa[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriu[i][j] = new Baldosa(hab.matriu[i][j]);
            }
        }
    }

    /**
     * Modifica el número de filas de la habitación.
     * Crea de nuevo todas las casillas de la matriz por lo que se
     * perderá todo
     *
     * @param nFilas número de filas ocupables que tendrá la habitación
     *
     */
    public void setFilas(int nFilas) {
        filas = nFilas + 2;

        creaMatriz();
    }

    /**
     * Modifica el número de columnas de la habitación.
     * Crea de nuevo todas las casillas de la matriz por lo que se
     * perderá todo
     *
     * @param nCols número de columnas ocupables que tendrá la habitación
     *
     */
    public void setColumnas(int nCols) {
        columnas = nCols + 2;

        creaMatriz();
    }

    /**
     * Inicializa la matriz de casillas con casillas vacías
     *
     */
    private void creaMatriz() {
        matriu = new Baldosa[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriu[i][j] = new Baldosa(new Posicion(i,j));

                // La casilla forma parte de la pared
                if (i == 0 || i == (filas - 1) || j == 0 || j == (columnas - 1)) {
                    matriu[i][j].marcarOcupada();
                }
            }
        }
    }


    /**
     * Hace que todas las baldosas de la habitación sean baldosas vacías
     *
     */
    public void vaciaCasillas() {
        creaMatriz();
    }

    public void vaciaCasillasContenidos() {
        for (int i = 1; i < filas - 1; i++) {
            for (int j = 1; j < columnas - 1; j++) {
                Baldosa orig = matriu[i][j];

                matriu[i][j] = new Baldosa(new Posicion(i,j));
                // Si esta marcada como ocupada pero no hay contenido se mantiene la marca
                if (orig.estaLlena() && !orig.hayContenido()) {
                    matriu[i][j].marcarOcupada();
                }
            }
        }
    }

    /**
     * Obtiene el número de filas que el usuario introdujo
     *
     * @return Devuelve el número de filas
     */
    public int getFilas() {
        return filas - 2;
    }

    /**
     * Obtiene el número de columnas que el usuario introdujo
     *
     * @return Devuelve el número de columnas
     */
    public int getColumnas() {
        return columnas - 2;
    }

    /**
     * Dado el mueble, colocarlo en la posición indicada
     *
     * @param pos La posición donde se debe colocar el mueble
     * @param mueble El mueble que se debe colocar en la habitación
     * @return El resultado indica que el mueble ha sido añandido a la habitación y colocado en la posición pos
     */
    public boolean anadirContenido(Posicion pos, Mueble mueble) {
        // De momento solo en horizontal
        int incx = mueble.getLongitud();
        int incy = mueble.getAnchura();

        int inix = pos.getY();
        int iniy = pos.getX();

        for (int i = iniy; i < iniy+incy; i++) {
            for (int j = inix; j < inix+incx; j++) {
                matriu[i][j].anadirContenido(mueble);
            }
        }

        return true;
    }

    /**
     * Dado el mueble y la posición, quitar el mueble que ocupa la posición pos
     *
     * @param pos La posición donde se debe colocar el mueble
     * @param mueble El mueble que se debe colocar en la habitación
     * @return El resultado indica que el mueble ha sido quitado de la habitación y liberada la posición pos
     */
    public boolean quitarContenido(Posicion pos, Mueble mueble) {
        int incx = mueble.getLongitud();
        int incy = mueble.getAnchura();

        int inix = pos.getY();
        int iniy = pos.getX();

        for (int i = iniy; i < iniy+incy; i++) {
            for (int j = inix; j < inix+incx; j++) {
                matriu[i][j].quitarContenido(mueble);
            }
        }
        return true;
    }

    /**
     * Comprueba que el mueble cabe en la habitación
     *
     * @param pos La posición donde se debe colocar el mueble
     * @param mueble El mueble que se debe colocar en la habitación
     * @return El resultado indica si el mueble cabe en la posición pos o  no
     */
    public boolean cabeContenido(Posicion pos, Mueble mueble) {
        int incx = mueble.getLongitud();
        int incy = mueble.getAnchura();

        int inix = pos.getY();
        int iniy = pos.getX();

        if (!posicionCorrecta(pos)) {
            return false;
        }

        if (inix + incx > columnas) {
            return false;
        }

        if (iniy + incy > filas) {
            return false;
        }

        boolean cabe = true;
        for (int i = iniy; i < iniy+incy && cabe; i++) {
            for (int j = inix; j < inix+incx && cabe; j++) {
                cabe = matriu[i][j].cabeContenido(mueble);
            }
        }

        return cabe;
    }

    /**
     * Determina si la posición es válida o no
     *
     * @param pos La posición donde se debe colocar el mueble
     * @return El resultado indica si la posición pos es válida y se encuentra en la habitación
     */
    public boolean posicionCorrecta(Posicion pos) {
        int x = pos.getX();
        int y = pos.getY();

        return (x <= filas && x >= 0 && y <= columnas && y >= 0);
    }

    public boolean posicionPared(Posicion pos) {
        int x = pos.getX();
        int y = pos.getY();

        return (x == 0 || x == (filas - 1) || y == 0 || y == (columnas - 1));
    }

    public boolean cabeContenidoPared(Posicion pos, ContenidoPared cont) {
        System.out.println("entra");
        int inix = pos.getX();
        int iniy = pos.getY();

        if (!posicionPared(pos)) {
            return false;
        }

        // No permite las esquinas
        if ((inix == 0	&& iniy == 0) || (inix == (filas - 1) && iniy == 0)) {
            return false;
        }


        boolean vertical;
        if (inix == 0 || inix == (filas - 1)) {
            vertical = false;
        } else {
            vertical = true;
        }

        boolean cabe = true;
        if (vertical) {
            if (inix + cont.getTamano() >= filas) {
                return false;
            }

            for (int i = 0; i < cont.getTamano() && cabe; i++) {
                cabe = matriu[inix + i][iniy].cabeContenidoPared(cont);
            }
        } else {
            if (iniy + cont.getTamano() >= columnas) {
                return false;
            }

            for (int i = 0; i < cont.getTamano() && cabe; i++) {
                cabe = matriu[inix][iniy + i].cabeContenidoPared(cont);
            }
        }

        return cabe;
    }

    public boolean anadirContenidoPared(Posicion pos, ContenidoPared cont) {
        int inix = pos.getX();
        int iniy = pos.getY();

        boolean vertical;
        if (inix == 0 || inix == filas - 1) vertical = false;
        else vertical = true;

        if (vertical) {
            for (int i = 0; i < cont.getTamano(); i++) {
                matriu[inix + i][iniy].anadirContenidoPared(cont);
                // Marca adyacentes como ocupadas
                if (iniy == 0) { // A la derecha
                    matriu[inix + i][iniy + 1].marcarOcupada();
                } else { // A la izquierda
                    matriu[inix + i][iniy - 1].marcarOcupada();
                }
            }
        } else {
            for (int i = 0; i < cont.getTamano(); i++) {
                matriu[inix][iniy + i].anadirContenidoPared(cont);
                if (inix == 0) {
                    matriu[inix + 1][iniy + i].marcarOcupada();
                } else {
                    matriu[inix - 1][iniy + i].marcarOcupada();
                }
            }
        }

        return true;
    }

    public boolean quitarContenidoPared(ContenidoPared cont) {
        int inix = cont.getPosicionX();
        int iniy = cont.getPosicionY();
        int tamano = cont.getTamano();

        boolean vertical;
        if (inix == 0 || inix == filas -1) vertical = false;
        else vertical = true;

        if (vertical) {
            for (int i = 0; i < tamano; i++) {
                matriu[inix + i][iniy].quitarContenidoPared(cont);
                // Quita la marca de ocupadas a las casillas adyacentes
                if (iniy == 0) matriu[inix + i][iniy + 1].vaciar(); // A la derecha
                else matriu[inix + i][iniy - 1].vaciar(); // A la izquierda
            }
        } else {
            for (int i = 0; i < tamano; i++) {
                matriu[inix][iniy + i].quitarContenidoPared(cont);

                if (inix == 0) matriu[inix + 1][iniy + i].vaciar();
                else matriu[inix - 1][iniy + i].vaciar();
            }
        }

        return true;
    }

    public boolean hayContenidoPared(Posicion pos) {
        Boolean res = matriu[pos.getX()][pos.getY()].hayContenidoPared();
        //System.out.println("@ "+ pos.getX() +", "+pos.getY() + ": "+res);

        return res;
    }

    /**
     * Copia una habitación, todas sus casillas y todos los muebles colocados
     *
     * @return Devuelve la copia de la habitación
     *
     */
    public Contenedor<Mueble,Baldosa> getCopy() {
        return new Habitacion(this);
    }

    /**
     * Consulta si hay algún contenido en la posición pos
     *
     * @param pos La posición dentro de la habitación
     * @return El resultado indica si en la posición pos hay contenido
     */
    public boolean hayContenido(Posicion pos) {
        return matriu[pos.getX()][pos.getY()].hayContenido();
    }

    public boolean esPuerta(Posicion pos) {
        if (!posicionPared(pos)) {
            return false;
        }

        return matriu[pos.getX()][pos.getY()].esPuerta();
    }

    public void print() {
        System.out.println("Filas: "+getFilas()+", Cols: "+getColumnas());
        for (int i = 0; i <= getFilas() + 1; i++) {
            for (int j = 0; j <= getColumnas() + 1; j++) {
                Posicion pos = new Posicion(i,j);
                if (estaLlena(pos) && hayContenido(pos)) {
                    System.out.print(getContenido(pos).get(0).getId());
                } else if (posicionPared(pos)) {
                    if (!hayContenidoPared(pos)) {
                        System.out.print("X");
                    } else {
                        System.out.print("#");
                    }
                } else {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

    public String toString() {
        return "Habitacion (" + getFilas() + ", " + getColumnas() + ")";
    }

    public Objeto toObjeto() {
        Objeto objeto = new Objeto();
        objeto.setTipo("Habitacion");
        objeto.addAtributo("filas", getFilas());
        objeto.addAtributo("columnas", getColumnas());
        return objeto;
    }
}
