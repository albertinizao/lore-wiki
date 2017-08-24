package com.opipo.rev.lorewiki.service.interfaces;

import java.io.Serializable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public interface CreationTest<T extends Object, I extends Serializable> extends GenericInterface<T, I> {

    default Class<T> getElementClass(){
        return (Class<T>) this.getClass().getGenericSuperclass();
    }

    @Test
    @DisplayName("Given element then save it")
    default void givenElementThenSaveIt() {
        I id = getCorrectID();
        T expected = buildElement(id);
        Mockito.when(getRepository().save(expected)).thenReturn(expected);
        T actual = getService().save(expected);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Given id then create the element and save it")
    default void givenIdThenCreateElement() {
        I id = getCorrectID();
        T expected = buildElement(id);
        Mockito.when(getRepository().save(Mockito.any(getElementClass()))).thenReturn(expected);
        T actual = getService().create(id);
        ArgumentCaptor<T> captor = ArgumentCaptor.forClass(getElementClass());
        Mockito.verify(getRepository()).save(captor.capture());
        assertNotNull(captor.getValue());
        assertEquals(expected, captor.getValue());
        assertEquals(expected, actual);
    }
}
