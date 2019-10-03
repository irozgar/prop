package compartidas;

import java.util.List;

public abstract class RestriccionContenedor extends Restriccion {
    /**
     * Crea una instancia de RestriccionContenedor y le asigna id como identificador
     *
     * @param id Identificador de la restriccion
     *
     */
    public RestriccionContenedor(int id) {
        super(id);
    }

    /**
     * Retorna true si se satisface la restricción y false si no
     *
     * @param contenidoColocado Lista del contenido que ya se ha colocado
     *
     */
    public abstract boolean seSatisface(List<Contenido> contenidoColocado);
}
