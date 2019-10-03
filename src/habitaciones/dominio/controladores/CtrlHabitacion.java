package habitaciones.dominio.controladores;

import habitaciones.dominio.modelos.*;
import habitaciones.dominio.controladores.input.*;

public class CtrlHabitacion {
    public static Habitacion crearHabitacion() {
        Habitacion habitacion = null;
        switch (menuHabitacion()) {
        case 1:
            habitacion = crearHabitacionVacia();
            break;
        case 2:
            habitacion = crearHabitacionFilasColumnas();
            break;
            //case 3: habitacion = crearHabitacionCopia(hab); break;
        case 0:
            break;
        default:
            System.out.println("Not an option...");
        }
        return habitacion;
    }

    private static int menuHabitacion() {
        System.out.println("\nMenu para crear habitacion:");
        System.out.println("Escoger creadora:");
        System.out.println("1. Habitacion().");
        System.out.println("2. Habitacion(int filas, int columnas).");
        //System.out.println("3. Habitacion(Habiatcion hab).");
        System.out.println("0. Al menu principal.");
        System.out.print("Option: ");
        return Input.getInt();
    }

    private static Habitacion crearHabitacionVacia() {
        System.out.println("\nCreamos la habitacion vacia");
        System.out.print("Usamos la creadora Habitacion()...");
        Habitacion habitacion = new Habitacion();
        System.out.println("Ok");
        System.out.println("Ahora usaremos los metodos setFilas y setColumnas de la nueva habitacion.");
        System.out.print("Indica el numero de racholas para las filas: ");
        int filas = Input.getInt();
        System.out.print("Usamos setFilas...");
        habitacion.setFilas(filas);
        System.out.println("Ok");
        System.out.print("Indica el numero de racholas para las columnas: ");
        int columnas = Input.getInt();
        System.out.print("Usamos setColumnas...");
        habitacion.setColumnas(columnas);
        System.out.println("Ok");
        System.out.println("Habitacion creada.");
        return habitacion;
    }

    private static Habitacion crearHabitacionFilasColumnas() {
        System.out.println("\nCreamos la habitacion con Habitacion(int filas, int columnas).");
        System.out.print("Indica primero el numero de filas (en racholas): ");
        int filas = Input.getInt();
        System.out.print("Indica ahora el numero de columnas (en racholas): ");
        int columnas = Input.getInt();
        System.out.print("Usamos ahora la creadora...");
        Habitacion habitacion = new Habitacion(filas, columnas);
        System.out.println("Ok");
        return habitacion;
    }

    private static Habitacion crearHabitacionCopia(Habitacion hab) {
        Habitacion habitacion = null;
        System.out.println("\nIntentamos copiar la habitacion existente.");
        if (hab != null) {
            System.out.print("Copiamos la habitacion...");
            habitacion = new Habitacion(hab);
            System.out.println("Ok");
        } else {
            System.out.println("No has creado ninguna habitacion para copiar");
        }
        return habitacion;
    }

    public static void consultarHabitacion(Habitacion habitacion) {
        if (habitacion != null) {
            System.out.println("\nProbamos los datos de la habitacion con los metodos getFilas y getColumnas");
            System.out.print("Filas: ");
            System.out.println(habitacion.getFilas());
            System.out.print("Columnas: ");
            System.out.println(habitacion.getColumnas());
        } else {
            System.out.println("\nNo has definido una habitacion.");
        }
    }
}
