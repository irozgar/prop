package edu.upc.fib.prop.c8g2.infrastructure.persistence;

import edu.upc.fib.prop.c8g2.domain.furniture.Furniture;
import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureId;
import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryFurnitureRepository implements FurnitureRepository {
    private HashMap<FurnitureId, Furniture> furnitureMap = new HashMap<>();

    @Override
    public List<Furniture> all() {
        return new ArrayList<>(furnitureMap.values());
    }

    @Override
    public void save(Furniture furniture) {
        furnitureMap.put(furniture.getId(), furniture);
    }

    @Override
    public void remove(FurnitureId id) {
        furnitureMap.remove(id);
    }

    @Override
    public Optional<Furniture> search(FurnitureId id) {
        return Optional.ofNullable(furnitureMap.get(id));
    }
}
