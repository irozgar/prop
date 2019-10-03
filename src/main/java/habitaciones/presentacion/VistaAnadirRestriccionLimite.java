package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import habitaciones.dominio.modelos.Mueble;
import habitaciones.dominio.modelos.restricciones.*;
import habitaciones.dominio.controladores.CtrlDominio;

public class VistaAnadirRestriccionLimite extends Vista {
    // Componentes de la interficie grafica

    private JButton buttonAnadirAlCjto;
    private JButton buttonAnadir;
    private JButton buttonVolver;
    private JLabel labelMueble;
    private JComboBox comboMueble;
    private JLabel labelTipo;
    private JComboBox comboTipo;
    private JLabel labelLimite;
    private JTextField textfieldLimite;

    private static String limite_minimo = "Limite Minimo";
    private static String limite_maximo = "Limite Maximo";
    private ArrayList<Mueble> CjtoMuebles = new ArrayList<Mueble>();

    // Constructor y metodos publicos
    public VistaAnadirRestriccionLimite(CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super("Anadir Restriccion Limite", ctrlPresentacion, ctrlDominio);
    }

    public void windowEvent_Close() {
        ctrlPresentacion.sincronizacionVistaAnadirRestriccion_a_Principal();
    }

    // Resto de metodos privados
    protected void inicializar_panelInformacion() {
        super.inicializar_panelInformacion();
        panelInformacion.setLayout(new GridLayout(4, 2));
        // Seleccion del mueble
        labelMueble = new JLabel("Mueble");
        textfieldLimite = new JTextField(5);
        panelInformacion.add(labelMueble);
        comboMueble = new JComboBox();
        panelInformacion.add(comboMueble);
        // Seleccion de tipo de mueble
        labelTipo = new JLabel("Tipo");
        panelInformacion.add(labelTipo);
        comboTipo = new JComboBox();
        comboTipo.addItem(limite_minimo);
        comboTipo.addItem(limite_maximo);
        panelInformacion.add(comboTipo);
        // Input del limite
        labelLimite = new JLabel("Limite");
        panelInformacion.add(labelLimite);
        textfieldLimite = new JTextField(5);
        panelInformacion.add(textfieldLimite);
        // Inflate comboBoxes de los muebles
        ArrayList<Mueble> muebles = ctrlDominio.getMuebles();
        for (Mueble mueble : muebles) {
            comboMueble.addItem(mueble);
        }
    }

    protected void inicializar_panelBotones() {
        super.inicializar_panelBotones();
        panelBotones.setLayout(new GridLayout(3,1));
        // Componentes
        buttonAnadirAlCjto = new JButton("Anadir al conjunto");
        panelBotones.add(buttonAnadirAlCjto);
        buttonAnadir = new JButton("Anadir");
        panelBotones.add(buttonAnadir);
        buttonVolver = new JButton("Volver");
        panelBotones.add(buttonVolver);
    }


    // Asignacion de listeners
    protected void asignar_listenersComponentes() {
        super.asignar_listenersComponentes();

        buttonAnadirAlCjto.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonAnadirAlCjto(event);
            }
        });

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
    private void actionPerformed_buttonAnadirAlCjto(ActionEvent event) {
        Mueble m = (Mueble)comboMueble.getSelectedItem();
        CjtoMuebles.add(m);
    }

    private void actionPerformed_buttonAnadir(ActionEvent event) {
        String data_limite = textfieldLimite.getText();
        Integer cantidad = Integer.parseInt(data_limite);
        String tipo = (String)comboTipo.getSelectedItem();
        if (tipo.equals(limite_minimo));
        //ctrlDominio.anadirRestriccionLimiteMinimo(cantidad.intValue(), CjtoMuebles);
        else if (tipo.equals(limite_maximo));
        //ctrlDominio.anadirRestriccionLimiteMaximo(cantidad.intValue(), CjtoMuebles);
        ctrlPresentacion.sincronizacionVistaAnadirRestriccion_a_Principal();
    }

    private void actionPerformed_buttonVolver(ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaAnadirRestriccion_a_Principal();
    }
}
