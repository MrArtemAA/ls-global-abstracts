package ru.livescripts.core;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.livescripts.core.exception.EntityNotFoundException;
import ru.livescripts.core.testtools.MapDao;
import ru.livescripts.core.testtools.model.StringEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

public class BaseEntityServiceTest {
    private static final StringEntity.StringIdGenerator STRING_ID_GENERATOR = new StringEntity.StringIdGenerator();

    private static final String STRING_ENTITY_ID_1 = STRING_ID_GENERATOR.next();
    private static final String STRING_ENTITY_ID_2 = STRING_ID_GENERATOR.next();
    private static final String STRING_ENTITY_ID_3 = STRING_ID_GENERATOR.next();

    private static final StringEntity STRING_ENTITY_1 = new StringEntity();
    private static final StringEntity STRING_ENTITY_2 = new StringEntity();
    private static final StringEntity STRING_ENTITY_3 = new StringEntity();

    private static final List<StringEntity> ALL = Arrays.asList(STRING_ENTITY_1, STRING_ENTITY_2, STRING_ENTITY_3);

    private static BaseEntityService<StringEntity, String, CRUD<StringEntity, String>> stringEntityService = new BaseEntityService<StringEntity, String, CRUD<StringEntity, String>>() {};

    @BeforeClass
    public static void setUpTest() {
        STRING_ENTITY_1.setId(STRING_ENTITY_ID_1);
        STRING_ENTITY_2.setId(STRING_ENTITY_ID_2);
        STRING_ENTITY_3.setId(STRING_ENTITY_ID_3);
    }

    @Before
    public void setUp() {
        stringEntityService.setDao(MapDao.builder(STRING_ID_GENERATOR)
                .add(STRING_ENTITY_1)
                .add(STRING_ENTITY_2)
                .add(STRING_ENTITY_3)
                .build()
        );
    }

    @Test
    public void testGetById() {
        StringEntity entity = stringEntityService.get(STRING_ENTITY_ID_1);
        assertEquals(STRING_ENTITY_1, entity);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetByIdNotFound() {
        stringEntityService.get("1");
    }

    @Test
    public void testSaveNew() {
        StringEntity entity = new StringEntity();
        StringEntity created = stringEntityService.save(entity);
        assertFalse(created.isNew());
        assertEquals(created, stringEntityService.get(created.getId()));
    }

    @Test
    public void testSaveUpdate() {
        StringEntity updated = stringEntityService.save(STRING_ENTITY_1);
        assertEquals(STRING_ENTITY_1, updated);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testSaveUpdateNotFound() {
        StringEntity entity = new StringEntity();
        entity.setId(STRING_ID_GENERATOR.next());
        stringEntityService.save(entity);
    }

    @Test
    public void testDelete() {
        assertTrue(stringEntityService.delete(STRING_ENTITY_ID_1));
        List<StringEntity> expected = Arrays.asList(STRING_ENTITY_2, STRING_ENTITY_3);
        List<? extends StringEntity> actual = StreamSupport
                .stream(stringEntityService.getAll().spliterator(), false)
                .collect(Collectors.toList());

        assertFalse(expected.retainAll(actual));
        assertFalse(actual.retainAll(expected));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteNotFound() {
        stringEntityService.delete("1");
    }

    @Test
    public void testGetAll() {
        List<? extends StringEntity> actual = StreamSupport.stream(stringEntityService.getAll().spliterator(), false)
                .collect(Collectors.toList());

        assertFalse(ALL.retainAll(actual));
        assertFalse(actual.retainAll(ALL));
    }
}