package bg.bulgarlegacy.model.entites;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseEntityTest {

    @Test
    public void testIdGetterAndSetter() {

        BaseEntity baseEntity = new BaseEntity();


        Long expectedId = 123L;
        baseEntity.setId(expectedId);


        Long actualId = baseEntity.getId();
        assertEquals(expectedId, actualId, "ID should match");


        Long newExpectedId = 456L;
        baseEntity.setId(newExpectedId);


        Long newActualId = baseEntity.getId();
        assertEquals(newExpectedId, newActualId, "New ID should match");
    }

    @Test
    public void testDefaultIdValue() {

        BaseEntity baseEntity = new BaseEntity();


        Long defaultId = baseEntity.getId();
        assertEquals(null, defaultId, "Default ID should be null");
    }
}
