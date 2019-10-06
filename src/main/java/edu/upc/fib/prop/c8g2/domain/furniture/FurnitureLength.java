package edu.upc.fib.prop.c8g2.domain.furniture;

import edu.upc.fib.prop.c8g2.domain.IntegerValueObject;

public class FurnitureLength extends IntegerValueObject {
    public FurnitureLength(int value) {
        super(value);
        ensureGreaterThanZero(value);
    }

    private void ensureGreaterThanZero(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Length must be greater than zero");
        }
    }
}
