package com.opipo.rev.lorewiki.controller.interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public interface CreateTest<T, I extends Serializable> extends GenericControllerTest<T,I> {

    T buildElementWithEmptyID();

    @Test
    @DisplayName("POST {id} with doesn't match with element")
    default void givenIdAndOtherIdInElementAndTryToCreateThenGetException() {
        I id = getCorrectID();
        T element = buildElement(getIncorrectID());
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,()->getController().create(id, element));
        assertTrue(iae.getMessage().contains("The id is not the expected"));
    }

    @Test
    @DisplayName("POST {id} with doesn't match with the persisted element")
    default void givenIdAndElementWhoExistsAndTryToCreateThenGetException() {
        I id = getCorrectID();
        T element = buildElement(id);
        Mockito.when(getService().find(id)).thenReturn(element);
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,()->getController().create(id, element));
        assertTrue(iae.getMessage().contains("The element exists now"));
    }

    @Test
    default void givenIdWhoDoesntExistAndElementWithoutIDTryToCreateThenGetCreatedElement() {
        I id = getCorrectID();
        T element = buildElement(null);
        T expected = buildElement(id);
        Mockito.when(getService().find(id)).thenReturn(null);
        Mockito.when(getService().create(id)).thenReturn(expected);
        Mockito.when(getService().save(expected)).thenReturn(expected);
        ResponseEntity<T> actual = getController().create(id, element);
        assertTrue(validateResponseEntity(actual, HttpStatus.ACCEPTED, expected));
    }

    @Test
    default void givenIdWhoDoesntExistAndTryToCreateThenGetCreatedElement() {
        I id = getCorrectID();
        T expected = buildElement(id);
        Mockito.when(getService().find(id)).thenReturn(null);
        Mockito.when(getService().create(id)).thenReturn(expected);
        Mockito.when(getService().save(expected)).thenReturn(expected);
        ResponseEntity<T> actual = getController().create(id, null);
        assertTrue(validateResponseEntity(actual, HttpStatus.ACCEPTED, expected));
    }

    @Test
    default void givenIdWhoDoesntExistAndElementWithoutIdAndTryToCreateThenGetCreatedElement() {
        I id = getCorrectID();
        T expected = buildElement(id);
        Mockito.when(getService().find(id)).thenReturn(null);
        Mockito.when(getService().create(id)).thenReturn(expected);
        Mockito.when(getService().save(expected)).thenReturn(expected);
        ResponseEntity<T> actual = getController().create(id, buildElementWithEmptyID());
        assertTrue(validateResponseEntity(actual, HttpStatus.ACCEPTED, expected));
    }

    @Test
    default void givenIdAndElementWhoDoesntExistAndTryToCreateThenGetCreatedElement() {
        I id = getCorrectID();
        T expected = buildElement(id);
        Mockito.when(getService().find(id)).thenReturn(null);
        Mockito.when(getService().save(expected)).thenReturn(expected);
        ResponseEntity<T> actual = getController().create(id, expected);
        assertTrue(validateResponseEntity(actual, HttpStatus.ACCEPTED, expected));
    }
}
