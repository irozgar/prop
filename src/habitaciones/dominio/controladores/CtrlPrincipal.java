package habitaciones.dominio.controladores;

import java.util.*;

import compartidas.*;

import habitaciones.dominio.modelos.*;
import habitaciones.dominio.modelos.enums.*;
import habitaciones.gestion.*;
import habitaciones.dominio.modelos.restricciones.*;
import habitaciones.dominio.controladores.input.*;

public class CtrlPrincipal {
    private static CtrlDominio ctrlDominio;
    private static CtrlMueble  ctrlMueble;
    private static CtrlGenerador ctrlGenerador;
    private static Habitacion habitacion;
    private static ArrayList<Mueble> muebles;

    public static void main (String [] args) {
        init();

        muebles = new ArrayList<Mueble>();
        int option = menu();
        while (option != 0) {
            switch (option) {
            case 1:
                ctrlDominio.setHabitacion(CtrlHabitacion.crearHabitacion());
                break;
            case 2:
                ctrlMueble.crearMueble();
                break;
            case 3:
                ctrlMueble.borrarMueble();
                break;
            case 4:
                printInfoHabitacion();
                break;
            case 5:
                ctrlMueble.consultarMuebles();
                break;
            case 6:
                InputRestricciones.crearRestriccionContenidos(muebles);
                break;
            case 7:
                Habitacion hab = ctrlDominio.getHabitacion();
                if (hab.getFilas() <= 0 || hab.getColumnas() <= 0) {
                    System.err.println("Antes de generar se debe crear una habitacion.");
                } else {
                    ctrlGenerador.generar();
                }
                break;
            case 8:
                guardar();
                break;
            case 9:
                cargar();
                break;
            case 0:
                break;
            default:
                System.out.println("Not an option...");
                break;
            }
            option = menu();
        }
    }

    public static void init() {
        ctrlDominio = new CtrlDominio();
        ctrlDominio.inicializarCtrlDominio();
        ctrlMueble  = new CtrlMueble(ctrlDominio);
        ctrlGenerador = new CtrlGenerador(ctrlDominio);
    }

    private static void printInfoHabitacion() {
        Habitacion hab = ctrlDominio.getHabitacion();
        System.out.print("Filas: "+hab.getFilas()+" Columnas: "+hab.getColumnas());
    }

    private static int menu() {
        System.out.println("\nMain menu");
        System.out.println("1. Crear habitacion.");
        System.out.println("2. Anadir mueble.");
        System.out.println("3. Borrar mueble.");
        System.out.println("4. Consultar informacion de habitacion.");
        System.out.println("5. Consultar muebles.");
        System.out.println("6. Anadir restriccion de distancia.");
        System.out.println("7. Generar solucion.");
        System.out.println("8. Guardar.");
        System.out.println("9. Cargar.");
        System.out.println("0. Salir.");
        System.out.print("Option: ");
        return Input.getInt();
    }

    private static void guardar() {
        System.out.println("\n###### Guardar");
        boolean error = false;

        String path = null;
        do {
            try {
                System.out.println("Escribe la ruta del fichero donde quieres guardar los datos:");
                path = Input.getString();
                ctrlDominio.guardar(path);
                error = false;
            } catch (java.io.FileNotFoundException e) {
                System.out.println("ERROR: No se pudo crear el archivo por favor revise que la ruta sea correcta y tenga permisos de escritura.");
                error = true;
            }
        } while (error);
    }

    private static void cargar() {
        System.out.println("\n###### Cargar");

        boolean error = false;

        String path = null;
        do {
            try {
                System.out.println("Escribe la ruta del fichero desde el que quieres cargar los datos: ");
                path = Input.getString();
                ctrlDominio.cargar(path);
                error = false;
            } catch (java.io.FileNotFoundException e) {
                System.out.println("ERROR: No se pudo crear el archivo por favor revise que la ruta sea correcta y tenga permisos de escritura.");
                error = true;
            }

        } while (error);
        /*Cargar cargar = new Cargar(Input.getString());
        ArrayList<Objeto> objetos = cargar.getLista();
        // Crear los objetos habitacion y la lista de muebles
        habitacion = new Habitacion();
        muebles = new ArrayList<Mueble>();
        Iterator<Objeto> iterator = objetos.iterator();
        while (iterator.hasNext()) {
          Objeto objeto = iterator.next();
          if (objeto.getTipo().equals("Habitacion"))
            habitacion = getHabitacion(objeto);
          if (objeto.getTipo().equals("Mueble"))
            muebles.add(getMueble(objeto));
        }
        System.out.println("Habitacion:");
        System.out.println("Filas: " + habitacion.getFilas());
        System.out.println("Columnas: " + habitacion.getColumnas());
        for (Mueble mueble : muebles) {
          System.out.println("Mueble " + mueble.getId());
          System.out.println("Longitud: " + mueble.getLongitud());
          System.out.println("Anchura: " + mueble.getAnchura());
        }*/
    }

    private static Habitacion getHabitacion(Objeto objeto) {
        int filas = Integer.parseInt((String)objeto.getValor("filas"));
        int columnas = Integer.parseInt((String)objeto.getValor("columnas"));
        return new Habitacion(filas, columnas);
    }

    private static Mueble getMueble(Objeto objeto) {
        int id = Integer.parseInt((String)objeto.getValor("id"));
        int longitud = Integer.parseInt((String)objeto.getValor("longitud"));
        int anchura = Integer.parseInt((String)objeto.getValor("anchura"));
        return new Mueble(id, TMueble.SILLA, new Color(0,0,0), longitud, anchura);
    }
}
