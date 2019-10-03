package habitaciones.dominio.modelos;

/**
* La clase Baldosa representa una unidad de todo el espacio de la habitación
*
*/

import compartidas.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;


public class Baldosa extends Casilla<Mueble> {

    protected ArrayList<ContenidoPared> listaContPar;
    /**
     * Crear una baldosa nueva
     * @param pos Posición que ocupa la baldosa
     */
    public Baldosa(Posicion pos) {
        super(pos);
        llena = false;
        listaContPar = new ArrayList<ContenidoPared> ();
    }

    /**
     * Comprueba si el mueble mueb cabe en la baldosa
     *
     * @param mueb
     * @return El resultado indica si la baldosa está vacía o no
     */
    public boolean cabeContenido(Mueble mueb) {
        return !llena;
    }


    public boolean esPuerta() {
        if (listaContPar.size() == 0)
            return false;

        if (listaContPar.get(0).esPuerta()) {
            return true;
        }

        return false;
    }

    public boolean cabeContenidoPared(ContenidoPared cont) {
        return (listaContPar.size() == 0);
    }

    public boolean quitarContenidoPared(ContenidoPared cont) {
        if (listaContPar.remove(cont)) {
            cont.desasignarCasilla(this);
            return true;
        }

        return false;
    }

    public boolean hayContenidoPared() {
        /* System.out.println("listaContPar.size():" + listaContPar.size()); */
        return (listaContPar.size() > 0);
    }

    public boolean anadirContenidoPared(ContenidoPared cont) {
        if (listaContPar.size() == 0) {
            listaContPar.add(cont);
            cont.asignarCasilla(this);
            return true;
        }

        return false;
    }

    /**
     * Añade el mueble c a la habitación si la baldosa no está ocupada
     *
     * @param c El mueble al que le asignamos la baldosa
     * @return El resultado indica si es posible realizar la operación de asignar el mueble c a las baldosas
     */
    public boolean anadirContenido(Mueble c) {
        if (!llena) {
            lista.add(c);
            llena = true;
            c.asignarCasilla(this);
            return true;
        }

        return false;
    }

    /**
     * Quita el mueble de la habitacióm desasignando las baldosas que ocupa el mueble
     *
     * @param c El mueble a quitar
     * @return El resultado indica si es posible realizar la operación de desasignar el mueble c de las baldosas
     */
    public boolean quitarContenido(Mueble c) {
        if (lista.remove(c)) {
            llena = false;
            c.desasignarCasilla(this);
            return true;
        }

        return false;
    }

    /**
     * Vaciar una serie de baldosas de los muebles que puede contener
     *
     */
    public void vaciar() {
        lista.clear();
        llena = false;
    }

    /**
     * Copia una baldosa con todo su contenido
     *
     * @param bald Baldosa original de la que se copiaran los datos
     */
    public Baldosa(Baldosa bald) {
        pos = new Posicion(bald.getX(), bald.getY());
        lista = new ArrayList<Mueble>();
        Iterator<Mueble> muebleOrigIterator = bald.lista.iterator();
        while (muebleOrigIterator.hasNext()) {
            Mueble original = muebleOrigIterator.next();
            Mueble copia = new Mueble(original);
            copia.desasignarTodas();

            List<Baldosa> ocupadas = original.getCasillas();
            Iterator<Baldosa> ocupadasIterator = ocupadas.iterator();
            while (ocupadasIterator.hasNext()) {
                copia.asignarCasilla(ocupadasIterator.next());
            }

            lista.add(copia);
        }

        listaContPar = new ArrayList<ContenidoPared>();
        Iterator<ContenidoPared> contParedIt = bald.listaContPar.iterator();


        while (contParedIt.hasNext()) {
            ContenidoPared original = contParedIt.next();
            ContenidoPared copia = new ContenidoPared(original);
            copia.desasignarTodas();
            List<Baldosa> ocupadas = original.getCasillas();
            Iterator<Baldosa> ocupadasIterator = ocupadas.iterator();
            while (ocupadasIterator.hasNext()) {
                copia.asignarCasilla(ocupadasIterator.next());
            }

            listaContPar.add(copia);
        }
    }

    /**
     * Marca esta casilla como ocupada
     *
     */
    public void marcarOcupada() {
        llena = true;
    }

    /**
     * Devuelve cierto si hay algun contenido colocado en la baldosa
     * @return El resultado indica si la baldosa contiene mueble
     */
    public boolean hayContenido() {
        return !lista.isEmpty();
    }

}
