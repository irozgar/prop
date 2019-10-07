package edu.upc.fib.prop.c8g2.domain.furniture;

import edu.upc.fib.prop.c8g2.domain.ColorMother;

public class FurnitureMother {
    public static Furniture random() {
        return new Furniture(
            FurnitureIdMother.random(),
            FurnitureLengthMother.random(),
            FurnitureWidthMother.random(),
            FurnitureType.TABLE,
            ColorMother.random()
        );
    }
}
