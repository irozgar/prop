package edu.upc.fib.prop.c8g2.domain;

import java.util.Random;

public class IntegerMother {
    public static int random() {
        Random r = new Random();
        return r.nextInt();
    }

    public static int greaterThanZero() {
        int result;
        do {
            result = IntegerMother.random();
        } while (result <= 0);
        return result;
    }

    public static int betweenZeroAnd(int max) {
        Random r = new Random();
        return r.nextInt(max);
    }
}
