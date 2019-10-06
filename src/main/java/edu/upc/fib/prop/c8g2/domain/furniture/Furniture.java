package edu.upc.fib.prop.c8g2.domain.furniture;

import edu.upc.fib.prop.c8g2.domain.Color;

import java.util.Objects;

public class Furniture {
    private final FurnitureLength length;
    private final FurnitureWidth width;
    private final FurnitureType type;
    private Color color;

    public Furniture(FurnitureLength length, FurnitureWidth width, FurnitureType type, Color color) {
        this.length = length;
        this.width = width;
        this.type = type;
        this.color = color;
    }

    public FurnitureLength getLength() {
        return length;
    }

    public FurnitureWidth getWidth() {
        return width;
    }

    public FurnitureType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Furniture furniture = (Furniture) o;
        return length.equals(furniture.length) &&
                width.equals(furniture.width) &&
                type == furniture.type &&
                color.equals(furniture.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, type, color);
    }
}
