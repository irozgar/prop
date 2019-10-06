package edu.upc.fib.prop.c8g2.application;

import edu.upc.fib.prop.c8g2.domain.Color;
import edu.upc.fib.prop.c8g2.domain.furniture.*;

public class FurnitureService {
    private FurnitureRepository repository;

    public FurnitureService(FurnitureRepository repository) {
        this.repository = repository;
    }

    public void create(String id, int length, int width, FurnitureType type, int red, int green, int blue) {
        Furniture furniture = new Furniture(
                new FurnitureId(id),
                new FurnitureLength(length),
                new FurnitureWidth(width),
                type,
                new Color(red, green, blue)
        );
        repository.save(furniture);
    }
}
