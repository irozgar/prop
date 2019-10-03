package compartidas;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class CjtoRestricciones {
    public CjtoRestricciones() {}

    /**
     * Devuelve true si se satisfacen las restricciones para contenidoActual
     *
     * @param contenidoActual El contenido cuyas restricciones se están comprobando
     * @param contenidoColocado Lista con el contenido que ya se ha colocado en el contenedor
     *
     * @return boolean
     *
     */
    public boolean satisfaceRestricciones(Contenido contenidoActual, List<Contenido> contenidoColocado) {
        return (satisfaceRestriccionesContenido(contenidoActual, contenidoColocado) &&
                satisfaceRestriccionesCjtoContenidos(contenidoActual) &&
                satisfaceRestriccionesContenedor(contenidoActual, contenidoColocado));
    }

    /**
     * Devuelve true si se satisfacen las restricciones de contenedor para contenidoActual
     *
     * @param contenidoActual El contenido cuyas restricciones se están comprobando
     * @param contenidoColocado Lista con el contenido que ya se ha colocado en el contenedor
     *
     * @return boolean
     *
     */
    public boolean satisfaceRestriccionesContenedor(Contenido contenidoActual, List<Contenido> contenidoColocado) {
        List<RestriccionContenedor> restricciones = contenidoActual.getRestriccionesContenedor();

        Iterator<RestriccionContenedor> iteradorRestricciones = restricciones.iterator();
        while (iteradorRestricciones.hasNext()) {
            RestriccionContenedor restriccion = iteradorRestricciones.next();

            if (!restriccion.seSatisface(contenidoColocado)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Devuelve true si se satisfacen las restricciones de contenido para ContenidoActual
     *
     * @param contenidoActual
     * @param contenidoColocado
     *
     * @return boolean
     *
     */
    public boolean satisfaceRestriccionesContenido(Contenido contenidoActual, List<Contenido> contenidoColocado) {
        List<RestriccionContenidos> restriccionesSatisfacibles = obtieneRestriccionesSatisfacibles(contenidoActual, contenidoColocado);

        Iterator<RestriccionContenidos> iteradorSatisfacibles = restriccionesSatisfacibles.iterator();
        while (iteradorSatisfacibles.hasNext()) {
            RestriccionContenidos restr = iteradorSatisfacibles.next();

            if (!restr.seSatisface()) {
                return false;
            }
        }

        return true;
    }


    /**
     * Devuelve true si se cumplen las restricciones de conjunto de contenidoActual
     *
     * @contenidoActual
     *
     * @return boolean
     *
     */
    public boolean satisfaceRestriccionesCjtoContenidos(Contenido contenidoActual) {
        List<RestriccionConjuntos> restriccionesCjto = contenidoActual.getRestriccionesConjunto();
        Iterator<RestriccionConjuntos> iteradorRestriccionesCjto = restriccionesCjto.iterator();

        while (iteradorRestriccionesCjto.hasNext()) {
            RestriccionConjuntos restr = iteradorRestriccionesCjto.next();

            if (!restr.seSatisface(contenidoActual)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Devuelve una lista con las restricciones de contenido de contenidoActual que se pueden satisfacer
     *
     * @param contenidoActual
     * @param contenidoColocado
     *
     * @return List<RestriccionContenido>
     *
     */

    private List<RestriccionContenidos> obtieneRestriccionesSatisfacibles(Contenido contenidoActual, List<Contenido> contenidoColocado) {
        List<RestriccionContenidos> satisfacibles = new ArrayList<RestriccionContenidos>();

        Iterator<RestriccionContenidos> iteradorRestricciones = contenidoActual.getRestricciones().iterator();
        while (iteradorRestricciones.hasNext()) {
            RestriccionContenidos restr = iteradorRestricciones.next();
            if (restr.esSatisfacible(contenidoColocado)) {
                satisfacibles.add(restr);
            }
        }

        return satisfacibles;
    }
}
