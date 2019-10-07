package edu.upc.fib.prop.c8g2.acl.furniture;

import edu.upc.fib.prop.c8g2.application.FurnitureService;
import edu.upc.fib.prop.c8g2.domain.furniture.Furniture;
import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureMother;
import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureRepository;
import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureType;
import edu.upc.fib.prop.c8g2.infrastructure.persistence.InMemoryFurnitureRepository;
import habitaciones.dominio.modelos.Mueble;
import habitaciones.dominio.modelos.enums.TMueble;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FurnitureServiceWrapperShould {
    @Test
    void createFurnitureMappingAnIntegerId() {
        FurnitureRepository repository = new InMemoryFurnitureRepository();
        FurnitureService service = new FurnitureService(repository);
        FurnitureServiceWrapper wrapper = new FurnitureServiceWrapper(service);
        Furniture expected = FurnitureMother.random();
        wrapper.create(
                2,
                expected.getLength().getValue(),
                expected.getWidth().getValue(),
                TMueble.MESA,
                expected.getColor().getRed(),
                expected.getColor().getGreen(),
                expected.getColor().getBlue()
        );

        List<Furniture> found = repository.all();
        assertEquals(1, found.size());
        assertEquals(FurnitureType.TABLE, found.get(0).getType());
    }

    @Test
    void removesFurnitureUsingMappedId() {
        FurnitureRepository repository = new InMemoryFurnitureRepository();
        FurnitureService service = new FurnitureService(repository);
        FurnitureServiceWrapper wrapper = new FurnitureServiceWrapper(service);
        Furniture expected = FurnitureMother.random();
        wrapper.create(
                2,
                expected.getLength().getValue(),
                expected.getWidth().getValue(),
                TMueble.MESA,
                expected.getColor().getRed(),
                expected.getColor().getGreen(),
                expected.getColor().getBlue()
        );

        wrapper.remove(2);

        List<Furniture> found = repository.all();
        assertEquals(0, found.size());
    }

    @Test
    void searchesFurnitureUsingTheMappedId() {
        FurnitureRepository repository = new InMemoryFurnitureRepository();
        FurnitureService service = new FurnitureService(repository);
        FurnitureServiceWrapper wrapper = new FurnitureServiceWrapper(service);
        Furniture expected = FurnitureMother.random();
        wrapper.create(
                2,
                expected.getLength().getValue(),
                expected.getWidth().getValue(),
                TMueble.MESA,
                expected.getColor().getRed(),
                expected.getColor().getGreen(),
                expected.getColor().getBlue()
        );

        Mueble mueble = wrapper.search(2);

        assertEquals(2, mueble.getId());
        assertEquals(expected.getLength().getValue(), mueble.getLongitud());
        assertEquals(expected.getWidth().getValue(), mueble.getAnchura());
        assertEquals(TMueble.MESA, mueble.getTipoMueble());
        assertEquals(expected.getColor().getRed(), mueble.getColor().getRed());
        assertEquals(expected.getColor().getGreen(), mueble.getColor().getGreen());
        assertEquals(expected.getColor().getBlue(), mueble.getColor().getBlue());
    }

    @Test
    void searchesAllFurniture() {
        FurnitureRepository repository = new InMemoryFurnitureRepository();
        FurnitureService service = new FurnitureService(repository);
        FurnitureServiceWrapper wrapper = new FurnitureServiceWrapper(service);
        Furniture expected = FurnitureMother.random();
        wrapper.create(
                2,
                expected.getLength().getValue(),
                expected.getWidth().getValue(),
                TMueble.MESA,
                expected.getColor().getRed(),
                expected.getColor().getGreen(),
                expected.getColor().getBlue()
        );

        List<Mueble> found = wrapper.all();
        assertEquals(1, found.size());
    }
}
