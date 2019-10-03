package habitaciones.dominio.controladores;

import java.util.*;

import habitaciones.dominio.modelos.*;
import habitaciones.dominio.modelos.enums.*;
import habitaciones.dominio.modelos.restricciones.*;
import habitaciones.gestion.*;
import compartidas.*;

public class CtrlDominio {

    private Habitacion habitacion;
    private ArrayList<Mueble> muebles;
    private ArrayList<RestriccionDistancia> rDistancia;
    private ArrayList<RestriccionLimite> rLimite;
    private ArrayList<ContenidoPared> contenidosPared;
    private int muebleID;
    private int restriccionID;
    private int contenidoParedID;

    // Constructor y metodos de inicializacion
    public CtrlDominio() {
        inicializarCtrlDominio();
        muebleID = 0;
        restriccionID = 0;
        contenidoParedID = 0;
    }

    public void inicializarCtrlDominio() {
        cleanHabitacion();
    }

    // Llamadas desde el controlador de presentacion
    public void crearHabitacion(int filas, int columnas) {
        habitacion = new Habitacion(filas, columnas);
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion hab) {
        habitacion = hab;
    }

    public void anadirContenidoPared(TContenidoPared tipo, int fila, int columna, int tamano) throws Exception {
        Posicion pos = new Posicion(fila, columna);
        ContenidoPared contenido = new ContenidoPared(contenidoParedID++, tipo, tamano, pos);

        if (!habitacion.cabeContenidoPared(pos, contenido)) {
            throw new Exception("La puerta o ventana que se intenta colocar no cabe");
        } else {
            contenidosPared.add(contenido);
            habitacion.anadirContenidoPared(pos, contenido);
            habitacion.print();
        }
    }

    public ArrayList<ContenidoPared> getContenidosPared() {
        return contenidosPared;
    }

    public void borrarContenidoPared(ContenidoPared contenido) {
        habitacion.quitarContenidoPared(contenido);
        contenidosPared.remove(contenido);
    }

    public void anadirMueble(TMueble tipo, int longitud, int anchura, int red, int green, int blue) {
        // TODO crear el color en funcion del mueble que sea
        Color color = new Color(red, green, blue);
        Mueble mueble = new Mueble(muebleID++, tipo, color, longitud, anchura);
        muebles.add(mueble);
    }

    public void borrarMueble(Mueble mueble) {
        // Borra las restricciones que impliquen al mueble
        Iterator<RestriccionDistancia> rDistIt = rDistancia.iterator();
        while (rDistIt.hasNext()) {
            RestriccionDistancia rest = rDistIt.next();
            if (rest.getMuebleId1() == mueble.getId() || rest.getMuebleId2() == mueble.getId()) {
                System.out.println("#Borra Rest");
                rDistIt.remove();
            }
        }
        muebles.remove(mueble);
    }

    public void borrarRestriccionDistancia(RestriccionDistancia restr) {
        rDistancia.remove(restr);
    }

    public Mueble buscaMueble(int id) {
        for (Mueble m : muebles) {
            if (m.getId() == id) {
                return m;
            }
        }

        return null;
    }

    public ArrayList<Mueble> getMuebles() {
        return muebles;
    }

    public int getRestriccionId() {
        return restriccionID;
    }

    public void anadirRestriccionDistancia(RestriccionDistancia restriccion) {
        rDistancia.add(restriccion);
        restriccionID++;
    }

    public ArrayList<RestriccionDistancia> getRestriccionesDistancia() {
        return rDistancia;
    }

    public void anadirRestriccionLimite(RestriccionLimite restriccion) {
        rLimite.add(restriccion);
        restriccionID++;
    }

    public ArrayList<RestriccionLimite> getRestriccionesLimite() {
        return rLimite;
    }

    public void cleanHabitacion() {
        habitacion = new Habitacion();
        muebles = new ArrayList<Mueble>();
        rDistancia = new ArrayList<RestriccionDistancia>();
        rLimite = new ArrayList<RestriccionLimite>();
        contenidosPared = new ArrayList<ContenidoPared>();
    }

    public void guardar(String path) throws java.io.FileNotFoundException {
        Guardar.guardar(path, habitacion, muebles, rDistancia, rLimite, contenidosPared);
    }

    public void cargar(String path) throws java.io.FileNotFoundException {
        Cargar c = new Cargar(path);
        muebles = new ArrayList<Mueble>();
        rDistancia = new ArrayList<RestriccionDistancia>();
        contenidosPared = new ArrayList<ContenidoPared>();
        habitacion = c.getHabitacion();
        muebles = c.getMuebles();
        rDistancia = c.getRestriccionesDist();
        contenidosPared = c.getContenidosPared();
        muebleID = -1;
        if (muebles.size() == 0) muebleID = 0;
        for (Mueble m : muebles)
            if (m.getId() > muebleID) muebleID = m.getId() + 1;

        restriccionID = -1;
        if (rDistancia.size() == 0) restriccionID = 0;
        for (Restriccion r : rDistancia)
            if (r.getId() > restriccionID) restriccionID = r.getId() + 1;

        contenidoParedID	= -1;
        if (contenidosPared.size() == 0) contenidoParedID = 0;
        Iterator<ContenidoPared> coIt = contenidosPared.iterator();
        while (coIt.hasNext()) {
            ContenidoPared cp = coIt.next();
            if (habitacion.cabeContenidoPared(cp.getPosicion(), cp)) {
                habitacion.anadirContenidoPared(cp.getPosicion(), cp);
            }
        }

    }
}
