package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import habitaciones.dominio.modelos.Mueble;
import habitaciones.dominio.modelos.restricciones.*;
import habitaciones.dominio.controladores.CtrlDominio;

public class VistaAnadirRestriccionDistancia extends Vista {
    // Componentes de la interficie grafica
    private JButton buttonAnadir;
    private JButton buttonVolver;
    private JLabel labelMueble1;
    private JComboBox comboMueble1;
    private JLabel labelMueble2;
    private JComboBox comboMueble2;
    private JLabel labelTipo;
    private JComboBox comboTipo;
    private JLabel labelDistancia;
    private JTextField textfieldDistancia;

    private static String distancia_minima = "Distancia Minima";
    private static String distancia_maxima = "Distancia Maxima";

    // Constructor y metodos publicos
    public VistaAnadirRestriccionDistancia(CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super("Anadir Restriccion Distancia", ctrlPresentacion, ctrlDominio);
    }

    public void windowEvent_Close() {
        ctrlPresentacion.sincronizacionVistaAnadirRestriccion_a_Principal();
    }

    // Resto de metodos privados
    protected void inicializar_panelInformacion() {
        super.inicializar_panelInformacion();
        panelInformacion.setLayout(new GridLayout(4, 2));
        // Seleccion del primer mueble
        labelMueble1 = new JLabel("Mueble 1");
        textfieldDistancia = new JTextField(5);
        panelInformacion.add(labelMueble1);
        comboMueble1 = new JComboBox();
        panelInformacion.add(comboMueble1);
        // Seleccion del segundo mueble
        labelMueble2 = new JLabel("Mueble 2");
        panelInformacion.add(labelMueble2);
        comboMueble2 = new JComboBox();
        panelInformacion.add(comboMueble2);
        // Seleccion de tipo de mueble
        labelTipo = new JLabel("Tipo");
        panelInformacion.add(labelTipo);
        comboTipo = new JComboBox();
        comboTipo.addItem(distancia_minima);
        comboTipo.addItem(distancia_maxima);
        panelInformacion.add(comboTipo);
        // Input de la distancia
        labelDistancia = new JLabel("Distancia");
        panelInformacion.add(labelDistancia);
        textfieldDistancia = new JTextField(5);
        panelInformacion.add(textfieldDistancia);
        // Inflate comboBoxes de los muebles
        ArrayList<Mueble> muebles = ctrlDominio.getMuebles();
        for (Mueble mueble : muebles) {
            comboMueble1.addItem(mueble);
            comboMueble2.addItem(mueble);
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
    private void actionPerformed_buttonAnadir(ActionEvent event) {
        Mueble m1 = (Mueble)comboMueble1.getSelectedItem();
        Mueble m2 = (Mueble)comboMueble2.getSelectedItem();
        String data_distancia = textfieldDistancia.getText();
        Integer distancia = Integer.parseInt(data_distancia);
        if (m1.getId() == m2.getId()) {
            ctrlPresentacion.muestraError("Un mueble ser el unico al que afecte una restriccion de distancia");
            return;
        }
        if (distancia <= 0) {
            ctrlPresentacion.muestraError("La distancia debe ser mayor que 0");
            return;
        }
        String tipo = (String)comboTipo.getSelectedItem();
        if (tipo.equals(distancia_minima)) {
            int id = ctrlDominio.getRestriccionId();
            RestriccionDistanciaMinima restriccion = new RestriccionDistanciaMinima(id, distancia.intValue(), m1, m2);
            ctrlDominio.anadirRestriccionDistancia(restriccion);
        } else if (tipo.equals(distancia_maxima)) {
            int id = ctrlDominio.getRestriccionId();
            RestriccionDistanciaMaxima restriccion = new RestriccionDistanciaMaxima(id, distancia.intValue(), m1, m2);
            ctrlDominio.anadirRestriccionDistancia(restriccion);
        }
        ctrlPresentacion.sincronizacionVistaAnadirRestriccion_a_Principal();
    }

    private void actionPerformed_buttonVolver(ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaAnadirRestriccion_a_Principal();
    }
}
