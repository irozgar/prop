package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import habitaciones.dominio.modelos.Mueble;
import habitaciones.dominio.modelos.restricciones.*;
import habitaciones.dominio.controladores.CtrlDominio;

public class VistaAnadirRestriccion extends Vista {
    // Componentes de la interficie grafica
    private JButton buttonDist;
    private JButton buttonLimi;

    private JButton buttonVolver;

    // Constructor y metodos publicos
    public VistaAnadirRestriccion(CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super("Anadir Restriccion", ctrlPresentacion, ctrlDominio);
    }

    public void windowEvent_Close() {
        ctrlPresentacion.sincronizacionVistaAnadirRestriccion_a_Principal();
    }

    // Resto de metodos privados
    protected void inicializar_panelBotones() {
        super.inicializar_panelBotones();
        panelBotones.setLayout(new GridLayout(3,1));
        // Botones distancia
        buttonDist = new JButton("Distancia");
        panelBotones.add(buttonDist);
        // Botones limite
        buttonLimi = new JButton("Limite");
        //panelBotones.add(buttonLimi);
        // Boton volver
        buttonVolver = new JButton("Volver");
        panelBotones.add(buttonVolver);
    }

    // Asignacion de listeners
    protected void asignar_listenersComponentes() {
        super.asignar_listenersComponentes();
        buttonDist.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonDist(event);
            }
        });
        buttonLimi.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonLimi(event);
            }
        });
        buttonVolver.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonVolver(event);
            }
        });
    }

    // Metodos de las interfaces Listener
    private void actionPerformed_buttonDist(ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaAnadirRestriccion_a_AnadirRestriccionDistancia();
    }

    private void actionPerformed_buttonLimi(ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaAnadirRestriccion_a_AnadirRestriccionLimite();
    }

    private void actionPerformed_buttonVolver(ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaAnadirRestriccion_a_Principal();
    }
}
