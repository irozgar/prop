package habitaciones.dominio.controladores.input;

import java.util.ArrayList;

import habitaciones.dominio.modelos.Mueble;

import habitaciones.dominio.modelos.restricciones.*;

public class InputRestricciones {

    private static int id = 0;

    public static void crearRestriccionContenidos(ArrayList<Mueble> muebles) {
        System.out.println("\nCrear restriccion de contenidos.");
        switch (menu()) {
        case 1:
            crearRestriccionDistanciaMinima(muebles);
            break;
        case 2:
            crearRestriccionDistanciaMaxima(muebles);
            break;
        }
    }

    private static int menu() {
        System.out.println("1. Restriccion de distancia minima.");
        System.out.println("2. Restriccion de distancia maxima.");
        System.out.print("Option: ");
        return Input.getInt();
    }

    private static void crearRestriccionDistanciaMinima(ArrayList<Mueble> muebles) {
        System.out.println("\nCrear restriccion de distancia minima.");
        Mueble a = null;
        while (a == null) {
            System.out.print("ID del primer mueble: ");
            a = buscarMueble(muebles, Input.getInt());
        }
        Mueble b = null;
        while (b == null) {
            System.out.print("ID del segundo mueble: ");
            b = buscarMueble(muebles, Input.getInt());
        }
        System.out.print("Distancia minima: ");
        int dist = Input.getInt();
        RestriccionDistanciaMinima r = new RestriccionDistanciaMinima(id++, dist, a, b);
        a.anadirRestriccion(r);
        b.anadirRestriccion(r);
    }

    private static void crearRestriccionDistanciaMaxima(ArrayList<Mueble> muebles) {
        System.out.println("\nCrear restriccion de distancia maxima.");
        Mueble a = null;
        while (a == null) {
            System.out.print("ID del primer mueble: ");
            a = buscarMueble(muebles, Input.getInt());
        }

        Mueble b = null;
        while (b == null) {
            System.out.print("ID del segundo mueble: ");
            b = buscarMueble(muebles, Input.getInt());
        }
        System.out.print("Distancia maxima: ");
        int dist = Input.getInt();
        RestriccionDistanciaMaxima r = new RestriccionDistanciaMaxima(id++, dist, a, b);
        a.anadirRestriccion(r);
    }

    private static Mueble buscarMueble(ArrayList<Mueble> muebles, int id) {
        for (Mueble m : muebles)
            if (m.getId() == id) return m;
        return null;
    }
}
