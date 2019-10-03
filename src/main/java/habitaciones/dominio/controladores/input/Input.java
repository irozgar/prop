package habitaciones.dominio.controladores.input;

import java.util.Scanner;

public class Input {
    public static int getInt() {
        Scanner scanner = new Scanner(System.in);
        Integer val = null;
        boolean parseable = false;
        while (!parseable) {
            try {
                String input = scanner.nextLine();
                val = Integer.parseInt(input);
                parseable = true;
            } catch(Exception e) {
                System.out.println("Valor incorrecto.");
                System.out.print("Vuelva a intentar: ");
            }
        }
        return val.intValue();
    }

    public static char getChar() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.charAt(0);
    }

    public static String getString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
