/**
 * Restricciones de distancia
 *
 */

package habitaciones.dominio.modelos.restricciones;

import java.util.*;
import compartidas.RestriccionContenidos;
import compartidas.Contenido;
import habitaciones.dominio.modelos.Mueble;
import habitaciones.dominio.modelos.Baldosa;

import habitaciones.gestion.Objeto;

public abstract class RestriccionDistancia extends RestriccionContenidos {

    protected int distancia;
    protected Mueble c1;
    protected Mueble c2;

    public RestriccionDistancia(int id, int distancia, Mueble c1, Mueble c2) {
        super(id);
        this.distancia = distancia;
        this.c1 = c1;
        this.c2 = c2;
    }

    public RestriccionDistancia(RestriccionDistancia rest) {
        super(rest.id);
        distancia = rest.distancia;
        c1 = rest.c1;
        c2 = rest.c2;
    }

    public void setMueble1(Mueble mueb) {
        c1 = mueb;
    }

    public Mueble getMueble1() {
        return c1;
    }

    public void setMueble2(Mueble mueb) {
        c2 = mueb;
    }

    public Mueble getMueble2() {
        return c2;
    }

    protected int calculaDistancia() {
        List<Baldosa> lCasillas1 = c1.getCasillas();
        List<Baldosa> lCasillas2 = c2.getCasillas();

        int distancia_x = Integer.MAX_VALUE;
        int distancia_y = Integer.MAX_VALUE;
        Iterator<Baldosa> it1 = lCasillas1.iterator();
        while (it1.hasNext()) {
            Baldosa a = it1.next();
            Iterator<Baldosa> it2 = lCasillas2.iterator();
            while (it2.hasNext()) {
                Baldosa b = it2.next();
                int distancia_x_tmp = java.lang.Math.abs(a.getX() - b.getX());
                if (distancia_x_tmp < distancia_x) {
                    distancia_x = distancia_x_tmp;
                }
                int distancia_y_tmp = java.lang.Math.abs(a.getY() - b.getY());
                if (distancia_y_tmp < distancia_y) {
                    distancia_y = distancia_y_tmp;
                }
            }
        }
        return (int)java.lang.Math.ceil(java.lang.Math.hypot(distancia_x, distancia_y));
    }

    public int getMuebleId1() {
        return c1.getId();
    }

    public int getMuebleId2() {
        return c2.getId();
    }

    public int getDistancia() {
        return distancia;
    }

    public boolean esSatisfacible(List<Contenido> contenidoColocado) {
        boolean C1 = (c1.getCasillas().size() == c1.getAnchura()*c1.getLongitud());
        boolean C2 = (c2.getCasillas().size() == c2.getAnchura()*c2.getLongitud());
        return C1 && C2;
    }

    public Objeto toObjeto() {
        Objeto objeto = new Objeto();
        objeto.setTipo("Restriccion");
        objeto.addAtributo("id", id);
        objeto.addAtributo("objeto1_id", c1.getId());
        objeto.addAtributo("objeto2_id", c2.getId());
        objeto.addAtributo("distancia", distancia);

        return objeto;
    }
}
