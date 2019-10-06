package edu.upc.fib.prop.c8g2.domain.furniture;

import java.util.UUID;

public class FurnitureIdMother {
    public static FurnitureId random() {
        return new FurnitureId(UUID.randomUUID().toString());
    }

    public static FurnitureId fromString(String id) {
        return new FurnitureId(id);
    }
}
