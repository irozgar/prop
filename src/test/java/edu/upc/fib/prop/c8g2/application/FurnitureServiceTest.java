package edu.upc.fib.prop.c8g2.application;

import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureIdMother;
import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureMother;
import edu.upc.fib.prop.c8g2.domain.furniture.Furniture;
import edu.upc.fib.prop.c8g2.domain.furniture.FurnitureRepository;
import edu.upc.fib.prop.c8g2.infrastructure.persistence.InMemoryFurnitureRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FurnitureServiceTest {
    @Test
    public void createsFurniture() {
        FurnitureRepository repository = new InMemoryFurnitureRepository();
        FurnitureService service = new FurnitureService(repository);
        Furniture expected = FurnitureMother.random();
        service.create(
                expected.getId().getValue(),
                expected.getLength().getValue(),
                expected.getWidth().getValue(),
                expected.getType(),
                expected.getColor().getRed(),
                expected.getColor().getGreen(),
                expected.getColor().getBlue()
        );

        List<Furniture> found = repository.all();
        assertTrue(found.contains(expected));
    }

    @Test
    void removeFurniture() {
        FurnitureRepository repository = new InMemoryFurnitureRepository();
        FurnitureService service = new FurnitureService(repository);
        Furniture expected = FurnitureMother.random();
        service.create(
                expected.getId().getValue(),
                expected.getLength().getValue(),
                expected.getWidth().getValue(),
                expected.getType(),
                expected.getColor().getRed(),
                expected.getColor().getGreen(),
                expected.getColor().getBlue()
        );

        service.remove(expected.getId().getValue());

        assertEquals(0, repository.all().size());
    }
}
