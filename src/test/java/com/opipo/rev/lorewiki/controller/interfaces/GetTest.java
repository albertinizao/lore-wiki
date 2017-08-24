package com.opipo.rev.lorewiki.controller.interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertTrue;

public interface GetTest<T, I extends Serializable> extends GenericControllerTest<T,I> {

    @Test
    @DisplayName("Get {id}")
    default void givenIdThenGetElement() {
        I id = getCorrectID();
        T expected = buildElement(id);
        Mockito.when(getService().find(id)).thenReturn(expected);
        ResponseEntity<T> actual = getController().get(id);
        assertTrue(validateResponseEntity(actual, HttpStatus.OK, expected));
    }
}
