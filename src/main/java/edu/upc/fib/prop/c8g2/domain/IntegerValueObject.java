package edu.upc.fib.prop.c8g2.domain;

import java.util.Objects;

public abstract class IntegerValueObject {
    private int value;

    public IntegerValueObject(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerValueObject that = (IntegerValueObject) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
