package habitaciones.presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import habitaciones.dominio.modelos.Habitacion;
import habitaciones.dominio.controladores.CtrlDominio;

public class VistaPrincipal extends Vista {
    private JButton buttonCrearHabitacion;
    private JButton buttonAnadirContenidoPared;
    private JButton buttonBorrarContenidoPared;
    private JButton buttonAnadirMueble;
    private JButton buttonBorrarMueble;
    private JButton buttonAnadirRestriccion;
    private JButton buttonBorrarRestriccion;
    private JButton buttonGenerarSolucion;
    private JButton buttonGuardarHabitacion;
    private JButton buttonCargarHabitacion;
    private JButton buttonCleanHabitacion;
    private JButton buttonSalir;

    // Menus
    private JMenuBar menubarVista;
    private JMenu menuFile;
    private JMenuItem menuitemQuit;
    private JMenu menuOpciones;
    private JMenuItem menuitemCrearHabitacion;
    private JMenuItem menuitemAnadirMueble;
    private JMenuItem menuitemBorrarMueble;
    private JMenuItem menuitemAnadirRestriccion;
    private JMenuItem menuitemBorrarRestriccion;
    private JMenuItem menuitemGenerarSolucion;
    private JMenuItem menuitemGuardarHabitacion;
    private JMenuItem menuitemCargarHabitacion;
    private JMenuItem menuitemSalir;

    // Constructor y metodos publicos
    public VistaPrincipal(CtrlPresentacion ctrlPresentacion, CtrlDominio ctrlDominio) {
        super("Aplicacion Habitacion", ctrlPresentacion, ctrlDominio);
    }

    public void windowEvent_Close() {
        // Abre una ventana con un dialogo
        VistaDialogo vistaDialogo = new VistaDialogo();
        String[] strBotones = {"Si","No"};
        int isel = vistaDialogo.setDialogo("Abandonar aplicacion","Desea salir?",strBotones,2);
        System.out.println("Resultado del dialogo: " + isel + " " + strBotones[isel]);
        if (isel == 0) System.exit(0);

    }

    // Metodos de las interfaces Listener
    public void actionPerformed_buttonCrearHabitacion (ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaPrincipal_a_vistaCrearHabitacion();
    }

    public void actionPerformed_buttonAnadirContenidoPared(ActionEvent event) {
        Habitacion hab = ctrlDominio.getHabitacion();
        if (hab.getFilas() <= 0 || hab.getColumnas() <= 0) {
            ctrlPresentacion.muestraError("Debes crear una habitacion antes de colocar puertas y ventanas");
            return;
        }
        ctrlPresentacion.sincronizacionVistaPrincipal_a_vistaAnadirContenidoPared();
    }

    public void actionPerformed_buttonBorrarContenidoPared(ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaPrincipal_a_vistaBorrarContenidoPared();
    }

    public void actionPerformed_buttonAnadirMueble (ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaPrincipal_a_vistaAnadirMueble();
    }

    public void actionPerformed_buttonBorraMueble (ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaPrincipal_a_vistaBorrarMueble();
    }

    public void actionPerformed_buttonAnadirRestriccion (ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaPrincipal_a_vistaAnadirRestriccion();
    }

    public void actionPerformed_buttonBorrarRestriccion (ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaPrincipal_a_vistaBorrarRestriccion();
    }

    public void actionPerformed_buttonGenerarSolucion (ActionEvent event) {
        //ctrlPresentacion.sincronizacionVistaPrincipal_a_vistaGenerar();
        Habitacion hab = ctrlDominio.getHabitacion();
        if (hab.getFilas() <= 0 || hab.getColumnas() <= 0) {
            ctrlPresentacion.muestraError("Debes crear una habitacion antes de poder generar");
            return;
        }
        ctrlPresentacion.sincronizacionVistaPrincipal_a_vistaTablero();
    }

    public void actionPerformed_buttonGuardarHabitacion(ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaPrincipal_a_vistaGuardar();
    }

    public void actionPerformed_buttonCleanHabitacion(ActionEvent event) {
        ctrlDominio.cleanHabitacion();
        ctrlPresentacion.updateLista();
    }

    public void actionPerformed_buttonCargarHabitacion(ActionEvent event) {
        ctrlPresentacion.sincronizacionVistaPrincipal_a_vistaCargar();
    }

    public void actionPerformed_buttonSalir (ActionEvent event) {
        // Abre una ventana con un dialogo
        VistaDialogo vistaDialogo = new VistaDialogo();
        String[] strBotones = {"Si","No"};
        int isel = vistaDialogo.setDialogo("Abandonar aplicacion","Desea salir?",strBotones,2);
        System.out.println("Resultado del dialogo: " + isel + " " + strBotones[isel]);
        if (isel == 0) System.exit(0);
    }

    // Asignacion de listeners
    protected void asignar_listenersComponentes() {
        // Listeners para los botones
        buttonCrearHabitacion.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonCrearHabitacion(event);
            }
        });

        buttonAnadirContenidoPared.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonAnadirContenidoPared(event);
            }
        });

        buttonBorrarContenidoPared.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonBorrarContenidoPared(event);
            }
        });

        buttonAnadirMueble.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonAnadirMueble(event);
            }
        });

        buttonBorrarMueble.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonBorraMueble(event);
            }
        });

        buttonAnadirRestriccion.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonAnadirRestriccion(event);
            }
        });
        buttonBorrarRestriccion.addActionListener
        (new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionPerformed_buttonBorrarRestriccion(event);
            }
        });

        buttonGenerarSolucion.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonGenerarSolucion(event);
            }
        });

        buttonGuardarHabitacion.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonGuardarHabitacion(event);
            }
        });

        buttonCargarHabitacion.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonCargarHabitacion(event);
            }
        });

        buttonCleanHabitacion.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonCleanHabitacion(event);
            }
        });

        buttonSalir.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                actionPerformed_buttonSalir(event);
            }
        });

        // Listeners para las opciones de menu
        /**
         * Listener Crear Habitación
         */
        menuitemCrearHabitacion.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                actionPerformed_buttonCrearHabitacion(event);
            }
        });

        /**
         * Listener Añadir mueble
         */
        menuitemAnadirMueble.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                actionPerformed_buttonAnadirMueble(event);
            }
        });

        /**
         * Listener Borra mueble
         */
        menuitemBorrarMueble.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                actionPerformed_buttonBorraMueble(event);
            }
        });

        /**
         * Listener Añadir restricción
         */
        menuitemAnadirRestriccion.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                actionPerformed_buttonAnadirRestriccion(event);
            }
        });

        /**
         * Listener Borrar Restricción
         */
        menuitemBorrarRestriccion.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                actionPerformed_buttonBorrarRestriccion(event);
            }
        });

        /**
         * Listener Generar solución
         */
        menuitemGenerarSolucion.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                actionPerformed_buttonGenerarSolucion(event);
            }
        });

        /**
         * Listener Guardar Habitación
         */
        menuitemGuardarHabitacion.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                actionPerformed_buttonGuardarHabitacion(event);
            }
        });

        /**
         * Listener Cargar Habitación
         */
        menuitemCargarHabitacion.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                actionPerformed_buttonCargarHabitacion(event);
            }
        });

        /**
         * Listener Salir
         */
        menuitemSalir.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                actionPerformed_buttonSalir(event);
            }
        });

        /**
         * Listener Quit
         */
        menuitemQuit.addActionListener
        (new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                System.exit(0);
            }
        });
        // Listeners para el resto de componentes
    }

    // Resto de metodos privados
    protected void inicializar_menubarVista() {
        menubarVista = new JMenuBar();
        menuFile = new JMenu("File");
        menuitemQuit = new JMenuItem("Quit");
        menuOpciones = new JMenu("Opciones");
        menuitemCrearHabitacion = new JMenuItem("Crear Habitacion");
        menuitemAnadirMueble = new JMenuItem("Anadir mueble");
        menuitemBorrarMueble = new JMenuItem("Borrar mueble");
        menuitemAnadirRestriccion = new JMenuItem("Anadir restriccion");
        menuitemBorrarRestriccion = new JMenuItem("Borrar restriccion");
        menuitemGenerarSolucion = new JMenuItem("Generar solucion");
        menuitemGuardarHabitacion = new JMenuItem("Guardar Habitacion");
        menuitemCargarHabitacion = new JMenuItem("Cargar Habitacion");
        menuitemSalir = new JMenuItem("Salir");
        menuFile.add(menuitemQuit);
        menuOpciones.add(menuitemCrearHabitacion);
        menuOpciones.add(menuitemAnadirMueble);
        menuOpciones.add(menuitemBorrarMueble);
        menuOpciones.add(menuitemAnadirRestriccion);
        menuOpciones.add(menuitemBorrarRestriccion);
        menuOpciones.add(menuitemGenerarSolucion);
        menuOpciones.add(menuitemGuardarHabitacion);
        menuOpciones.add(menuitemCargarHabitacion);
        menuOpciones.add(menuitemSalir);
        menubarVista.add(menuFile);
        menubarVista.add(menuOpciones);
        this.setJMenuBar(menubarVista);
    }

    protected void inicializar_panelInformacion() {
        super.inicializar_panelInformacion();
        inicializar_menubarVista();
    }

    protected void inicializar_panelBotones() {
        super.inicializar_panelBotones();
        // Layout
        panelBotones.setLayout(new GridLayout(12,1));
        // Componentes
        buttonCrearHabitacion = new JButton("Crear Habitacion");
        buttonAnadirContenidoPared = new JButton("Anadir Puerta/Ventana");
        buttonBorrarContenidoPared = new JButton("Quitar Puerta/Ventana");
        buttonAnadirMueble = new JButton("Anadir mueble");
        buttonBorrarMueble = new JButton("Borrar mueble");
        buttonAnadirRestriccion = new JButton("Anadir restriccion");
        buttonBorrarRestriccion = new JButton("Borrar restriccion");
        buttonGenerarSolucion = new JButton("Generar solucion");
        buttonGuardarHabitacion = new JButton("Guardar Habitacion");
        buttonCargarHabitacion = new JButton("Cargar Habitacion");
        buttonCleanHabitacion = new JButton("Borrar Todo");
        buttonSalir = new JButton("Salir");
        panelBotones.add(buttonCrearHabitacion);
        panelBotones.add(buttonAnadirContenidoPared);
        panelBotones.add(buttonBorrarContenidoPared);
        panelBotones.add(buttonAnadirMueble);
        panelBotones.add(buttonBorrarMueble);
        panelBotones.add(buttonAnadirRestriccion);
        panelBotones.add(buttonBorrarRestriccion);
        panelBotones.add(buttonGenerarSolucion);
        panelBotones.add(buttonGuardarHabitacion);
        panelBotones.add(buttonCargarHabitacion);
        panelBotones.add(buttonCleanHabitacion);
        panelBotones.add(buttonSalir);

        // Tooltips
        buttonCrearHabitacion.setToolTipText("Llama al controlador de dominio con la informacion del ComboBox");
        buttonCargarHabitacion.setToolTipText("Abre una nueva ventana sincronizada");
        buttonGuardarHabitacion.setToolTipText("Cambia el panel de informacion");
        buttonSalir.setToolTipText("Abre un Dialogo modal simple");
    }
}
