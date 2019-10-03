package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import habitaciones.dominio.modelos.enums.*;
import habitaciones.dominio.controladores.CtrlDominio;

public class VistaAnadirContenidoPared extends Vista {
    // Componentes de la interficie grafica
    private JButton buttonAnadir;
    private JButton buttonVolver;
    private JTextField textfieldFila;
    private JTextField textfieldColumna;
    private JTextField textfieldTamano;
    private JLabel labelFila;
    private JLabel labelColumna;
    private JLabel labelTamano;
    private JLabel labelTipo;
    private JComboBox tipoCombo;

    // Constructor y metodos publicos
    public VistaAnadirContenidoPared (CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super("Anadir Puerta/Ventana", ctrlPresentacion, ctrlDominio);
    }

    public void windowEvent_Close() {
        ctrlPresentacion.sincronizacionVistaAnadirContenidoPared_a_Principal();
    }

    // Resto de metodos privados
    protected void inicializar_panelInformacion() {
        super.inicializar_panelInformacion();

        labelFila         = new JLabel("Fila");
        textfieldFila     = new JTextField(5);
        labelColumna      = new JLabel("Columna");
        textfieldColumna  = new JTextField(5);
        labelTipo         = new JLabel("Tipo");
        tipoCombo         = new JComboBox();
        labelTamano       = new JLabel("Tamano");
        textfieldTamano  = new JTextField(5);

        panelInformacion.setLayout(new GridLayout(6, 2));
        panelInformacion.add(labelFila);
        panelInformacion.add(textfieldFila);
        panelInformacion.add(labelColumna);
        panelInformacion.add(textfieldColumna);
        panelInformacion.add(labelTipo);
        panelInformacion.add(tipoCombo);
        panelInformacion.add(labelTamano);
        panelInformacion.add(textfieldTamano);

        for (TContenidoPared t : TContenidoPared.values()) {
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
        Integer fila;
        Integer columna;
        Integer tamano;

        try {
            fila = Integer.parseInt(textfieldFila.getText());
        } catch (java.lang.NumberFormatException e) {
            ctrlPresentacion.muestraError("El numero de fila debe ser un entero mayor o igual que 0.");
            return;
        }

        try {
            columna = Integer.parseInt(textfieldColumna.getText());
        } catch (java.lang.NumberFormatException e) {
            ctrlPresentacion.muestraError("El numero de columna debe ser un entero mayor o igual que 0.");
            return;
        }

        TContenidoPared tipo = (TContenidoPared)tipoCombo.getSelectedItem();

        try {
            tamano = Integer.parseInt(textfieldTamano.getText());
        } catch (java.lang.NumberFormatException e) {
            ctrlPresentacion.muestraError("El tamano debe ser un numero entero mayor que 0.");
            return;
        }


        if (fila < 0) {
            ctrlPresentacion.muestraError("El numero de fila debe ser un entero mayor o igual que 0.");
            return;
        }

        if (columna < 0) {
            ctrlPresentacion.muestraError("El numero de columna debe ser un entero mayor o igual que 0.");
            return;
        }

        if (tamano <= 0) {
            ctrlPresentacion.muestraError("El tamano debe ser un numero entero mayor que 0.");
            return;
        }

        try {
            ctrlDominio.anadirContenidoPared(tipo, fila, columna, tamano);
            ctrlPresentacion.sincronizacionVistaAnadirContenidoPared_a_Principal();
        } catch(Exception e) {
            ctrlPresentacion.muestraError(e.getMessage());
            return;
        }
    }

    public void actionPerformed_buttonVolver (ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaAnadirContenidoPared_a_Principal();
    }
}
