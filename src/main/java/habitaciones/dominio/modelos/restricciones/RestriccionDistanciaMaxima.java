package habitaciones.dominio.modelos.restricciones;

import java.util.*;
import compartidas.Contenido;
import habitaciones.dominio.modelos.Mueble;

import habitaciones.gestion.Objeto;

public class RestriccionDistanciaMaxima extends RestriccionDistancia {

    public RestriccionDistanciaMaxima(int id, int distancia, Mueble a, Mueble b) {
        super(id, distancia, a, b);
    }

    public RestriccionDistanciaMaxima(RestriccionDistancia rest) {
        super(rest);
    }

    public boolean seSatisface() {
        return calculaDistancia() <= distancia;
    }

    public String toString() {
        String text = "Dist. maxima: ";
        text += "(" + this.c1.getId();
        text += "," + this.c2.getId() + ") ";
        text += distancia + "racholas";
        return text;
    }

    public Objeto toObjeto() {
        Objeto objeto = super.toObjeto();
        objeto.addAtributo("Tipo", "DistanciaMax");

        return objeto;
    }
}
