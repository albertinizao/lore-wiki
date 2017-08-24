package com.opipo.rev.lorewiki.service.interfaces;

import com.opipo.rev.lorewiki.service.impl.AbstractServiceDTO;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.Serializable;


public interface ExistsTest<T extends Object, I extends Serializable> extends GenericInterface<T, I> {

    I getCorrectID();

    @Test
    @DisplayName("Given correct id then check that exists")
    default void givenCorrectIdThenExists() {
        I id = getCorrectID();
        initFindCorrect(id);
        assertTrue(getService().exists(id));
    }

    @Test
    @DisplayName("Given miss id then check that doesn't exist")
    default void givenMissCorrectIdThenDoesntExists() {
        assertFalse(getService().exists(getCorrectID()));
    }

    @Test
    @DisplayName("Given id when check if exists then doesn't launch exception")
    default void givenCorrectIdThenDoesntThrowsExceptionBecauseItExists() {
        I id = getCorrectID();
        initFindCorrect(id);
        getService().checkExists(id);
        assertTrue(true);
    }

    @Test
    @DisplayName("Given miss id when check if exists then throw exception")
    default void givenCorrectIdThenThrowsExceptionBecauseItDoesntExists() {
        I id = getCorrectID();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->getService().checkExists(id));
        assertTrue(exception.getMessage().contains(AbstractServiceDTO.DOESNT_EXISTS));
    }

    @Test
    @DisplayName("Given id when check if doesn't exist then throw exception")
    default void givenCorrectIdThenThrowsExceptionBecauseItExists() {
        I id = getCorrectID();
        initFindCorrect(id);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->getService().checkDoesntExists(id));
        assertTrue(exception.getMessage().contains(AbstractServiceDTO.EXISTS));
    }

    @Test
    @DisplayName("Given miss id when check if doesn't exists then doesn't launch exception")
    default void givenCorrectIdThenDoesntThrowsExceptionBecauseItDoesntExists() {
        I id = getCorrectID();
        getService().checkDoesntExists(id);
        assertTrue(true);
    }
}
