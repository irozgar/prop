package edu.upc.fib.prop.c8g2.acl.furniture;

import edu.upc.fib.prop.c8g2.application.FurnitureService;
import edu.upc.fib.prop.c8g2.domain.furniture.Furniture;
import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureMother;
import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureRepository;
import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureType;
import edu.upc.fib.prop.c8g2.infrastructure.persistence.InMemoryFurnitureRepository;
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

        Furniture found = wrapper.search(2);

        assertEquals(expected.getLength(), found.getLength());
        assertEquals(expected.getWidth(), found.getWidth());
        assertEquals(FurnitureType.TABLE, found.getType());
        assertEquals(expected.getColor(), found.getColor());
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

        List<Furniture> found = wrapper.all();

        assertEquals(1, found.size());
    }
}
