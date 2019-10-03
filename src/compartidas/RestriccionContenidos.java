package compartidas;

import java.util.List;
import java.util.ArrayList;

public abstract class RestriccionContenidos extends Restriccion {

    public RestriccionContenidos(int id) {
        super(id);
    }

    public abstract boolean seSatisface();

    public abstract boolean esSatisfacible(List<Contenido> contenidoColocado);
}
