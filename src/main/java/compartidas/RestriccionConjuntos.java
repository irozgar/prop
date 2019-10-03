package compartidas;

public abstract class RestriccionConjuntos extends Restriccion {
    public RestriccionConjuntos(int id) {
        super(id);
    }

    public abstract boolean seSatisface(Contenido contenidoActual);
}
