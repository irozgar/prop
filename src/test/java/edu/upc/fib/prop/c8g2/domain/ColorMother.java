package edu.upc.fib.prop.c8g2.domain;

public class ColorMother {
    public static Color random() {
        return new Color(
            IntegerMother.betweenZeroAnd(255),
            IntegerMother.betweenZeroAnd(255),
            IntegerMother.betweenZeroAnd(255)
        );
    }
}
