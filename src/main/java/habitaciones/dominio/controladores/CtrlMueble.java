package habitaciones.dominio.controladores;

import java.util.*;

import habitaciones.dominio.modelos.*;
import habitaciones.dominio.modelos.enums.*;
import habitaciones.dominio.controladores.input.*;

public class CtrlMueble {

    private int idMueble = 0;
    private CtrlDominio ctrlDominio;

    public CtrlMueble(CtrlDominio ctrlDominio) {
        this.ctrlDominio = ctrlDominio;
    }

    public void crearMueble() {
        System.out.println("####### Anadir mueble:");
        int ancho;
        do {
            System.out.print("Ancho (baldosas): ");
            ancho = Input.getInt();
            if (ancho <= 0 || ancho > 100) {
                System.out.println("El ancho debe ser superior a 0 e inferior o igual a 100.");
            }
        } while (ancho <= 0 || ancho > 100);

        int largo;
        do {
            System.out.print("Largo (baldosas): ");
            largo = Input.getInt();
            if (largo <= 0 || largo > 100) {
                System.out.println("El largo debe ser superior a 0 e inferior o igual a 100.");
            }
        } while (largo <= 0 || largo > 100);

        TMueble tipo = null;
        do {
            System.out.println("Tipo de mueble [desconocido | mesa | silla | armario | electr_cocina | cama]: ");
            String stipo = Input.getString();
            if (stipo.equals("desconodido") || stipo.equals("DESCONOCIDO")) {
                tipo = TMueble.DESCONOCIDO;
            } else if (stipo.equals("mesa") || stipo.equals("MESA")) {
                tipo = TMueble.MESA;
            } else if (stipo.equals("silla") || stipo.equals("SILLA")) {
                tipo = TMueble.SILLA;
            } else if (stipo.equals("armario") || stipo.equals("ARMARIO")) {
                tipo = TMueble.ARMARIO;
            } else if (stipo.equals("electr_cocina") || stipo.equals("ELECTR_COCINA")) {
                tipo = TMueble.ELECTR_COCINA;
            } else if (stipo.equals("cama") || stipo.equals("cama")) {
                tipo = TMueble.CAMA;
            }
        } while (tipo == null);

        int red;
        int green;
        int blue;
        do {
            System.out.print("Rojo (0..255): ");
            red = Input.getInt();
        } while (red < 0 || red > 255);

        do {
            System.out.print("Verde (0..255): ");
            green = Input.getInt();
        } while (green < 0 || green > 255);

        do {
            System.out.print("Azul (0..255): ");
            blue = Input.getInt();
        } while (blue < 0 || blue > 255);

        ctrlDominio.anadirMueble(tipo, largo, ancho, red, green, blue);

        /*
        System.out.println("\nAnadir mueble:");
        System.out.print("Ancho (racholas): ");
        int ancho = Input.getInt();
        System.out.print("Largo (racholas): ");
        int largo = Input.getInt();
        System.out.println("Usamos la creadora de mueble.");
        return new Mueble(idMueble++, TMueble.SILLA, new Color(0,0,0), largo, ancho);*/
    }

    public void consultarMuebles() {
        ArrayList<Mueble> muebles = ctrlDominio.getMuebles();

        System.out.println("\n###### Mostrando lista de muebles");
        if (muebles.size() > 0) {
            for (Mueble mueble : muebles) {
                System.out.println("\nMueble con ID: " + mueble.getId());
                System.out.println("    Longitud: " + mueble.getLongitud());
                System.out.println("    Anchura: " + mueble.getAnchura());
                System.out.println("    Tipo: " +mueble.getTipoMueble());
                System.out.println("    Rojo: " +mueble.getRed());
                System.out.println("    Verde: "+mueble.getGreen());
                System.out.println("    Azul: "+mueble.getBlue());
            }
        } else {
            System.out.println("\nNo has definido ningun mueble.");
        }
    }

    public void borrarMueble() {
        System.out.println("\n###### Borrar mueble");
        System.out.print("Introduce el ID del mueble a borrar: ");
        int id = Input.getInt();

        Mueble m = null;
        boolean abort = false;
        do {
            m = ctrlDominio.buscaMueble(id);
            if (m == null) {
                System.out.print("Mueble no encontrado, introduce el id del mueble a borrar o -1 para salir: ");
                id = Input.getInt();
            }
        } while (abort == false && m == null);

        if (abort)
            return;

        ctrlDominio.borrarMueble(m);
        System.out.print("Mueble borrado");
    }
}
