package edu.upc.fib.prop.c8g2.domain.furniture;

import java.util.List;

public interface FurnitureRepository {
    List<Furniture> all();
    void save(Furniture furniture);
    void remove(FurnitureId id);
}
