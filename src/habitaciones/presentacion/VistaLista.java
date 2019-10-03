package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import habitaciones.dominio.modelos.*;
import habitaciones.dominio.modelos.restricciones.*;
import compartidas.*;
import habitaciones.dominio.controladores.CtrlDominio;

public class VistaLista extends Vista {

    // Componentes de la interficie grafica
    private JList lista;

    // Constructor y metodos publicos
    public VistaLista (CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super("Info", ctrlPresentacion, ctrlDominio);
    }

    public void windowEvent_Close() {
        VistaDialogo vistaDialogo = new VistaDialogo();
        String[] strBotones = {"Si", "No"};
        int isel = vistaDialogo.setDialogo("Abandonar aplicacion", "Desea salir?", strBotones, 2);
        if (isel == 0) System.exit(0);
    }

    public void update() {
        Vector<Object> data = new Vector<Object>();
        // Anadir habitacion
        data.add("HABITACION");
        Habitacion habitacion = ctrlDominio.getHabitacion();
        if (habitacion.getFilas() > 0) {
            data.add(habitacion);
            ArrayList<ContenidoPared> cps = ctrlDominio.getContenidosPared();
            if (cps.size() > 0) {
                for (ContenidoPared cp: cps) {
                    data.add(cp);
                }
            } else {
                data.add("No hay ni puertas ni ventanas");
            }
        } else data.add("No has creado una habitacion");
        // Anadir muebles
        data.add("MUEBLES");
        ArrayList<Mueble> muebles = ctrlDominio.getMuebles();
        if (muebles.size() > 0)
            for (Mueble mueble : muebles) data.add(mueble);
        else data.add("No has creado ningun mueble");
        // Anadir restricciones
        data.add("RESTRICCIONES DISTANCIA");
        ArrayList<RestriccionDistancia> rDist = ctrlDominio.getRestriccionesDistancia();
        if (rDist.size() > 0) {
            for (RestriccionDistancia r : rDist) {
                data.add(r);
            }
        } else data.add("No has creado ninguna restriccion de distancia");
        lista.setListData(data);
    }

    protected void inicializar_panelInformacion() {
        super.inicializar_panelInformacion();
        lista = new JList();
        panelInformacion.add(lista);
    }
}
