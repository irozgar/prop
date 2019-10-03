package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import habitaciones.dominio.modelos.ContenidoPared;
import habitaciones.dominio.controladores.CtrlDominio;

public class VistaBorrarContenidoPared extends Vista {
    // Elementos de la ventana
    private JLabel labelContenidoPared;
    private JComboBox comboContenidoPared;
    private JButton buttonBorrar;
    private JButton buttonVolver;

    // Constructora y metodos publicos
    public VistaBorrarContenidoPared(CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super("Borrar Puerta/Ventana", ctrlPresentacion, ctrlDominio);
    }

    public void windowEvent_Close() {
        ctrlPresentacion.sincronizacionVistaBorrarContenidoPared_a_Principal();
    }

    protected void inicializar_panelInformacion() {
        super.inicializar_panelInformacion();

        labelContenidoPared = new JLabel("Puerta/Ventana");
        comboContenidoPared = new JComboBox();

        panelInformacion.add(labelContenidoPared);
        panelInformacion.add(comboContenidoPared);

        ArrayList<ContenidoPared> contenidosPared = ctrlDominio.getContenidosPared();
        for (ContenidoPared cp : contenidosPared) {
            comboContenidoPared.addItem(cp);
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
        ctrlDominio.borrarContenidoPared((ContenidoPared) comboContenidoPared.getSelectedItem());
        ctrlPresentacion.sincronizacionVistaBorrarContenidoPared_a_Principal();
    }

    private void actionPerformed_buttonVolver(ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaBorrarContenidoPared_a_Principal();
    }
}

