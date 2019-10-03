package habitaciones.presentacion;

import java.util.*;
import java.awt.Point;

import habitaciones.dominio.controladores.CtrlDominio;

public class CtrlPresentacion {

    private CtrlDominio ctrlDominio;
    private VistaPrincipal vistaPrincipal;
    private VistaLista vistaLista;
    private VistaCrearHabitacion vistaCrearHabitacion;
    private VistaAnadirContenidoPared vistaAnadirContenidoPared;
    private VistaBorrarContenidoPared vistaBorrarContenidoPared;
    private VistaAnadirMueble vistaAnadirMueble;
    private VistaBorrarMueble vistaBorrarMueble;
    private VistaBorrarRestriccion vistaBorrarRestriccion;
    private VistaAnadirRestriccion vistaAnadirRestriccion;
    private VistaAnadirRestriccionDistancia vistaAnadirRestriccionDistancia;
    private VistaAnadirRestriccionLimite vistaAnadirRestriccionLimite;
    private VistaTablero vistaTablero;
    private VistaGuardar vistaGuardar;
    private VistaCargar vistaCargar;

    // Constructor y metodos de inicializacion
    public CtrlPresentacion() {
        ctrlDominio = new CtrlDominio();
    }

    public void inicializarPresentacion() {
        ctrlDominio.inicializarCtrlDominio();
        vistaPrincipal =  new VistaPrincipal(this, ctrlDominio);
        vistaPrincipal.hacerVisible();
        vistaLista = new VistaLista(this, ctrlDominio);
        vistaLista.update();
        vistaLista.hacerVisible();
    }

    // Metodos publicos
    public void updateLista() {
        vistaLista.update();
    }

    public Vista  getPrincipal() {
        return vistaPrincipal;
    }

    // Metodos de sincronizacion entre vistas
    /* VISTA CREAR HABITACION */
    public void sincronizacionVistaPrincipal_a_vistaCrearHabitacion() {
        vistaPrincipal.desactivar();
        vistaCrearHabitacion = new VistaCrearHabitacion(this, ctrlDominio);
        vistaCrearHabitacion.hacerVisible();
    }

    public void sincronizacionVistaCrearHabitacion_a_Principal() {
        vistaCrearHabitacion.hacerInvisible();
        vistaPrincipal.activar();
        vistaLista.update();
    }

    /* VISTA ANADIR PUERTA/VENTANA */
    public void sincronizacionVistaPrincipal_a_vistaAnadirContenidoPared() {
        vistaPrincipal.desactivar();
        vistaAnadirContenidoPared = new VistaAnadirContenidoPared(this, ctrlDominio);
        vistaAnadirContenidoPared.hacerVisible();
    }

    public void sincronizacionVistaAnadirContenidoPared_a_Principal() {
        vistaAnadirContenidoPared.hacerInvisible();
        vistaPrincipal.activar();
        vistaLista.update();
    }

    /* VISTA BORRAR PUERTA/VENTANA */
    public void sincronizacionVistaPrincipal_a_vistaBorrarContenidoPared() {
        vistaPrincipal.desactivar();
        vistaBorrarContenidoPared = new VistaBorrarContenidoPared(this, ctrlDominio);
        vistaBorrarContenidoPared.hacerVisible();
    }

    public void sincronizacionVistaBorrarContenidoPared_a_Principal() {
        vistaBorrarContenidoPared.hacerInvisible();
        vistaPrincipal.activar();
        vistaLista.update();
    }

    /* VISTA ANADIR MUEBLE */
    public void sincronizacionVistaPrincipal_a_vistaAnadirMueble() {
        vistaPrincipal.desactivar();
        vistaAnadirMueble = new VistaAnadirMueble(this, ctrlDominio);
        vistaAnadirMueble.hacerVisible();
    }

    public void sincronizacionVistaAnadirMueble_a_Principal() {
        vistaAnadirMueble.hacerInvisible();
        vistaPrincipal.activar();
        vistaLista.update();
    }


    /* VISTA BORRAR MUEBLE */
    public void sincronizacionVistaPrincipal_a_vistaBorrarMueble() {
        vistaPrincipal.desactivar();
        vistaBorrarMueble = new VistaBorrarMueble(this, ctrlDominio);
        vistaBorrarMueble.hacerVisible();
    }

    public void sincronizacionVistaBorrarMueble_a_Principal() {
        vistaBorrarMueble.hacerInvisible();
        vistaPrincipal.activar();
        vistaLista.update();
    }

    /* VISTA ANADIR RESTRICCION */
    public void sincronizacionVistaPrincipal_a_vistaAnadirRestriccion() {
        vistaPrincipal.desactivar();
        vistaAnadirRestriccion = new VistaAnadirRestriccion(this, ctrlDominio);
        vistaAnadirRestriccion.hacerVisible();
    }

    public void sincronizacionVistaAnadirRestriccion_a_Principal() {
        vistaAnadirRestriccion.hacerInvisible();
        vistaPrincipal.activar();
        vistaLista.update();
        if (vistaAnadirRestriccionDistancia != null &&
                vistaAnadirRestriccionDistancia.isVisible())
            vistaAnadirRestriccionDistancia.hacerInvisible();
        else  if (vistaAnadirRestriccionLimite != null &&
                  vistaAnadirRestriccionLimite.isVisible())
            vistaAnadirRestriccionLimite.hacerInvisible();
    }

    public void sincronizacionVistaPrincipal_a_vistaBorrarRestriccion() {
        vistaPrincipal.desactivar();
        vistaBorrarRestriccion = new VistaBorrarRestriccion(this, ctrlDominio);
        vistaBorrarRestriccion.hacerVisible();
    }

    public void sincronizacionVistaBorrarRestriccion_a_Principal() {
        vistaBorrarRestriccion.hacerInvisible();
        vistaPrincipal.activar();
        vistaLista.update();
    }

    public void sincronizacionVistaAnadirRestriccion_a_AnadirRestriccionDistancia() {
        vistaAnadirRestriccion.hacerInvisible();
        vistaAnadirRestriccionDistancia = new VistaAnadirRestriccionDistancia(this, ctrlDominio);
        vistaAnadirRestriccionDistancia.hacerVisible();
    }

    public void sincronizacionVistaAnadirRestriccion_a_AnadirRestriccionLimite() {
        vistaAnadirRestriccion.hacerInvisible();
        vistaAnadirRestriccionLimite = new VistaAnadirRestriccionLimite(this, ctrlDominio);
        vistaAnadirRestriccionLimite.hacerVisible();
    }

    /* VISTA GENERAR
    public void sincronizacionVistaPrincipal_a_vistaGenerar() {
      vistaPrincipal.desactivar();
      vistaGenerar = new VistaGenerar(this, ctrlDominio);
      vistaGenerar.hacerVisible();
    }*/

    /*public void sincronizacionVistaGenerar_a_Principal() {
      vistaGenerar.hacerInvisible();
      vistaPrincipal.activar();
      vistaLista.update();
    }*/

    /*VISTA TABLERO*/

    public void sincronizacionVistaPrincipal_a_vistaTablero() {
        vistaPrincipal.desactivar();
        vistaTablero = new VistaTablero(this, ctrlDominio);
        vistaTablero.hacerVisible();
    }

    public void sincronizacionVistaTablero_a_Principal() {
        vistaTablero.hacerInvisible();
        vistaPrincipal.activar();
    }

    /* VISTA GUARDAR */
    public void sincronizacionVistaPrincipal_a_vistaGuardar() {
        vistaPrincipal.desactivar();
        vistaGuardar = new VistaGuardar(this, ctrlDominio);
        vistaGuardar.hacerVisible();
    }

    public void sincronizacionVistaGuardar_a_Principal() {
        vistaGuardar.hacerInvisible();
        vistaPrincipal.activar();
        vistaLista.update();
    }

    /* VISTA CARGAR */
    public void sincronizacionVistaPrincipal_a_vistaCargar() {
        vistaPrincipal.desactivar();
        vistaCargar = new VistaCargar(this, ctrlDominio);
        vistaCargar.hacerVisible();
    }

    public void sincronizacionVistaCargar_a_Principal() {
        vistaCargar.hacerInvisible();
        vistaPrincipal.activar();
        vistaLista.update();
    }

    public void muestraError(String mensaje) {
        VistaDialogo vd = new VistaDialogo();
        String[] strBotones = new String[1];
        strBotones[0] = "Cancelar";
        vd.setDialogo("ERROR", mensaje, strBotones, 0);
    }
}
