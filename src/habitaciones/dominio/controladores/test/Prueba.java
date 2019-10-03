package habitaciones.dominio.controladores.test;

import java.util.ArrayList;
import compartidas.*;
import habitaciones.dominio.modelos.*;
import habitaciones.dominio.modelos.enums.*;
import habitaciones.dominio.modelos.restricciones.*;
import habitaciones.dominio.controladores.input.*;

class Prueba {
    @SuppressWarnings("unchecked")
    public static void main (String [] args) {
        Habitacion h = new Habitacion(10, 10);

        Mueble m1 = new Mueble(0, TMueble.SILLA, new Color(0,0,0), 3, 3);
        Mueble m2 = new Mueble(1, TMueble.SILLA, new Color(0,0,0), 3, 3);

        ArrayList<Mueble> m = new ArrayList<Mueble>();
        m.add(m1);
        m.add(m2);

        RestriccionDistanciaMinima r = new RestriccionDistanciaMinima(0, 8, m1, m2);
        m1.anadirRestriccion(r);
        m2.anadirRestriccion(r);

        Generador g = new Generador<Mueble, Baldosa>();
        try {
            g.generar(h, m, -1);
        } catch(Exception e) {
        }
        h.print();
    }
}
