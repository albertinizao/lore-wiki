package com.opipo.rev.lorewiki.service.interfaces;

import com.opipo.rev.lorewiki.service.impl.AbstractServiceDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public interface FindTest<T extends Object, I extends Serializable> extends GenericInterface<T, I> {
    I getIncorrectID();

    @Test
    @DisplayName("Given id the return the element")
    default void givenIdThenRetunElement() {
        I id = getCorrectID();
        T expected = buildElement(id);
        initFindCorrect(id);
        T actual = getService().find(id);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Given miss id the return null")
    default void givenMissIdThenRetunNoElement() {
        I id = getCorrectID();
        T expected = buildElement(id);
        initFindCorrect(id);
        T actual = getService().find(getIncorrectID());
        assertNull(actual);
    }

    @Test
    @DisplayName("When find all the elements then return collection")
    default void whenInvokeGetAllThenGetAll() {
        List<T> expected = new ArrayList<>();
        expected.add(buildElement(getCorrectID()));
        Mockito.when(getRepository().findAll()).thenReturn(expected);
        Collection<T> actual = getService().findAll();
        assertEquals(expected, actual);
    }
}
