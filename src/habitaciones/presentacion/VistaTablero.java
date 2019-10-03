package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import compartidas.*;
import habitaciones.dominio.modelos.*;
import habitaciones.dominio.modelos.restricciones.*;
import habitaciones.dominio.controladores.CtrlDominio;

public class VistaTablero extends Vista {
    // Componentes de la interficie grafica
    private JButton buttonVolver;
    private JLabel matriu;

    // Constructor y metodos publicos
    public VistaTablero(CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super("Tablero", ctrlPresentacion, ctrlDominio);
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
        Boolean error = false;

        try {
            habitacion.vaciaCasillasContenidos();
            generador.generar(habitacion, copia_muebles, -1);
        } catch(Exception e) {
            ctrlPresentacion.muestraError("No se pudo generar la habitacion: " + e.getMessage());
            System.out.println("No se pudo generar la habitacion: "+e.getMessage());
            e.printStackTrace();
            error = true;
        }

        if (!error) {
            habitacion.print();
        }
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

    /**
     * Mostrar el tablero en una ventana
     */
    protected void inicializar_panelInformacion() {
        super.inicializar_panelInformacion();
        generar();
        Habitacion habitacion = ctrlDominio.getHabitacion();
        panelInformacion.setLayout(new GridLayout(habitacion.getFilas()+2,habitacion.getColumnas()+2));
        panelInformacion.setBackground(new java.awt.Color(0, 0, 0));
        panelInformacion.setBorder(javax.swing.BorderFactory.createMatteBorder(3,3,3,3,new java.awt.Color(255, 255, 255)));
        panelInformacion.setSize(habitacion.getFilas()+4,habitacion.getColumnas()+4);

        JLabel[][] matriu = new JLabel[habitacion.getFilas()+2][habitacion.getColumnas()+2];
        for (int i = 0; i <= habitacion.getFilas()+1; i++) {
            for (int j = 0; j <= habitacion.getColumnas()+1; j++) {
                Posicion pos = new Posicion(i,j);

                if (habitacion.estaLlena(pos) && habitacion.hayContenido(pos)) {
                    Mueble mueb = habitacion.getContenido(pos).get(0);
                    java.awt.Color color = new java.awt.Color(mueb.getRed(), mueb.getGreen(), mueb.getBlue());
                    matriu[i][j] = new JLabel(Integer.toString(mueb.getId()));
                    matriu[i][j].setForeground(color);
                    panelInformacion.add(matriu[i][j]);
                } else if (habitacion.posicionPared(pos)) {
//				else if (habitacion.estaLlena(pos) && !habitacion.hayContenido(pos)) {
                    if (habitacion.hayContenidoPared(pos)) {
                        String c;
                        java.awt.Color color = new java.awt.Color(250,250,250);
                        if (habitacion.esPuerta(pos)) {
                            c = "p";
                            color = new java.awt.Color(184,134,11);
                        } else {
                            c = "v";
                            color = new java.awt.Color(30,144,255);
                        }

                        matriu[i][j] = new JLabel(c);
                        matriu[i][j].setForeground(color);
                    } else {
                        matriu[i][j] = new JLabel("X");
                    }
                    panelInformacion.add(matriu[i][j]);
                } else {
                    matriu[i][j] = new JLabel(" ");
                    panelInformacion.add(matriu[i][j]);
                }

                matriu[i][j] = new JLabel("|");
                panelInformacion.add(matriu[i][j]);
            }
        }
    }


    // Metodos de las interfaces Listener
    private void actionPerformed_buttonVolver(ActionEvent event) {
        ctrlDominio.getHabitacion().vaciaCasillasContenidos();
        ctrlPresentacion.sincronizacionVistaTablero_a_Principal();
    }

    public void windowEvent_Close() {
        ctrlDominio.getHabitacion().vaciaCasillasContenidos();
        ctrlPresentacion.sincronizacionVistaTablero_a_Principal();
    }

    // Asignacion de listeners
    protected void asignar_listenersComponentes() {
        super.asignar_listenersComponentes();
        buttonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionPerformed_buttonVolver(event);
            }
        });
    }

    protected void inicializar_panelBotones() {
        super.inicializar_panelBotones();
        panelBotones.setLayout(new GridLayout(1,1));
        buttonVolver = new JButton("Volver");
        panelBotones.add(buttonVolver);
    }
}
