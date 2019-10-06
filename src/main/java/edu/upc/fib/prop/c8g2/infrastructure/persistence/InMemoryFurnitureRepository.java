package edu.upc.fib.prop.c8g2.infrastructure.persistence;

import edu.upc.fib.prop.c8g2.domain.furniture.Furniture;
import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryFurnitureRepository implements FurnitureRepository {
    private List<Furniture> furnitureList = new ArrayList<>();

    @Override
    public List<Furniture> all() {
        return new ArrayList<>(furnitureList);
    }

    @Override
    public void save(Furniture furniture) {
        furnitureList.add(furniture);
    }
}
