package edu.upc.fib.prop.c8g2.acl.furniture;

import edu.upc.fib.prop.c8g2.application.FurnitureService;
import edu.upc.fib.prop.c8g2.domain.furniture.Furniture;
import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureType;
import habitaciones.dominio.modelos.Color;
import habitaciones.dominio.modelos.Mueble;
import habitaciones.dominio.modelos.enums.TMueble;

import java.util.*;

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
        switch (type) {
            case MESA: return FurnitureType.TABLE;
            case CAMA: return FurnitureType.BED;
            case SILLA: return FurnitureType.CHAIR;
            case ARMARIO: return FurnitureType.CLOSET;
            case ELECTR_COCINA: return FurnitureType.HOME_APPLIANCE;
            default: return FurnitureType.UNKNOWN;
        }
    }

    private TMueble mapTMueble(FurnitureType type) {
        switch (type) {
            case TABLE: return TMueble.MESA;
            case BED: return TMueble.CAMA;
            case CHAIR: return TMueble.SILLA;
            case CLOSET: return TMueble.ARMARIO;
            case HOME_APPLIANCE: return TMueble.ELECTR_COCINA;
            default: return TMueble.DESCONOCIDO;
        }
    }

    public void remove(int id) {
        UUID uuid = idMap.get(id);
        furnitureService.remove(uuid.toString());
        idMap.remove(id);
    }

    public Mueble search(int id) {
        UUID uuid = idMap.get(id);
        Optional<Furniture> furniture = furnitureService.search(uuid.toString());
        return createFromFurniture(id, furniture.orElseThrow(() -> new IllegalArgumentException("There is no furniture with the id: " + id)));
    }

    private Mueble createFromFurniture(int id, Furniture furniture) {
        Color color = new Color(
                furniture.getColor().getRed(),
                furniture.getColor().getGreen(),
                furniture.getColor().getBlue()
        );
        return new Mueble(
                id,
                mapTMueble(furniture.getType()),
                color,
                furniture.getLength().getValue(),
                furniture.getWidth().getValue()
        );
    }

    public List<Mueble> all() {
        Set<Integer> ids = idMap.keySet();
        List<Mueble> muebles = new ArrayList<>();
        for (Integer id : ids) {
            muebles.add(search(id));
        }
        return muebles;
    }
}
