package edu.upc.fib.prop.c8g2.domain.furniture;

import java.util.UUID;

public class FurnitureIdMother {
    public static FurnitureId random() {
        return new FurnitureId(UUID.randomUUID().toString());
    }
}
