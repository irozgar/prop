package edu.upc.fib.prop.c8g2.application;

import edu.upc.fib.prop.c8g2.domain.Color;
import edu.upc.fib.prop.c8g2.domain.furniture.*;

import java.util.List;
import java.util.Optional;

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

    public void remove(String id) {
        repository.remove(new FurnitureId(id));
    }

    public Optional<Furniture> search(String id) {
        return repository.search(new FurnitureId(id));
    }

    public List<Furniture> all() {
        return this.repository.all();
    }
}
