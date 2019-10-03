package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import habitaciones.dominio.controladores.CtrlDominio;

public class VistaCrearHabitacion extends Vista {
    // Componentes de la interficie grafica
    private JButton buttonCrear;
    private JButton buttonVolver;
    private JTextField textfieldAncho;
    private JTextField textfieldLargo;
    private JLabel labelAncho;
    private JLabel labelLargo;

    // Constructor y metodos publicos
    public VistaCrearHabitacion(CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super("Crear Habitacion", ctrlPresentacion, ctrlDominio);
    }

    public void windowEvent_Close() {
        ctrlPresentacion.sincronizacionVistaCrearHabitacion_a_Principal();
    }

    // Resto de metodos privados
    protected void inicializar_panelInformacion() {
        super.inicializar_panelInformacion();
        panelInformacion.setLayout(new GridLayout(2, 2));
        labelAncho = new JLabel("Ancho (baldosas):");
        panelInformacion.add(labelAncho);
        textfieldAncho = new JTextField(5);
        panelInformacion.add(textfieldAncho);
        labelLargo = new JLabel("Largo (baldolas):");
        panelInformacion.add(labelLargo);
        textfieldLargo = new JTextField(5);
        panelInformacion.add(textfieldLargo);
    }

    protected void inicializar_panelBotones() {
        super.inicializar_panelBotones();
        panelBotones.setLayout(new GridLayout(2,1));
        // Componentes
        buttonCrear = new JButton("Crear");
        panelBotones.add(buttonCrear);
        buttonVolver = new JButton("Volver");
        panelBotones.add(buttonVolver);
    }

    // Asignacion de listeners
    protected void asignar_listenersComponentes() {
        super.asignar_listenersComponentes();
        buttonVolver.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonVolver(event);
            }
        });
        buttonCrear.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonCrear(event);
            }
        });
    }

    // Metodos de las interfaces Listener
    private void actionPerformed_buttonVolver(ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaCrearHabitacion_a_Principal();
    }

    private void actionPerformed_buttonCrear(ActionEvent event) {

        Integer ancho;
        try {
            ancho = Integer.parseInt(textfieldAncho.getText());
        } catch(java.lang.NumberFormatException e) {
            ctrlPresentacion.muestraError("El ancho de la habitacion debe ser un numero entero mayor que 0 y menor o igual que 2000.");
            return;
        }

        Integer largo;
        try {
            largo = Integer.parseInt(textfieldLargo.getText());
        } catch(java.lang.NumberFormatException e) {
            ctrlPresentacion.muestraError("El largo de la habitacion debe ser un numero entero mayor que 0. y menor o igual que 2000");
            return;
        }

        if (ancho <= 0 || ancho > 2000) {
            ctrlPresentacion.muestraError("El ancho de la habitacion debe ser un numero entero mayor que 0 y menor o igual que 2000.");
            return;
        }
        if (largo <= 0 || largo > 2000) {
            ctrlPresentacion.muestraError("El largo de la habitacion debe ser un numero entero mayor que 0 y menor o igual que 2000.");
            return;
        }

        ctrlDominio.crearHabitacion(ancho.intValue(), largo.intValue());
        ctrlPresentacion.sincronizacionVistaCrearHabitacion_a_Principal();
    }
}
