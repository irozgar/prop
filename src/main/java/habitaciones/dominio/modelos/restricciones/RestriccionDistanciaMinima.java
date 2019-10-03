package habitaciones.dominio.modelos.restricciones;

import java.util.*;
import compartidas.Contenido;
import habitaciones.dominio.modelos.Mueble;

import habitaciones.gestion.Objeto;

public class RestriccionDistanciaMinima extends RestriccionDistancia {

    public RestriccionDistanciaMinima(int id, int distancia, Mueble a, Mueble b) {
        super(id, distancia, a, b);
    }

    public RestriccionDistanciaMinima(RestriccionDistancia rest) {
        super(rest);
    }

    public boolean seSatisface() {
        return calculaDistancia() >= distancia;
    }

    public String toString() {
        String text = "Dist. minima: ";
        text += "(" + this.c1.getId();
        text += "," + this.c2.getId() + ") ";
        text += distancia + "racholas";
        return text;
    }

    public Objeto toObjeto() {
        Objeto objeto = super.toObjeto();
        objeto.addAtributo("Tipo", "DistanciaMin");

        return objeto;
    }
}
