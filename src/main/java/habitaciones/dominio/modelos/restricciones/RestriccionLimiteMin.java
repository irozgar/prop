package habitaciones.dominio.modelos.restricciones;


import habitaciones.dominio.modelos.Mueble;
import compartidas.CjtContenidos;

import habitaciones.gestion.Objeto;

public class RestriccionLimiteMin extends RestriccionLimite {
    public RestriccionLimiteMin(int id, int cantidad, CjtContenidos<Mueble> cjtoMuebles) {
        super(id, cantidad, cjtoMuebles);
    }

    public RestriccionLimiteMin(RestriccionLimite rest) {
        super(rest);
    }

    public boolean seSatisface() {
        return cjtoMuebles.getListaCont().size() >= cantidad;
    }

    public Objeto toObjeto() {
        Objeto objeto = super.toObjeto();
        objeto.addAtributo("Tipo", "LimiteMin");

        return objeto;
    }
}
