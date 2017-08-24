package com.opipo.rev.lorewiki.controller.interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public interface SaveTest<T, I extends Serializable> extends GenericControllerTest<T,I> {

    @Test
    @DisplayName("PUT {id} with doesn't match with element")
    default void givenIdAndOtherIdInElementAndTryToSaveThenGetException() {
        I id = getCorrectID();
        T element = buildElement(getIncorrectID());
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,()->getController().save(id, element));
        assertTrue(iae.getMessage().contains("The id is not the expected"));
    }

    @Test
    @DisplayName("PUT {id} with element doesn't exist")
    default void givenIdAndElementWhoDoesntExistAndTryToSaveThenGetException() {
        I id = getCorrectID();
        T element = buildElement(id);
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,()->getController().save(id, element));
        assertTrue(iae.getMessage().contains("The element doesn't exist"));
    }

    @Test
    @DisplayName("PUT {id} with element")
    default void givenIdAndElementWhoExistsAndTryToSaveThenGetSavedElement() {
        I id = getCorrectID();
        T expected = buildElement(id);
        Mockito.when(getService().find(id)).thenReturn(expected);
        Mockito.when(getService().save(expected)).thenReturn(expected);
        ResponseEntity<T> actual = getController().save(id, expected);
        assertTrue(validateResponseEntity(actual, HttpStatus.ACCEPTED, expected));
    }
}
