package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import habitaciones.dominio.modelos.Mueble;
import habitaciones.dominio.controladores.CtrlDominio;

public class VistaBorrarMueble extends Vista {
    // Elementos de la ventana
    private JLabel labelMueble;
    private JComboBox comboMueble;
    private JButton buttonBorrar;
    private JButton buttonVolver;

    // Constructora y metodos publicos
    public VistaBorrarMueble(CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super("Borrar Mueble", ctrlPresentacion, ctrlDominio);
    }

    public void windowEvent_Close() {
        ctrlPresentacion.sincronizacionVistaBorrarMueble_a_Principal();
    }

    protected void inicializar_panelInformacion() {
        super.inicializar_panelInformacion();
        // Anadir label mueble
        labelMueble = new JLabel("Mueble");
        panelInformacion.add(labelMueble);
        // Anadir comboBox
        comboMueble = new JComboBox();
        panelInformacion.add(comboMueble);
        ArrayList<Mueble> muebles = ctrlDominio.getMuebles();
        for (Mueble mueble : muebles) {
            comboMueble.addItem(mueble);
        }
    }

    protected void inicializar_panelBotones() {
        super.inicializar_panelBotones();
        panelBotones.setLayout(new GridLayout(2,1));
        // Componentes
        // Anadir botones
        buttonBorrar = new JButton("Borrar");
        panelBotones.add(buttonBorrar);
        buttonVolver = new JButton("Volver");
        panelBotones.add(buttonVolver);
    }

    // Asignacion de listeners
    protected void asignar_listenersComponentes() {
        super.asignar_listenersComponentes();
        buttonBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionPerformed_buttonBorrar(event);
            }
        });
        buttonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionPerformed_buttonVolver(event);
            }
        });
    }

    // Metodos de las interfaces Listener
    private void actionPerformed_buttonBorrar(ActionEvent event) {
        ctrlDominio.borrarMueble((Mueble)comboMueble.getSelectedItem());
        ctrlPresentacion.sincronizacionVistaBorrarMueble_a_Principal();
    }

    private void actionPerformed_buttonVolver(ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaBorrarMueble_a_Principal();
    }
}

