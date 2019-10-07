package edu.upc.fib.prop.c8g2.domain.furniture;

import edu.upc.fib.prop.c8g2.domain.Color;

import java.util.Objects;

public class Furniture {
    private FurnitureId id;
    private final FurnitureLength length;
    private final FurnitureWidth width;
    private final FurnitureType type;
    private Color color;

    public Furniture(FurnitureId id, FurnitureLength length, FurnitureWidth width, FurnitureType type, Color color) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.type = type;
        this.color = color;
    }

    public FurnitureId getId() {
        return id;
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
        return id.equals(furniture.id) &&
                length.equals(furniture.length) &&
                width.equals(furniture.width) &&
                type == furniture.type &&
                color.equals(furniture.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, length, width, type, color);
    }
}
