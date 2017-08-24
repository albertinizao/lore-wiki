package com.opipo.rev.lorewiki.controller.interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public interface ListTest<T, I extends Serializable> extends GenericControllerTest<T,I> {

    @Test
    @DisplayName("Get /")
    default void whenInvokeGetAllThenGetAll() {
        I id = getCorrectID();
        Collection<I> expected = new ArrayList<>();
        expected.add(id);
        Collection<T> list = new ArrayList<>();
        list.add(buildElement(id));
        Mockito.when(getService().findAll()).thenReturn(list);
        ResponseEntity<Collection<I>> actual = getController().list();
        assertTrue(validateResponseEntity(actual, HttpStatus.OK, expected));
    }
}
