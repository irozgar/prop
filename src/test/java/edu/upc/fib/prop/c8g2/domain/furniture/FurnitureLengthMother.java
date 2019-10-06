package edu.upc.fib.prop.c8g2.domain.furniture;

import edu.upc.fib.prop.c8g2.domain.IntegerMother;

public class FurnitureLengthMother {
    public static FurnitureLength random() {
        return new FurnitureLength(IntegerMother.greaterThanZero());
    }
}
