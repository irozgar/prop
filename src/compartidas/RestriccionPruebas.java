package compartidas;

import java.util.List;

public class RestriccionPruebas extends RestriccionContenidos {
    protected boolean satisfacible;
    protected boolean seSatisface;

    public RestriccionPruebas(int id, boolean seSatisface, boolean satisfacible) {
        super(id);

        this.seSatisface = seSatisface;
        this.satisfacible = satisfacible;
    }

    public boolean seSatisface() {
        return seSatisface;
    }

    public boolean esSatisfacible(List<Contenido> lContenidos) {
        return satisfacible;
    }

    public boolean esSatisfacible() {
        return satisfacible;
    }

    public void toogleEsSatisfacible() {
        satisfacible = !satisfacible;
    }

    public void toogleSeSatisface() {
        seSatisface = !seSatisface;
    }
}
