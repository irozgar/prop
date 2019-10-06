package edu.upc.fib.prop.c8g2.acl.furniture;

import edu.upc.fib.prop.c8g2.application.FurnitureService;
import edu.upc.fib.prop.c8g2.domain.furniture.Furniture;
import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureType;
import habitaciones.dominio.modelos.enums.TMueble;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class FurnitureServiceWrapper {
    private FurnitureService furnitureService;
    private HashMap<Integer, UUID> idMap = new HashMap<>();

    public FurnitureServiceWrapper(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    public void create(int id, int length, int width, TMueble type, int red, int green, int blue) {
        UUID uuid = UUID.randomUUID();
        idMap.put(id, uuid);
        furnitureService.create(
                uuid.toString(),
                length,
                width,
                mapFurnitureType(type),
                red, green, blue
        );
    }

    private FurnitureType mapFurnitureType(TMueble type) {
        if (type == TMueble.MESA) {
            return FurnitureType.TABLE;
        }

        return FurnitureType.UNKNOWN;
    }

    public void remove(int id) {
        UUID uuid = idMap.get(id);
        furnitureService.remove(uuid.toString());
    }

    public Furniture search(int id) {
        UUID uuid = idMap.get(id);
        Optional<Furniture> furniture = furnitureService.search(uuid.toString());
        return furniture.orElseThrow(() -> new IllegalArgumentException("There is no furniture with the id: " + id));
    }
}
