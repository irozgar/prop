package habitaciones.dominio.modelos.restricciones;


import habitaciones.dominio.modelos.Mueble;
import compartidas.CjtContenidos;
import compartidas.Contenido;
import java.util.List;
import compartidas.RestriccionContenidos;

import habitaciones.gestion.Objeto;

public abstract class RestriccionLimite extends RestriccionContenidos {
    int cantidad;
    CjtContenidos<Mueble> cjtoMuebles;

    public RestriccionLimite(int id, int cantidad, CjtContenidos<Mueble> cjtoMuebles) {
        super(id);

        this.cantidad = cantidad;
        this.cjtoMuebles = cjtoMuebles;
    }

    public RestriccionLimite(RestriccionLimite rest) {
        super(rest.id);

        this.cantidad = rest.cantidad;
        this.cjtoMuebles = rest.cjtoMuebles;
    }

    public void reemplazaMueble(Mueble orig, Mueble nuevo) {
        cjtoMuebles.quitaCont(orig);
        cjtoMuebles.anadirCont(nuevo);
    }

    public boolean esSatisfacible(List<Contenido> contenidoColocado) {
        return true;
    }

    public Objeto toObjeto() {
        Objeto objeto = new Objeto();
        objeto.setTipo("Restriccion");
        objeto.addAtributo("id", id);

        //objeto.addAtributo("objeto1_id", c1.getId());
        //objeto.addAtributo("objeto2_id", c2.getId());
        objeto.addAtributo("cantidad", cantidad);

        return objeto;
    }
}
