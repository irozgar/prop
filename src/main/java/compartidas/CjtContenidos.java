package compartidas;

import java.util.*;




/**
 * @author Marc
 *
 * @param <T>
 */
public class CjtContenidos<T extends Contenido> {
    protected List<T> listaCont;
    protected String tipoCjtCont;
    protected List<RestriccionConjuntos> restr;

    //Constructoras
    /**
     * @pre Cierto
     * @post Crea un CjtContenidos vacio, sin Contenidos, tipo ni restricciones.
     */
    public CjtContenidos() {
        listaCont = new ArrayList<T>();
        tipoCjtCont="";
        restr = new ArrayList<RestriccionConjuntos>();
    }


    /**
     * @param tipo Tipo de los Contenidos que contendrà
     * @pre tipo sea un Tipo de Contenido
     * @post Crea un CjtContenidos sin contenidos ni restricciones pero con tipo
     */
    public CjtContenidos(String tipo) {
        listaCont = new ArrayList<T>();
        tipoCjtCont=tipo;
        restr = new ArrayList<RestriccionConjuntos>();
    }

    //Consultoras

    /**
     * @pre Cierto.
     * @post Devuelve contenidos del conjunto de contenidos.
     */
    public List<T> getListaCont() {
        return listaCont;
    }

    /**
     * @pre Cierto
     * @post Devuelve el tipo de contenido del conjunto de contenidos.
     */
    public String getTipoCjtCont() {
        return tipoCjtCont;
    }


    /**
     * @pre Cierto
     * @post Devuelve una lista con las restricciones del Conjunto
     * @return Lista con las restricciones del Conjunto
     */
    public List<RestriccionConjuntos> getRestriccionesConjunto() {
        return restr;
    }

    //Modificadoras
    /**
     * @pre Cierto
     * @param cont El contenido ha aÃ±adir.
     * @post Anade el contenido a la lista de cjt contenidos.
     */
    public void anadirCont(T cont) {
        cont.asignarRestrCjt(restr);
        listaCont.add(cont);
    }

    /**
     * @pre Cierto.
     * @param cont  contenido que se quiere quitar
     * @post Quita el contenido de la lista de cjt conjunto de contenidos.
     */
    public void quitaCont(T cont) {
        listaCont.remove(cont);
        cont.vaciarRestrCjt();
    }

    /**
     * @pre Cierto
     * @post Añade restriccion a la lista de restricciones
     * @param restriccion Restriccion a añadir
     */
    public void anadirRestr(RestriccionConjuntos restriccion) {
        Iterator<T> iterador = listaCont.iterator();
        while (iterador.hasNext()) {
            T aux = iterador.next();
            aux.anadirRestCjt(restriccion);
        }
        restr.add(restriccion);
    }

    /**
     * @pre Cierto
     * @post Quita la restriccion de la lista si ésta la contiene, en caso contrario no hace nada
     * @param restriccion Restriccion a quitar
     */
    public void quitarRestr(RestriccionConjuntos restriccion) {
        Iterator<T> iterador = listaCont.iterator();
        while (iterador.hasNext()) {
            T aux = iterador.next();
            aux.quitarRestCjt(restriccion);
        }
        restr.remove(restriccion);
    }
}
