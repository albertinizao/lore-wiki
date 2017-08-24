package com.opipo.rev.lorewiki.service.interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public interface DeleteTest<T extends Object, I extends Serializable> extends GenericInterface<T, I> {

    @Test
    @DisplayName("Given id then delete it")
    default void givenElementThenDeleteIt() {
        T element = buildElement(getCorrectID());
        getService().delete(element);
        Mockito.verify(getRepository()).delete(element);
    }

    @Test
    @DisplayName("Given element then delete it")
    default void givenIdThenDeleteIt() {
        I id = getCorrectID();
        getService().delete(id);
        Mockito.verify(getRepository()).delete(id);
    }
}
