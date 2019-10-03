package habitaciones.dominio.controladores.test;

import compartidas.Generador;
import habitaciones.dominio.controladores.CtrlDominio;
import habitaciones.dominio.modelos.Baldosa;
import habitaciones.dominio.modelos.Habitacion;
import habitaciones.dominio.modelos.Mueble;
import habitaciones.dominio.modelos.restricciones.RestriccionDistancia;
import habitaciones.dominio.modelos.restricciones.RestriccionDistanciaMaxima;
import habitaciones.dominio.modelos.restricciones.RestriccionDistanciaMinima;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Tester {
    public static void main(String[] args) {
        for (String file: args) {
            runTest(file);
        }
    }

    private static void runTest(String testFile) {
        CtrlDominio ctrl = new CtrlDominio();
        String filename = testFile;
        try {
            ctrl.cargar(filename);
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo cargar el archivo" + filename);
        }

        Generador generador = new Generador<Mueble, Baldosa>();
        Habitacion habitacion = ctrl.getHabitacion();
        ArrayList<Mueble> muebles = ctrl.getMuebles();
        ArrayList<Mueble> copia_muebles = new ArrayList<Mueble>();
        for (Mueble m : muebles)
            copia_muebles.add(new Mueble(m));

        aplicarRestriccionesContenido(ctrl, copia_muebles);
        Boolean error = false;

        try {
            habitacion.vaciaCasillasContenidos();
            generador.generar(habitacion, copia_muebles, -1);
        } catch(Exception e) {
            System.out.println("No se pudo generar la habitacion: "+e.getMessage());
            e.printStackTrace();
            error = true;
        }

        if (!error) {
            habitacion.print();
        }
    }

    private static void aplicarRestriccionesContenido(CtrlDominio ctrlDominio, ArrayList<Mueble> muebles) {

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

    private static Mueble searchMueble(ArrayList<Mueble> muebles, int idMueble) {
        Iterator<Mueble> it = muebles.iterator();
        while (it.hasNext()) {
            Mueble m = it.next();
            if (m.getId() == idMueble) return m;
        }
        return null;
    }
}
