package habitaciones.dominio.modelos.restricciones;


import habitaciones.dominio.modelos.Mueble;
import compartidas.CjtContenidos;

import habitaciones.gestion.Objeto;

public class RestriccionLimiteMax extends RestriccionLimite {
    public RestriccionLimiteMax(int id, int cantidad, CjtContenidos<Mueble> cjtoMuebles) {
        super(id, cantidad, cjtoMuebles);
    }

    public RestriccionLimiteMax(RestriccionLimite rest) {
        super(rest);
    }

    public boolean seSatisface() {
        return cjtoMuebles.getListaCont().size() <= cantidad;
    }

    public Objeto toObjeto() {
        Objeto objeto = super.toObjeto();
        objeto.addAtributo("Tipo", "LimiteMax");

        return objeto;
    }
}
