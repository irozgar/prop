package edu.upc.fib.prop.c8g2.domain.furniture;

import edu.upc.fib.prop.c8g2.domain.IntegerMother;

public class FurnitureWidthMother {
    public static FurnitureWidth random() {
        return new FurnitureWidth(IntegerMother.greaterThanZero());
    }
}
