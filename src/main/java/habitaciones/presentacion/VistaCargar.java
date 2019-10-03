package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.File;

import compartidas.*;
import habitaciones.gestion.*;
import habitaciones.dominio.modelos.*;
import habitaciones.dominio.controladores.CtrlDominio;

public class VistaCargar extends Vista {
    // Elementos de la ventana
    private JTextField textfieldPath;
    private JButton buttonBuscar;
    private JButton buttonCargar;
    private JButton buttonVolver;

    // Constructora y metodos publicos
    public VistaCargar(CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super("Cargar configuracion", ctrlPresentacion, ctrlDominio);
    }

    public void windowEvent_Close() {
        ctrlPresentacion.sincronizacionVistaCargar_a_Principal();
    }

    protected void inicializar_panelInformacion() {
        super.inicializar_panelInformacion();
        panelInformacion.setLayout(new GridLayout(2, 2));
        textfieldPath = new JTextField(10);
        panelInformacion.add(new JLabel("Path:"));
        panelInformacion.add(textfieldPath);
    }

    protected void inicializar_panelBotones() {
        super.inicializar_panelBotones();
        panelBotones.setLayout(new GridLayout(1,1));
        // Componentes
        // Anadir botones
        buttonBuscar = new JButton("Buscar");
        panelBotones.add(buttonBuscar);
        buttonCargar = new JButton("Cargar");
        panelBotones.add(buttonCargar);
        buttonVolver = new JButton("Volver");
        panelBotones.add(buttonVolver);
    }

    // Asignacion de listeners
    protected void asignar_listenersComponentes() {
        super.asignar_listenersComponentes();
        buttonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionPerformed_buttonBuscar(event);
            }
        });
        buttonCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionPerformed_buttonCargar(event);
            }
        });
        buttonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionPerformed_buttonVolver(event);
            }
        });
    }

    // Metodos de las interfaces Listener
    private void actionPerformed_buttonBuscar(ActionEvent event) {
        JFileChooser c = new JFileChooser();
        // Demonstrate "Open" dialog:
        int rVal = c.showOpenDialog(this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String path = c.getCurrentDirectory().toString();
            String name = c.getSelectedFile().getName();
            File f = new File(path, name);
            textfieldPath.setText(f.toString());
        }
        if (rVal == JFileChooser.CANCEL_OPTION) {
        }
    }

    private void actionPerformed_buttonCargar(ActionEvent event) {
        try {
            String path = textfieldPath.getText();
            ctrlDominio.cargar(path);
        } catch(java.io.FileNotFoundException e) {
            System.err.println("No se pudo cargar el archivo "+e.getMessage());
            return;
        }
        ctrlPresentacion.sincronizacionVistaCargar_a_Principal();
    }

    private void actionPerformed_buttonVolver(ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaCargar_a_Principal();
    }
}

