package com.opipo.rev.lorewiki.controller.interfaces;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

public interface IdCheckerTest<T, I extends Serializable> extends GenericControllerTest<T,I> {

    boolean checkIdFromElement(I id, T element);

    @Test
    default void givenNoIdAndNoElementThenReturnFalse() {
        assertTrue(getController().checkIdFromElement(null, null));
    }

    @Test
    default void givenNoIdAndNoIdInElementThenReturnFalse() {
        assertFalse(getController().checkIdFromElement(null, buildElement(null)));
    }

    @Test
    default void givenNoIdAndElementThenReturnFalse() {
        assertFalse(getController().checkIdFromElement(null, buildElement(getCorrectID())));
    }

    @Test
    default void givenIdAndNoElementThenReturnFalse() {
        assertFalse(getController().checkIdFromElement(getCorrectID(), null));
    }

    @Test
    default void givenIdAndNoIdInElementThenReturnFalse() {
        assertFalse(getController().checkIdFromElement(getCorrectID(), buildElement(null)));
    }

    @Test
    default void givenIdAndDistinctIdElementThenReturnFalse() {
        assertFalse(getController().checkIdFromElement(getCorrectID(), buildElement(getIncorrectID())));
    }

    @Test
    default void givenIdAndSameIdElementThenReturnTrue() {
        assertTrue(getController().checkIdFromElement(getCorrectID(), buildElement(getCorrectID())));
    }
}
