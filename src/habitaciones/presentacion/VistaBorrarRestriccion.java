package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import habitaciones.dominio.modelos.restricciones.*;
import habitaciones.dominio.controladores.CtrlDominio;

public class VistaBorrarRestriccion extends Vista {
    private JLabel labelRestriccion;
    private JComboBox comboRestriccion;
    private JButton buttonBorrar;
    private JButton buttonVolver;


    public VistaBorrarRestriccion(CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super("Borrar Restriccion", ctrlPresentacion, ctrlDominio);
    }

    public void windowEvent_Close() {
        ctrlPresentacion.sincronizacionVistaBorrarRestriccion_a_Principal();
    }

    protected void inicializar_panelInformacion() {
        super.inicializar_panelInformacion();

        labelRestriccion = new JLabel("Restriccion");
        comboRestriccion = new JComboBox();

        panelInformacion.add(labelRestriccion);
        panelInformacion.add(comboRestriccion);

        ArrayList<RestriccionDistancia> restricciones = ctrlDominio.getRestriccionesDistancia();
        for (RestriccionDistancia restriccion : restricciones) {
            comboRestriccion.addItem(restriccion);
        }
    }

    protected void inicializar_panelBotones() {
        super.inicializar_panelBotones();
        panelBotones.setLayout(new GridLayout(2,1));

        buttonBorrar = new JButton("Borrar");
        buttonVolver = new JButton("Volver");

        panelBotones.add(buttonBorrar);
        panelBotones.add(buttonVolver);
    }

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

    private void actionPerformed_buttonBorrar(ActionEvent event) {
        ctrlDominio.borrarRestriccionDistancia((RestriccionDistancia) comboRestriccion.getSelectedItem());
        ctrlPresentacion.sincronizacionVistaBorrarRestriccion_a_Principal();
    }

    private void actionPerformed_buttonVolver(ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaBorrarRestriccion_a_Principal();
    }
}

