package com.opipo.rev.lorewiki.controller.interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public interface DeleteTest<T, I extends Serializable> extends GenericControllerTest<T,I> {

    @Test
    default void givenIdThenDeleteElement() {
        I id = getCorrectID();
        ResponseEntity<I> actual = getController().delete(id);
        assertTrue(validateResponseEntity(actual, HttpStatus.OK, id));
        Mockito.verify(getService()).delete(id);
    }
}
