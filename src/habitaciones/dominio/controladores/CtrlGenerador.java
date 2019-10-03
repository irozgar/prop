package habitaciones.dominio.controladores;

import java.util.*;

import compartidas.*;
import habitaciones.dominio.modelos.*;
import habitaciones.dominio.modelos.restricciones.*;
import habitaciones.dominio.controladores.CtrlDominio;


public class CtrlGenerador {
    CtrlDominio ctrlDominio;

    public CtrlGenerador(CtrlDominio ctrlDominio) {
        this.ctrlDominio = ctrlDominio;
    }

    /**
    * Mostrar el tablero por la pantalla en la consola
    */
    @SuppressWarnings("unchecked")
    public void generar() {
        Generador generador = new Generador<Mueble, Baldosa>();
        Habitacion habitacion = ctrlDominio.getHabitacion();
        ArrayList<Mueble> muebles = ctrlDominio.getMuebles();
        ArrayList<Mueble> copia_muebles = new ArrayList<Mueble>();
        for (Mueble m : muebles)
            copia_muebles.add(new Mueble(m));
        aplicarRestriccionesContenido(copia_muebles);
        try {
            habitacion.vaciaCasillas();
            generador.generar(habitacion, copia_muebles, -1);
        } catch(Exception e) {
            System.out.println("No se pudo generar la habitacion: "+e.getMessage());
            //e.printStackTrace();
            return;
        }
        habitacion.print();
    }

    private void aplicarRestriccionesContenido(ArrayList<Mueble> muebles) {
        ArrayList<RestriccionDistancia> rDist = ctrlDominio.getRestriccionesDistancia();
        for (RestriccionDistancia r : rDist) {
            int id = r.getId();
            int dist = r.getDistancia();
            int idM1 = r.getMuebleId1();
            int idM2 = r.getMuebleId2();
            Mueble m1 = searchMueble(muebles, idM1);
            Mueble m2 = searchMueble(muebles, idM2);
            RestriccionDistancia rd;
            if (r instanceof RestriccionDistanciaMinima)
                rd = new RestriccionDistanciaMinima(id, dist, m1, m2);
            else
                rd = new RestriccionDistanciaMaxima(id, dist, m1, m2);
            m1.anadirRestriccion(rd);
            m2.anadirRestriccion(rd);
        }
    }

    private Mueble searchMueble(ArrayList<Mueble> muebles, int idMueble) {
        Iterator<Mueble> it = muebles.iterator();
        while (it.hasNext()) {
            Mueble m = it.next();
            if (m.getId() == idMueble) return m;
        }
        return null;
    }
}
