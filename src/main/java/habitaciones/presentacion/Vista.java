package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import habitaciones.dominio.controladores.CtrlDominio;

public abstract class Vista extends JFrame {
    // Control de presentacion
    protected CtrlPresentacion ctrlPresentacion;

    // Control del dominio
    protected CtrlDominio ctrlDominio;

    // Elementos de la ventana
    protected JPanel panelContenidos;
    protected JPanel panelInformacion;
    protected JPanel panelBotones;

    // Constructora y metodos publicos
    public Vista(String titulo, CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super(titulo);
        this.ctrlDominio = ctrlDominio;
        this.ctrlPresentacion = ctrlPresentacion;
        inicializar_Componentes();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(
        new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                windowEvent_Close();
            }
        }
        );
    }

    public abstract void windowEvent_Close();

    public void hacerVisible() {
        this.pack();
        this.setLocationRelativeTo(null);
        if (this instanceof VistaLista) {
            Point p = this.getLocation();
            p.translate(300, 0);
            this.setLocation(p);
        }
        this.setVisible(true);
    }

    public void hacerInvisible() {
        this.desactivar();
        this.setVisible(false);
    }

    public void activar() {
        this.setEnabled(true);
    }

    public void desactivar() {
        this.setEnabled(false);
    }

    // Resto de metodos privados
    protected void inicializar_Componentes() {
        inicializar_Frame();
        inicializar_panelInformacion();
        inicializar_panelBotones();
        inicializar_panelContenidos();
        asignar_Frame();
        asignar_listenersComponentes();
        this.add(panelContenidos);
    }

    protected void inicializar_Frame() {
        // Tamanyo
        /* this.setMinimumSize(new Dimension(400,200)); */
        /* this.setPreferredSize(this.getMinimumSize()); */
        /* this.setResizable(false); */
        // Posicion y operaciones por defecto
        /* this.setLocationRelativeTo(null); */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected void asignar_Frame() {
        // Se agrega panelContenidos al contentPane (el panelContenidos se
        // podria ahorrar y trabajar directamente sobre el contentPane)
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.add(panelContenidos);
    }

    protected void inicializar_panelContenidos() {
        panelContenidos = new JPanel();
        // Layout
        panelContenidos.setLayout(new BorderLayout());
        // Paneles
        panelContenidos.add(panelBotones,BorderLayout.SOUTH);
        panelContenidos.add(panelInformacion,BorderLayout.CENTER);
    }

    protected void inicializar_panelInformacion() {
        panelInformacion = new JPanel();
    }

    protected void inicializar_panelBotones() {
        panelBotones = new JPanel();
    }

    protected void asignar_listenersComponentes() {
    }
}

