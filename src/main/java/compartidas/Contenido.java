package compartidas;

import java.util.*;




/**
 * Clase que contiene la informacion del contenido y las restricciones que le afectan
 *
 */

public abstract class Contenido {
    protected int id;
    protected String tipo;
    protected List<RestriccionContenidos> restricciones;
    protected List<RestriccionConjuntos> restConj;
    protected List<RestriccionContenedor> restConten;
    protected boolean assignat;

    //Constructoras
    /**
     * Pre: Cierto.
     * Post: Se genera un Contenido vacío.
     */
    public Contenido() {
        id=-1;
        tipo="";
        restricciones = new ArrayList<RestriccionContenidos>();
        restConj = new ArrayList<RestriccionConjuntos>();
        assignat=false;
    }


    /**
     * Pre: Cierto.
     * @param Identificador del contenido.
     * @param Tipo del contenido.
     * Post: Se genera un Contenido con valores id,tipo y sin posición.
     */
    public Contenido(int id, String tipo) {
        this.id=id;
        this.tipo=tipo;
        restricciones = new ArrayList<RestriccionContenidos>();
        restConj = new ArrayList<RestriccionConjuntos>();
        assignat=false;
    }


    //Consultoras

    /**
     * Pre: Cierto.
     * Post: Retorna cierto si el contenido está asignado en alguna posición.
     */
    public boolean estaAsignado() {
        return (assignat);
    }

    /**
     * Pre: Cierto.
     * Post: Devuelve el tipo del Contenido.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Pre: Cierto.
     * Post: Devuelve el id del Contenido.
     */
    public int getId() {
        return id;
    }

    /**
     * Pre: Cierto.
     * Post: Devuelve la lista de restricciones del Contenido.
     */
    public List<RestriccionContenidos> getRestricciones() {
        return restricciones;
    }

    /**
     * Pre: Cierto
     * Post: Devuelve la lista de restricciones del Contenido respecto a su conjunto.
     */
    public List<RestriccionConjuntos> getRestriccionesConjunto() {
        return restConj;
    }

    /**
     * Pre: Cierto
     * Post: Devuelve la lista de restricciones del Contenido respecto a su contenedor.
     */
    public List<RestriccionContenedor> getRestriccionesContenedor() {
        return restConten;
    }


    //Modificadoras

    /**
     * Pre: Cierto
     * Post: Cambia el id del Contenido
     * @param id Identificador del Contenido
     */
    public void setId(int id) {
        this.id=id;
    }

    /**
     * Pre: Cierto
     * Post: Cambia el tipo de contenido a tipo.
     * @param tipo Tipo de Contenido
     */
    public void setTipo(String tipo) {
        this.tipo=tipo;
    }

    /**
     * Pre: restr es una restriccion valida.
     * Post: restr es añadida al Contenido.
     * @param restr es una restriccion a añadir
     */
    public void anadirRestriccion(RestriccionContenidos restr) {
        restricciones.add(restr);
    }


    /**
     * @param restr Restriccion a quitar
     * Pre: restr es una restricción válida
     * Post: Si restr esta asignada la elimina, sinó no hace nada
     */
    public void quitarRestriccion(RestriccionContenidos restr) {
        restricciones.remove(restr);
    }

    /**
     * Pre: restr es una lista de restricciones valida.
     * Post: las restricciones contenidas en restr son asignadas al Contenido.
     * @param restr es un conjunto de restricciones
     */
    public void asignarRestricciones(List<RestriccionContenidos> restr) {
        restricciones = restr;
    }

    public void vaciarRestricciones() {
        restricciones = new ArrayList<RestriccionContenidos>();
    }

    /**
     * Pre: restr es una restriccion valida.
     * Post: restr es añadida al Contenido.
     * @param restr es una restriccion a añadir respecto a un conjunto
     */
    public void anadirRestCjt(RestriccionConjuntos restr) {
        restConj.add(restr);
    }


    /**
     * @param restr Restriccion respecto a un conjunto a quitar
     * Pre: restr es una restricción válida
     * Post: Si restr esta asignada la elimina, sinó no hace nada
     */
    public void quitarRestCjt(RestriccionConjuntos restr) {
        restConj.remove(restr);
    }

    /**
     * Pre: restr es una lista de restricciones de conjuntos valida.
     * Post: las restricciones contenidas en restr son asignadas al Contenido.
     * @param restr es un conjunto de restricciones
     */
    public void asignarRestrCjt(List<RestriccionConjuntos> restr) {
        restConj = restr;
    }

    /**
     * Pre: Cierto
     * Post: Elimina las restricciones del Contenido
     */
    public void vaciarRestrCjt() {
        restConj = new ArrayList<RestriccionConjuntos>();
    }

    /**
     * Pre: restr es una restriccion valida.
     * Post: restr es añadida al Contenido.
     * @param restr es una restriccion a añadir respecto a un contenedor
     */
    public void anadirRestCont(RestriccionContenedor restr) {
        restConten.add(restr);
    }


    /**
     * @param restr Restriccion respecto a un contenedor a quitar
     * Pre: restr es una restricción válida
     * Post: Si restr esta asignada la elimina, sinó no hace nada
     */
    public void quitarRestCont(RestriccionContenedor restr) {
        restConten.remove(restr);
    }

    /**
     * Pre: restr es una lista de restricciones de contenedor valida.
     * Post: las restricciones contenidas en restr son asignadas al Contenido.
     * @param restr es una lista de restricciones de contenedor
     */
    public void asignarRestrCont(List<RestriccionContenedor> restr) {
        restConten = restr;
    }

    /**
     * Pre: Cierto
     * Post: Elimina las restricciones de Contenedor del Contenido
     */
    public void vaciarRestrCont() {
        restConten = new ArrayList<RestriccionContenedor>();
    }

}

