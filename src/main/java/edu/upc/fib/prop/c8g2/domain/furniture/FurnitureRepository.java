package edu.upc.fib.prop.c8g2.domain.furniture;

import java.util.List;
import java.util.Optional;

public interface FurnitureRepository {
    List<Furniture> all();
    void save(Furniture furniture);
    void remove(FurnitureId id);
    Optional<Furniture> search(FurnitureId id);
}
