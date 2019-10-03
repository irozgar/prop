package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import habitaciones.dominio.modelos.enums.*;
import habitaciones.dominio.controladores.CtrlDominio;

public class VistaAnadirMueble extends Vista {
    // Componentes de la interficie grafica
    private JButton buttonAnadir;
    private JButton buttonVolver;
    private JTextField textfieldAncho;
    private JTextField textfieldLargo;
    private JTextField textfieldRed;
    private JTextField textfieldGreen;
    private JTextField textfieldBlue;
    private JLabel labelAncho;
    private JLabel labelLargo;
    private JLabel labelTipo;
    private JLabel labelRed;
    private JLabel labelGreen;
    private JLabel labelBlue;
    private JComboBox tipoCombo;

    // Constructor y metodos publicos
    public VistaAnadirMueble (CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super("Anadir Mueble", ctrlPresentacion, ctrlDominio);
    }

    public void windowEvent_Close() {
        ctrlPresentacion.sincronizacionVistaAnadirMueble_a_Principal();
    }

    // Resto de metodos privados
    protected void inicializar_panelInformacion() {
        super.inicializar_panelInformacion();

        labelAncho     = new JLabel("Ancho (racholas)");
        textfieldAncho = new JTextField(5);
        labelLargo     = new JLabel("Largo (racholas)");
        textfieldLargo = new JTextField(5);
        labelTipo      = new JLabel("Tipo");
        tipoCombo      = new JComboBox();
        labelRed       = new JLabel("Rojo (0..255)");
        labelGreen     = new JLabel("Verde (0..255)");
        labelBlue      = new JLabel("Blue (0..255)");
        textfieldRed   = new JTextField(3);
        textfieldGreen = new JTextField(3);
        textfieldBlue  = new JTextField(3);

        panelInformacion.setLayout(new GridLayout(6, 2));
        panelInformacion.add(labelAncho);
        panelInformacion.add(textfieldAncho);
        panelInformacion.add(labelLargo);
        panelInformacion.add(textfieldLargo);
        panelInformacion.add(labelTipo);
        panelInformacion.add(tipoCombo);
        panelInformacion.add(labelRed);
        panelInformacion.add(textfieldRed);
        panelInformacion.add(labelGreen);
        panelInformacion.add(textfieldGreen);
        panelInformacion.add(labelBlue);
        panelInformacion.add(textfieldBlue);

        for (TMueble t : TMueble.values()) {
            tipoCombo.addItem(t);
        }
    }

    protected void inicializar_panelBotones() {
        super.inicializar_panelBotones();
        panelBotones.setLayout(new GridLayout(2,1));
        // Componentes
        buttonAnadir = new JButton("Anadir");
        panelBotones.add(buttonAnadir);
        buttonVolver = new JButton("Volver");
        panelBotones.add(buttonVolver);
    }

    // Asignacion de listeners
    protected void asignar_listenersComponentes() {
        super.asignar_listenersComponentes();
        buttonAnadir.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonAnadir(event);
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
    public void actionPerformed_buttonAnadir(ActionEvent event) {
        String data_ancho;
        Integer ancho;
        try {
            data_ancho = textfieldAncho.getText();
            ancho     = Integer.parseInt(data_ancho);
        } catch(java.lang.NumberFormatException e) {
            ctrlPresentacion.muestraError("El ancho debe ser un numero entero mayor que 0.");
            return;
        }


        String data_largo;
        Integer largo;
        try {
            data_largo = textfieldLargo.getText();
            largo     = Integer.parseInt(data_largo);
        } catch(java.lang.NumberFormatException e) {
            ctrlPresentacion.muestraError("El alto debe ser un numero entero mayor que 0.");
            return;
        }
        TMueble tipo      = (TMueble)tipoCombo.getSelectedItem();

        Integer red;
        Integer green;
        Integer blue;

        try {
            red       = Integer.parseInt(textfieldRed.getText());
            green     = Integer.parseInt(textfieldGreen.getText());
            blue      = Integer.parseInt(textfieldBlue.getText());
        } catch(java.lang.NumberFormatException e) {
            ctrlPresentacion.muestraError("Los colores deben tener valores enteros desde el 0 al 255.");
            return;
        }
        if (ancho <= 0 || largo <= 0) {
            ctrlPresentacion.muestraError("El mueble debe tener anchura y longitud mayores que 0.");
            return;
        }

        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            ctrlPresentacion.muestraError("Los colores deben tener valores enteros desde el 0 al 255");
            return;
        }

        ctrlDominio.anadirMueble(tipo, largo, ancho, red, green, blue);
        ctrlPresentacion.sincronizacionVistaAnadirMueble_a_Principal();
    }

    public void actionPerformed_buttonVolver (ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaAnadirMueble_a_Principal();
    }
}
